/*
Crie record Transaction(String id, String category, double amount, LocalDate date).
Dadas 20+ transações variadas, use streams para:
(1) maior valor por categoria (Map<String, Optional<Transaction>>),
(2) total gasto por categoria (Map<String, Double>),
(3) transações do último mês ordenadas por valor desc,
(4) top 3 categorias por total gasto.
Data: 14/05/2026
 */


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

record Transaction(String Id, String category, double amount, LocalDate date){}

class Medium {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("T01", "Alimentação", 150.50, LocalDate.of(2026, 5, 1)),
                new Transaction("T02", "Transporte", 45.00, LocalDate.of(2026, 5, 2)),
                new Transaction("T03", "Lazer", 200.00, LocalDate.of(2026, 5, 3)),
                new Transaction("T04", "Alimentação", 85.30, LocalDate.of(2026, 5, 5)),
                new Transaction("T05", "Salário", 5000.00, LocalDate.of(2026, 5, 5)),
                new Transaction("T06", "Contas", 350.00, LocalDate.of(2026, 5, 10)),
                new Transaction("T07", "Alimentação", 320.00, LocalDate.of(2026, 5, 12)),
                new Transaction("T08", "Transporte", 55.00, LocalDate.of(2026, 5, 12)),
                new Transaction("T09", "Lazer", 120.00, LocalDate.of(2026, 5, 15)),
                new Transaction("T10", "Investimentos", 1000.00, LocalDate.of(2026, 5, 15)),
                new Transaction("T11", "Alimentação", 45.00, LocalDate.of(2026, 5, 18)),
                new Transaction("T12", "Contas", 120.00, LocalDate.of(2026, 5, 20)),
                new Transaction("T13", "Lazer", 80.00, LocalDate.of(2026, 5, 22)),
                new Transaction("T14", "Transporte", 30.00, LocalDate.of(2026, 5, 25)),
                new Transaction("T15", "Alimentação", 190.00, LocalDate.of(2026, 5, 28)),
                new Transaction("T16", "Saúde", 450.00, LocalDate.of(2026, 4, 10)), // Mês anterior
                new Transaction("T17", "Alimentação", 110.00, LocalDate.of(2026, 4, 15)),
                new Transaction("T18", "Lazer", 300.00, LocalDate.of(2026, 4, 20)),
                new Transaction("T19", "Contas", 200.00, LocalDate.of(2026, 4, 25)),
                new Transaction("T20", "Transporte", 50.00, LocalDate.of(2026, 4, 30))
        );

        //(1) maior valor por categoria
        Map<String, Optional<Transaction>> teste1 = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.maxBy(Comparator.comparingDouble(Transaction::amount))
                ));

        //(2) total gasto por categoria
        Map<String, Double> teste2 = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.summingDouble(Transaction::amount)
                ));

        //(3) transações do último mês ordenadas por valor desc
        List<Transaction> teste3 = transactions.stream()
                .filter(t -> t.date().getMonthValue() == 4 && t.date().getYear() == 2026)
                // Ordena comparando o amount de forma decrescente (reversa)
                .sorted(Comparator.comparingDouble(Transaction::amount).reversed())
                .toList();

       //(4) top 3 categorias por total gasto.
        Map<String, Double> teste4 = teste2.entrySet().stream()
                // Ordena as linhas do mapa pelo valor (Double) do maior para o menor
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // Pega apenas os 3 primeiros
                .limit(3)
                // Coleta de volta preservando a ordem do pódio (LinkedHashMap)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        // Exibindo os resultados formatados com forEach
        System.out.println("--- (1) Maior valor por categoria ---");
        teste1.forEach((cat, t) -> System.out.println(cat + ": " + t.get().amount()));

        System.out.println("\n--- (2) Total gasto por categoria ---");
        teste2.forEach((cat, total) -> System.out.println(cat + ": " + total));

        System.out.println("\n--- (3) Transações de Abril (Decrescente) ---");
        teste3.forEach(t -> System.out.println(t.Id() + " (" + t.category() + "): R$ " + t.amount()));

        System.out.println("\n--- (4) Top 3 Categorias mais gastas ---");
        teste4.forEach((cat, total) -> System.out.println(cat + ": R$ " + total));
    }
}























/* ============================================================================
 * GUIA DE CONSULTA: DISSECANDO OS PIPELINES DE STREAMS (JAVA 21)
 * ============================================================================
 * * --- TESTE 1: MAIOR VALOR POR CATEGORIA ---
 * Objetivo: Criar um Mapa onde a chave é a categoria e o valor é a transação mais cara.
 * * .collect(Collectors.groupingBy( ... ))
 * -> Agrupa os dados em um Mapa. Recebe dois parâmetros principais:
 * * 1º Parâmetro (A Chave): Transaction::category
 * -> Diz ao Java para criar um "balde" para cada categoria (Alimentação, Lazer, etc.).
 * * 2º Parâmetro (O Coletor Secundário): Collectors.maxBy(Comparator.comparingDouble(Transaction::amount))
 * -> Em vez de guardar a lista inteira de transações no balde, ele analisa as
 * transações que estão entrando naquele balde e guarda APENAS a que tiver o
 * maior valor (amount). Como pode não vir nada, ele empacota em um Optional.
 * * ----------------------------------------------------------------------------
 * * --- TESTE 2: TOTAL GASTO POR CATEGORIA ---
 * Objetivo: Criar um Mapa com a soma total gasta em cada categoria.
 * * .collect(Collectors.groupingBy(Transaction::category, Collectors.summingDouble(Transaction::amount)))
 * -> Semelhante ao Teste 1, cria os baldes por categoria.
 * -> O coletor secundário 'summingDouble' vai pegando o 'amount' de cada transação
 * que cai no balde e vai somando (acumulando). O resultado final do balde vira um Double.
 * * ----------------------------------------------------------------------------
 * * --- TESTE 3: TRANSAÇÕES DO ÚLTIMO MÊS ORDENADAS (DESC) ---
 * Objetivo: Filtrar um mês específico e ordenar da mais cara para a mais barata.
 * * .filter(t -> t.date().getMonthValue() == 4 && t.date().getYear() == 2026)
 * -> Deixa passar na esteira apenas transações de Abril de 2026.
 * * .sorted(Comparator.comparingDouble(Transaction::amount).reversed())
 * -> 'comparingDouble' ensina a comparar pelo valor.
 * -> '.reversed()' inverte a ordem padrão (que é do menor pro maior), garantindo
 * que os maiores valores fiquem no topo da lista.
 * * .toList()
 * -> Empacota as transações que sobraram em uma nova Lista imutável.
 * * ----------------------------------------------------------------------------
 * * --- TESTE 4: TOP 3 CATEGORIAS POR TOTAL GASTO ---
 * Objetivo: Pegar o mapa de somas do Teste 2 e extrair o pódio (Top 3).
 * * teste2.entrySet().stream()
 * -> 'entrySet()' pega as "linhas" do mapa gerado no Teste 2 (pares de Chave=Valor,
 * ex: ["Alimentação"=1030.80]) e as coloca em uma nova Stream.
 * * .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
 * -> Ordena essas linhas olhando para o 'Value' (o total gasto) em ordem decrescente.
 * * .limit(3)
 * -> Operação de corte: deixa passar os 3 primeiros e descarta o resto.
 * * .collect(Collectors.toMap( ... ))
 * -> Remonta o mapa final preservando a ordem. Recebe 4 parâmetros:
 * 1. Map.Entry::getKey   -> Usa o nome da categoria como chave do novo mapa.
 * 2. Map.Entry::getValue -> Usa o valor somado como valor do novo mapa.
 * 3. (v1, v2) -> v1      -> Regra em caso de chaves duplicadas (mantém a primeira).
 * 4. LinkedHashMap::new  -> CRUCIAL: Diz ao Java para usar um LinkedHashMap,
 * que é o tipo de mapa que preserva a ordem do pódio
 * definida pelo '.sorted()'.
 * ============================================================================
 */




