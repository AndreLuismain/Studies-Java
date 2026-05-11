/*
Implemente um SimpleCache<K,V> estendendo LinkedHashMap
com capacity máxima. Sobrescreva removeEldestEntry() para
retornar true quando size() > capacity. Use o construtor
com accessOrder=true. Compare o resultado com o exercício
anterior (LRU manual) — perceba o quão mais simples fica.
Valide com os mesmos casos de teste.
Data:
 */


import java.util.LinkedHashMap;
import java.util.Map;

class SimpleCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public SimpleCache(int capacity) {
        // super(initialCapacity, loadFactor, accessOrder)
        // accessOrder = true define que a ordem de iteração será a do último acesso (LRU)
        // accessOrder = false (padrão) definiria a ordem de inserção
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // O LinkedHashMap chama este método automaticamente após cada put().
        // Se retornar true, a entrada mais antiga (eldest) é ejetada da memória.
        return size() > capacity;
    }
}

//Classe MAIN
public class Medium {
    static void main(String[] args) {
        // Criando um cache LRU para no máximo 3 elementos
        SimpleCache<String, String> cache = new SimpleCache<>(3);

        System.out.println("1. Inserindo 3 elementos (A, B, C):");
        cache.put("A", "Valor A");
        cache.put("B", "Valor B");
        cache.put("C", "Valor C");
        System.out.println("Estado do Cache: " + cache); // Saída esperada: [A, B, C]

        System.out.println("\n2. Acessando a chave 'A' (A vai para o final da fila de recentes):");
        cache.get("A");
        System.out.println("Estado do Cache: " + cache); // Saída esperada: [B, C, A]

        System.out.println("\n3. Inserindo o 4º elemento 'D' (Estoura capacidade de 3):");
        // Como 'B' se tornou o elemento menos recentemente utilizado (ficou no topo da fila), ele deve ser ejetado.
        cache.put("D", "Valor D");
        System.out.println("Estado do Cache: " + cache); // Saída esperada: [C, A, D]

        System.out.println("\n4. Atualizando a chave 'C' (C volta a ser o mais recente):");
        cache.put("C", "Novo Valor C");
        System.out.println("Estado do Cache: " + cache); // Saída esperada: [A, D, C]
    }
}
