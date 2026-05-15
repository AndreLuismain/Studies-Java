/*
Dada uma List<String>, reescreva os seguintes lambdas como method references:
(1) s -> s.toUpperCase(), (2) s -> System.out.println(s), (3) (a, b) -> a.compareTo(b),
(4) s -> s.isEmpty(). Depois escreva um pipeline completo usando apenas method references:
filtrar não-vazios, uppercase, ordenar, imprimir.

 Data: 15/05/2026
 */


import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Easy3 {
    public static void main(String[] args) {
        List<String> palavras = new ArrayList<>();
        palavras.add("abacate");
        palavras.add("bacate");
        palavras.add("acate");
        palavras.add("bac");
        palavras.add("ac");
        palavras.add("");

        // (1) s -> s.toUpperCase()
        System.out.println("=====================");
        List<String> palavrasMaiusculas = palavras.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println(palavrasMaiusculas);

        // (2) s -> System.out.println(s)
        System.out.println("=====================");
        palavras.forEach(System.out::println);

        // (3) (a, b) -> a.compareTo(b)
        System.out.println("=====================");
        palavras.sort(String::compareTo);
        System.out.println(palavras);

        // (4) s -> s.isEmpty() (Filtrando os NÃO vazios para exibir)
        System.out.println("=====================");
        List<String> palavrasNaoVazias = palavras.stream()
                .filter(Predicate.not(String::isEmpty))
                .toList();
        System.out.println(palavrasNaoVazias);

        // filtrar não-vazios -> uppercase -> ordenar -> imprimir
        System.out.println("=====================");
        palavras.stream()
                .filter(Predicate.not(String::isEmpty))
                .map(String::toUpperCase)
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}
