/*
Crie um sistema de processamento de texto: defina
Function<String,String>s para: trim, lowercase, removeSpecialChars, truncate(n).
Monte pipelines com andThen(): Function<String,String> sanitize = trim.andThen(lowercase).andThen(removeSpecialChars).
Crie um TextPipeline genérico que aceita List<Function<String,String>> e usa reduce(Function.identity(), Function::andThen) para compor todas.

Data: 15/05/2026
 */

import java.util.List;
import java.util.function.Function;


class Hard3 {
    // Definindo as funções individuais
    static Function<String, String> trim = String::trim;
    static Function<String, String> lowercase = String::toLowerCase;
    // Remove tudo que não for letra, número ou espaço
    static Function<String, String> removeSpecialChars = s -> s.replaceAll("[^a-zA-Z0-9\\s]", "");

    // Metodo que retorna uma Function configurada com o tamanho máximo
    static Function<String, String> truncate(int n) {
        return s -> s.length() > n ? s.substring(0, n) : s;
    }

    // Definindo o Pipeline Dinâmico
    public static class TextPipeline {
        private final Function<String, String> pipeline;

        public TextPipeline(List<Function<String, String>> steps) {
            // Usa Function.identity() como valor inicial (uma função que retorna a própria entrada)
            // Usa Function::andThen como acumulador para encadear as funções
            this.pipeline = steps.stream()
                    .reduce(Function.identity(), Function::andThen);
        }

        public String apply(String text) {
            return pipeline.apply(text);
        }
    }
    public static void main(String[] args) {
        String rawText = "   Processamento FUNCIONAL em Java: O Guia Definitivo!!! 2026   ";
        System.out.println("Texto Original: '" + rawText + "'\n");

        // Exemplo 1: Pipeline fixo usando andThen() -
        // Executa na ordem: trim -> lowercase -> removeSpecialChars
        Function<String, String> sanitize = trim
                .andThen(lowercase)
                .andThen(removeSpecialChars);

        System.out.println("1. Resultado (sanitize via andThen):");
        System.out.println("'" + sanitize.apply(rawText) + "'\n");


        // Exemplo 2: Usando compose()
        // Executa de trás pra frente: primeiro trim, depois lowercase
        Function<String, String> formatBeforeLower = lowercase.compose(trim);

        System.out.println("2. Resultado (format via compose):");
        System.out.println("'" + formatBeforeLower.apply(rawText) + "'\n");


        // Exemplo 3: Usando a classe TextPipeline genérica 
        TextPipeline customPipeline = new TextPipeline(List.of(
                trim,
                lowercase,
                removeSpecialChars,
                truncate(23) // Corta no 23º caractere
        ));

        System.out.println("3. Resultado (TextPipeline dinâmico com reduce):");
        System.out.println("'" + customPipeline.apply(rawText) + "'");
    }
}
