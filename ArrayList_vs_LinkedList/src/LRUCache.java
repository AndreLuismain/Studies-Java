/*
Exercise HARD
Implemente LRUCache<K,V> do zero com capacity máxima. Internamente:
HashMap<K, Node<K,V>> para lookup + LinkedList para ordem de acesso (mais recente na frente).
Ao fazer get(key): mova o nó para o início da lista. Ao fazer put(key, value):
se cheio, remova o nó do fim (menos recente). Valide com testes: cache de capacity 3, insira 5 items,
verifique quais foram evicted.
Data: 11/05/2026
 */


import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    // Classe interna representando o Nó da Lista Duplamente Encadeada
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> cache;

    //Nós "fictícios" (dummy) para evitar lidar com null pointers nos extremos
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que 0.");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();

        //Inicializa a lista com head e tail conectados
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Retorna o valor e move o nó para o início (Mais Recente).
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null; // Cache miss
        }
        // Cache hit: atualiza a ordem de acesso
        moveToFirst(node);
        return node.value;
    }

    /**
     * Insere ou atualiza um par chave-valor. Evicta o menos recente se cheio.
     */
    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);

        if (node != null) {
            // Se já existe, atualiza o valor e move para o início
            node.value = value;
            moveToFirst(node);
        } else {
            // Se a capacidade máxima foi atingida, remove o LRU (fim da lista)
            if (cache.size() >= capacity) {
                Node<K, V> lru = removeLast();
                cache.remove(lru.key);
                System.out.println("[EVICTED] Chave removida por falta de espaço: " + lru.key);
            }

            // Cria novo nó, insere no mapa e no início da lista
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addFirst(newNode);
        }
    }

    //MÉTODOS PRIVADOS DE MANIPULAÇÃO DA LISTA (O(1))

    //Adiciona um nó logo após o Head (início)
    private void addFirst(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    //Remove um nó arbitrário da lista desfazendo suas conexões
    private void removeNode(Node<K, V> node) {
        Node<K, V> prevNode = node.prev;
        Node<K, V> nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    //Move um nó existente para o início
    private void moveToFirst(Node<K, V> node) {
        removeNode(node);
        addFirst(node);
    }

    // Remove e retorna o nó logo antes do Tail (o menos recentemente usado)
    private Node<K, V> removeLast() {
        Node<K, V> lruNode = tail.prev;
        removeNode(lruNode);
        return lruNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[MRU] ");
        Node<K, V> curr = head.next;
        while (curr != tail) {
            sb.append("{").append(curr.key).append("=").append(curr.value).append("} ");
            curr = curr.next;
        }
        sb.append("[LRU]");
        return sb.toString();
    }

    //VALIDAÇÃO (MAIN)
    public static void main(String[] args) {
        System.out.println("=== TESTE: LRUCache de Capacidade 3 ===");
        LRUCache<Integer, String> lru = new LRUCache<>(3);

        System.out.println("\n1. Inserindo 3 itens (1, 2, 3)...");
        lru.put(1, "Item 1");
        lru.put(2, "Item 2");
        lru.put(3, "Item 3");
        System.out.println("Estado do Cache: " + lru);

        System.out.println("\n2. Inserindo o 4º item (provocará eviction do 1)...");
        lru.put(4, "Item 4");
        System.out.println("Estado do Cache: " + lru);

        System.out.println("\n3. Acessando a chave 2 via get() (move para MRU)...");
        System.out.println("Valor retornado: " + lru.get(2));
        System.out.println("Estado do Cache: " + lru);

        System.out.println("\n4. Inserindo o 5º item (provocará eviction do 3, pois o 2 foi salvo pelo get)...");
        lru.put(5, "Item 5");
        System.out.println("Estado do Cache: " + lru);

        System.out.println("\n5. Verificando chaves evictadas via get()...");
        System.out.println("get(1) -> " + lru.get(1) + " (Correto, foi evictado)");
        System.out.println("get(3) -> " + lru.get(3) + " (Correto, foi evictado)");
        System.out.println("get(4) -> " + lru.get(4) + " (Presente no cache)");
    }
}