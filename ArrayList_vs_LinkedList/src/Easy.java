/*
Crie um benchmark: insira 50.000 elementos no índice 0
de um ArrayList e de um LinkedList. Meça e compare os tempos.
Em seguida, acesse o elemento do meio (índice size/2) em ambos.
Imprima os resultados em ms.

Documente no código: por que ArrayList perde na inserção no início
e ganha no acesso por índice?

Data: 10/05/2026
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Classe MAIN
public class Easy {
    static void main(String[] args) {
        int total = 50_000; //Quantidade de elementos
        List<Integer> arrayListTest = new ArrayList<>();
        List<Integer> linkedListTest = new LinkedList<>();

        System.out.println("--- 1. INSERÇÃO NO ÍNDICE 0 ---");

        /*
         * POR QUE O ARRAYLIST PERDE AQUI?
         * Porque ele usa um array fixo por baixo. Ao inserir no índice 0, ele precisa
         * empurrar (fazer shift) TODOS os elementos existentes para a direita O(n).
         * Arraylist precisa mover todos os dados inseridos antes para inserir no i(0).
         * A LinkedList ganha porque só cria um nó na memória e ajusta ponteiros O(1).
         */

        // Medindo ArrayList
        long inicio = System.nanoTime();
        for (int i = 0; i < total; i++)
            arrayListTest.add(0, i);
        System.out.printf("ArrayList inserção:  %.2f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);

        // Medindo LinkedList
        inicio = System.nanoTime();
        for (int i = 0; i < total; i++)
            linkedListTest.add(0, i);
        System.out.printf("LinkedList inserção: %.2f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);


        System.out.println("\n--- 2. ACESSO AO ELEMENTO DO MEIO ---");

        /*
         * POR QUE O ARRAYLIST GANHA AQUI?
         * Porque o acesso em arrays nativos é instantâneo O(1) via cálculo matemático de memória.
         * A LinkedList perde porque não tem acesso direto; ela precisa pular de nó em nó
         * desde o início até alcançar a posição do meio O(n), tendo que olhar todos os indices.
         */

        int indice_size = total / 2;

        // Medindo ArrayList
        inicio = System.nanoTime();
        arrayListTest.get(indice_size);
        System.out.printf("ArrayList acesso:  %.4f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);

        // Medindo LinkedList
        inicio = System.nanoTime();
        linkedListTest.get(indice_size);
        System.out.printf("LinkedList acesso: %.4f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);


    }
}
