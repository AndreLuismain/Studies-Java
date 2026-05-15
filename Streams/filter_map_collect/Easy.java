/*
Dada List<Integer> numbers de 1 a 20, escreva pipelines separados para:
(1) filtrar pares, elevar ao quadrado, coletar em lista,
(2) somar todos com reduce(0, Integer::sum),
(3) encontrar o primeiro múltiplo de 7 com findFirst(),
(4) verificar se algum é maior que 15 com anyMatch(),
(5) verificar se todos são positivos com allMatch().
Data: 14/05/2026
 */


import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Easy {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1,20).boxed().toList();

        //Filtra os pares, eleva ao quadrado, coloca em outra lista
        List<Integer> list1 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n*n)
                .toList();

        //Somar todos
        int somaTotal = numbers.stream()
                .reduce(0, Integer::sum);

        //Encontra o primeiro divisível por 7
        int primeiroMultiplo7 = numbers.stream()
                .filter(n -> n % 7 == 0)
                .findFirst()
                .orElse(-1);

        //Verifica se tem maior que 15
        boolean maior15 = numbers.stream()
                .anyMatch(n -> n > 15);

        //Verifica se todos são positivos
        boolean todosPositivos = numbers.stream()
                .allMatch(n -> n > 0);

        System.out.println(list1);
        System.out.println(somaTotal);
        System.out.println(primeiroMultiplo7);
        System.out.println(maior15);
        System.out.println(todosPositivos);
    }
}
