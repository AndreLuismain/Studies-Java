/*
Dado um texto longo, conte a frequência de cada palavra.
Implemente duas versões: (1) com getOrDefault(),
(2) com map.merge(word, 1, Integer::sum). Após contar,
use o Map para: encontrar a palavra mais frequente,
listar palavras que aparecem mais de 3 vezes, mostrar o top-5.
Ignore case e pontuação.
Data: 09/05/2026
 */



import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class Medium {
    static void main(String[] args) {

        String textoLongo = "O café é bom. Programar em Java com café é muito bom. " +
                "O programador bebe café, escreve código em Java, e bebe mais café. " +
                "Sem café, o código Java não sai. Café, Java e código são a vida!";

        System.out.println("Texto original:\n" + textoLongo + "\n");

        // --- ETAPA 1: LIMPEZA DO TEXTO ---
        // 1. toLowerCase() -> ignora case (tudo minúsculo)
        // 2. replaceAll -> usa regex para trocar pontuações por espaço
        // 3. split -> quebra o texto em uma matriz de palavras a cada espaço
        String[] palavras = textoLongo.toLowerCase()
                .replaceAll("[\\p{Punct}]", " ")
                .split("\\s+");


        // --- ETAPA 2: CONTAGEM (VERSÃO 1 - getOrDefault) ---
        Map<String, Integer> frequenciaV1 = new HashMap<>();
        for (String palavra : palavras) {
            // Pega o valor atual (ou 0 se não existir) e soma 1
            frequenciaV1.put(palavra, frequenciaV1.getOrDefault(palavra, 0) + 1);
        }
        System.out.println("Contagem (Versão getOrDefault): " + frequenciaV1);


        // --- ETAPA 3: CONTAGEM (VERSÃO 2 - merge) ---
        Map<String, Integer> frequenciaV2 = new HashMap<>();
        for (String palavra : palavras) {
            // Se não existir, insere 1. Se existir, soma o antigo com 1
            frequenciaV2.merge(palavra, 1, Integer::sum);
        }
        System.out.println("Contagem (Versão merge):        " + frequenciaV2);
        System.out.println("--------------------------------------------------");


        // --- ETAPA 4: ANÁLISES (Usando o mapa da Versão 2) ---

        // A) Encontrar a palavra mais frequente
        // Usamos stream para comparar as entradas (Entry) pelo valor (getValue)
        String maisFrequente = frequenciaV2.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhuma");

        System.out.println(" Palavra mais frequente: '" + maisFrequente + "' " +
                "(aparece " + frequenciaV2.get(maisFrequente) + " vezes)");


        // B) Listar palavras que aparecem MAIS de 3 vezes
        List<String> maisDeTresVezes = frequenciaV2.entrySet().stream()
                .filter(par -> par.getValue() > 3) // Filtro condicional
                .map(Map.Entry::getKey)            // Pega só a chave (palavra)
                .toList();

        System.out.println(" Palavras que aparecem > 3 vezes: " + maisDeTresVezes);


        // C) Mostrar o Top-5
        // Ordena de forma decrescente (reversed) e limita a 5 resultados
        List<Map.Entry<String, Integer>> top5 = frequenciaV2.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .toList();

        System.out.println(" TOP 5 palavras mais usadas:");
        int posicao = 1;
        for (Map.Entry<String, Integer> par : top5) {
            System.out.println("   " + posicao + "º lugar: '" + par.getKey() +
                    "' -> " + par.getValue() + " vezes");
            posicao++;
        }



    }
}
