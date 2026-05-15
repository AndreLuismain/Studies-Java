/*
Dado um List<String> lines com parágrafos de texto,
use uma única pipeline de stream para: fazer split de cada linha em palavras (flatMap),
normalizar (lowercase, remover pontuação), filtrar stop words ("a", "o", "de"...),
contar frequência (Collectors.groupingBy + counting), retornar top-10 palavras como
List<Map.Entry<String,Long>> ordenadas por frequência desc.

Data: 14/05/2026
 */

import java.util.*;
import java.util.stream.Collectors;


public class Hard {
    public static void main(String[] args) {
        // Base de dados simulada (Parágrafos variados)
        List<String> lines = List.of(
                "O Java 21 é incrível! Com streams, o código fica muito mais limpo.",
                "Aprender flatMap é essencial para dominar fluxos de dados complexos no Java.",
                "O map transforma um em um, mas o flatMap transforma um em muitos, achatando as listas.",
                "Java, Java, Java! Código limpo, streams eficientes e muito poder de processamento."
        );

        //Conjunto de Stop Words (busca em O(1) usando Set)
        Set<String> stopWords = Set.of(
                "o", "a", "e", "é", "em", "um", "uma", "de", "do", "da",
                "para", "com", "mas", "no", "na", "os", "as"
        );

        //A SUPER PIPELINE
        List<Map.Entry<String, Long>> top10Palavras = lines.stream()
                // 1. flatMap: Quebra a linha por espaços ou pontuação e achata os arrays resultantes
                .flatMap(line -> Arrays.stream(line.split("[\\p{Punct}\\s]+")))

                // 2. Normalização: Filtra eventuais vazios e passa para minúsculo
                .filter(word -> !word.isBlank())
                .map(String::toLowerCase)

                // 3. Filtro de Stop Words
                .filter(word -> !stopWords.contains(word))

                // 4. Agrupamento e Contagem (Gera o Map<String, Long>)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))

                // 5. Transição suave: Pegamos as linhas do Mapa e abrimos uma nova Stream
                .entrySet().stream()

                // 6. Ordenação Descrescente comparando pelo Valor (Frequência)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())

                // 7. Extrai apenas o Top 10
                .limit(10)

                // 8. Terminal: Coleta para Lista
                .toList();
        //Exibição do resultado
        System.out.println("--- TOP 10 PALAVRAS MAIS FREQUENTES ---");
        top10Palavras.forEach(entry ->
                System.out.printf("- '%s': %d vez(es)%n", entry.getKey(), entry.getValue())
        );
    }
}






























/* ============================================================================
 * GUIA DE CONSULTA: PIPELINE DE PROCESSAMENTO DE TEXTO COM FLATMAP (JAVA 21)
 * ============================================================================
 * Objetivo: Pegar uma lista de frases, extrair todas as palavras, limpá-las,
 * contar a frequência de cada uma e devolver as 10 mais usadas.
 * * --- FASE 1: EXTRAÇÃO E ACHATAMENTO ---
 * * .stream()
 * -> Inicia a esteira. Neste momento temos um Stream<String> onde cada elemento
 * é uma frase inteira (ex: "O Java 21 é incrível!").
 * * * .flatMap(line -> Arrays.stream(line.split("[\\p{Punct}\\s]+")))
 * -> O CORAÇÃO DO CÓDIGO. Se usássemos apenas .map(), teríamos um Stream de arrays
 * de palavras (Stream<String[]>). O .flatMap() faz duas coisas:
 * 1. O 'split' quebra a frase em um array de palavras usando uma expressão
 * regular que arranca espaços (\s) e pontuações (\p{Punct}) de uma vez só.
 * 2. O 'Arrays.stream()' transforma esse array em uma micro-esteira.
 * 3. O flatMap "achata" (dissolve) todas essas micro-esteiras em uma única
 * esteira principal. Agora temos um Stream<String> de palavras individuais.
 * * --- FASE 2: LIMPEZA E NORMALIZAÇÃO ---
 * * .filter(word -> !word.isBlank())
 * -> Peneira de segurança: remove eventuais pedaços de texto vazios gerados pelo split.
 * * * .map(String::toLowerCase)
 * -> Padronização: transforma tudo em minúsculo para que "Java" e "java" sejam
 * contados como a mesma palavra.
 * * * .filter(word -> !stopWords.contains(word))
 * -> Peneira de relevância: consulta o Set de stopWords e descarta artigos e
 * preposições ("o", "a", "de", etc.).
 * * --- FASE 3: AGRUPAMENTO E CONTAGEM ---
 * * .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
 * -> Cria os "baldes" (Mapa).
 * - A chave (word -> word) é a própria palavra.
 * - O coletor secundário 'counting()' conta quantos elementos caíram dentro
 * de cada balde. O resultado final desta etapa é um Map<String, Long>.
 * * --- FASE 4: EXTRAÇÃO DO PÓDIO (TOP 10) ---
 * * .entrySet().stream()
 * -> Como a etapa anterior encerrou a stream retornando um Mapa, pegamos as
 * "linhas" desse mapa (Map.Entry, que contém Chave=Palavra e Valor=Frequência)
 * e abrimos uma NOVA Stream para processar esses pares.
 * * * .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
 * -> Ordena as linhas do mapa. Usamos 'comparingByValue()' para focar no número
 * de repetições (Long) e aplicamos '.reversed()' para que as palavras com
 * maior contagem fiquem no topo da esteira.
 * * * .limit(10)
 * -> Corta a esteira, deixando passar apenas as 10 primeiras posições.
 * * * .toList()
 * -> Operação Terminal: empacota os 10 pares (Map.Entry) vencedores em uma
 * Lista final imutável.
 * ============================================================================
 */