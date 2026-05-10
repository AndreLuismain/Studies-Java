import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/*
Crie um record Student(String name, String grade, double score).
Dada uma lista de 15+ estudantes,
use streams para: (1) agrupar por nota
(Map<String, List<Student>>),
(2) encontrar a média de score por nota (Map<String, Double>),
(3) encontrar o melhor aluno por nota (Map<String, Optional<Student>>).
Tudo com uma única stream pipeline cada.
Data: 09/05/2026
 */


record Student(String name, String grade, double score){}



public class Hard {
    static void main(String[] args) {
        List<Student> listaAlunos = List.of(
                new Student("Aluno1", "A", 155.5),
                new Student("Aluno2", "A", 130.5),
                new Student("Aluno3", "A", 125.5),
                new Student("Aluno4", "B", 85.0),
                new Student("Aluno5", "B", 92.5),
                new Student("Aluno6", "B", 78.0),
                new Student("Aluno7", "C", 60.0),
                new Student("Aluno8", "C", 45.5),
                new Student("Aluno9", "A", 140.0),
                new Student("Aluno10", "B", 88.0),
                new Student("Aluno11", "C", 70.0),
                new Student("Aluno12", "A", 160.0), // Melhor do A
                new Student("Aluno13", "B", 95.0),  // Melhor do B
                new Student("Aluno14", "C", 55.0),
                new Student("Aluno15", "A", 110.0),
                new Student("Aluno16", "C", 72.5)   // Melhor do C
        );

        // 1. Agrupar estudantes por nota (grade)
        // O coletor padrão do groupingBy já joga os itens em uma List
        Map<String, List<Student>> agruparPorNota = listaAlunos.stream()
                .collect(Collectors.groupingBy(Student::grade));

        System.out.println("1. Agrupado por Nota (Listas):");
        agruparPorNota.forEach((nota, alunos) -> {
            System.out.println("   Turma " + nota + ": " + alunos.size() + " alunos");
        });
        System.out.println("--------------------------------------------------");


        // 2. Encontrar a média de score por nota
        // Usamos averagingDouble como "coletor secundário" para processar a lista de cada grupo
        Map<String, Double> encontrarMedia = listaAlunos.stream()
                .collect(Collectors.groupingBy(
                        Student::grade,
                        Collectors.averagingDouble(Student::score)
                ));

        System.out.println("2. Média de Score por Nota:");
        encontrarMedia.forEach((nota, media) -> {
            System.out.printf("   Turma %s: Média %.2f\n", nota, media);
        });
        System.out.println("--------------------------------------------------");


        // 3. Encontrar o melhor aluno por nota
        // Usamos maxBy comparando o score. Ele retorna Optional porque a turma poderia estar vazia.
        Map<String, Optional<Student>> encontrarMelhor = listaAlunos.stream()
                .collect(Collectors.groupingBy(
                        Student::grade,
                        Collectors.maxBy(Comparator.comparingDouble(Student::score))
                ));

        System.out.println("3. Melhor Aluno de Cada Nota:");
        encontrarMelhor.forEach((nota, alunoOpt) -> {
            // Se o aluno existir dentro do Optional, imprime os dados dele
            alunoOpt.ifPresent(aluno ->
                    System.out.println("   Turma " + nota + " -> " + aluno.name() + " (Score: " + aluno.score() + ")")
            );
        });
    }
}
