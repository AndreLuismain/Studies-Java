/*
Implemente um Autocomplete usando TreeMap<String, List<String>>.
Para cada palavra inserida, indexe todos os seus prefixos como
chaves (ex: "java" → chaves "j", "ja", "jav", "java"). Implemente
suggest(String prefix) usando subMap() para recuperar todas as palavras
que começam com o prefixo dado. Compare a performance com uma busca
linear em lista com 10.000 palavras.
Data: 11/05/2026
 */


import java.util.*;

// CLASSE DO AUTOCOMPLETE (Implementação com TreeMap)
class Autocomplete {
    private final TreeMap<String, List<String>> indicePrefixos = new TreeMap<>();

    public void insert(String palavra) {
        if (palavra == null || palavra.isEmpty()) return;

        String palavraNormalizada = palavra.toLowerCase();

        // Indexa todos os prefixos possíveis da palavra
        for (int i = 1; i <= palavraNormalizada.length(); i++) {
            String prefixo = palavraNormalizada.substring(0, i);
            indicePrefixos.computeIfAbsent(prefixo, k -> new ArrayList<>()).add(palavra);
        }
    }

    public List<String> suggest(String prefixo) {
        if (prefixo == null || prefixo.isEmpty()) return Collections.emptyList();

        String pref = prefixo.toLowerCase();

        // Calcula o limite superior para o subMap (ex: "jav" -> "jaw")
        String limiteSuperior = pref.substring(0, pref.length() - 1) +
                (char) (pref.charAt(pref.length() - 1) + 1);

        // Extrai o intervalo exato da árvore
        SortedMap<String, List<String>> subMapa = indicePrefixos.subMap(pref, limiteSuperior);

        // Agrupa os resultados removendo duplicatas
        Set<String> sugestoesUnicas = new LinkedHashSet<>();
        for (List<String> palavras : subMapa.values()) {
            sugestoesUnicas.addAll(palavras);
        }

        return new ArrayList<>(sugestoesUnicas);
    }
}

// CLASSE PRINCIPAL DE TESTE E COMPARAÇÃO
public class Hard {
    public static void main(String[] args) {
        Autocomplete autocomplete = new Autocomplete();
        List<String> listaLinear = new ArrayList<>();

        System.out.println("Gerando e indexando 10.000 palavras no cache...");

        // Inserindo palavras reais para validação
        String[] conhecidas = {"java", "javascript", "javalin", "jardim", "janela", "maca", "macaco"};
        for (String p : conhecidas) {
            autocomplete.insert(p);
            listaLinear.add(p);
        }

        // Preenchendo o restante para somar 10.000 registros
        for (int i = 0; i < 9993; i++) {
            String simulada = "palavra" + i;
            autocomplete.insert(simulada);
            listaLinear.add(simulada);
        }

        String prefixoBusca = "jav";
        System.out.println("\nBuscando sugestões para o prefixo: '" + prefixoBusca + "'\n");

        // TESTE 1: TREEMAP
        long inicioTree = System.nanoTime();
        List<String> resTree = autocomplete.suggest(prefixoBusca);
        long tempoTree = System.nanoTime() - inicioTree;

        // TESTE 2: BUSCA LINEAR
        long inicioLinear = System.nanoTime();
        List<String> resLinear = new ArrayList<>();
        for (String palavra : listaLinear) {
            if (palavra.startsWith(prefixoBusca)) {
                resLinear.add(palavra);
            }
        }
        long tempoLinear = System.nanoTime() - inicioLinear;

        // EXIBIÇÃO DOS RESULTADOS
        System.out.println("Resultado encontrado: " + resTree);
        System.out.println("--------------------------------------------------");
        System.out.printf("TreeMap (subMap):    %,d nanosegundos%n", tempoTree);
        System.out.printf("Busca Linear (List): %,d nanosegundos%n", tempoLinear);
        System.out.println("--------------------------------------------------");

        if (tempoTree > 0) {
            System.out.println(" O TreeMap foi aproximadamente " + (tempoLinear / tempoTree) + "x mais rápido.");
        }
    }
}