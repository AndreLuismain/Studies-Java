/*
Crie uma classe Calculator com calculate()
sobrecarregado para: (1) int + int, (2) double + double,
(3) String + String (concatenação), (4) List<Integer> (soma todos).
Agora perceba: itens 1 e 2 poderiam ser um só com generics.
Refatore usando <T extends Number> e discuta a diferença no comentário.
 Data: 07/05/2026
 */


import java.util.Arrays;
import java.util.List;

class Calculator{
    int calculate(int a, int b){
        return a+b;
    }
    double calculate(double a, double b){
        return a+b;
    }
    String  calculate(String  a, String  b){
        return a+b;
    }
    int calculate(List<Integer> numbers) {
        int sum = 0;
        // Se a lista for nula, evitamos um erro e retornamos 0
        if (numbers == null) {
            return 0;
        }

        for (Integer num : numbers) {
            // Verifica se o número não é nulo antes de somar (boa prática)
            if (num != null) {
                sum += num;
            }
        }
        return sum;
    }
}
//Classe com generics
class Calculator1 {

    /*
     * ---------------------------------------------------------
     * DISCUSSÃO: SOBRECARGA vs GENERICS (<T extends Number>)
     * ---------------------------------------------------------
     *
     * VANTAGENS:
     * 1. Menos código: Reduzimos dois métodos (int e double) a um só.
     * 2. Flexibilidade: Agora essa calculadora aceita Float, Long e Short automaticamente,
     *    coisa que a versão anterior não fazia!
     *
     * DESVANTAGENS / DIFERENÇAS:
     * 1. Operador '+': A classe 'Number' é um Objeto. O Java não permite fazer 'a + b'
     *    com objetos. Por isso, somos obrigados a converter tudo para o maior tipo
     *    primitivo comum usando 'doubleValue()'.
     * 2. Perda de Precisão no Retorno: Se você passar dois 'int' (ex: 10 e 5), o
     *    retorno será forçado para 'double' (15.0), pois o metodo precisa de um tipo
     *    de retorno genérico que comporte qualquer soma.
     * 3. Autoboxing: Se você passar um 'int' primitivo, o Java gastará um pouquinho de
     *    memória extra para "embrulhar" esse int dentro de um objeto 'Integer' para
     *    satisfazer o 'T'.
     */
    public <T extends Number> double calculate(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // 3. Sobrecarga para String + String (Continua igual)
    public String calculate(String a, String b) {
        return a + b;
    }

    // 4. Sobrecarga para List<Integer> (Continua igual)
    public int calculate(List<Integer> numbers) {
        int sum = 0;
        if (numbers == null) return 0;

        for (Integer num : numbers) {
            if (num != null) sum += num;
        }
        return sum;
    }
}


public class Medium {
    public static void main(String[] args) {
        Calculator1 calc = new Calculator1(); //Removendo o adicionando '1' ao Claculator altera qual vai usar

        System.out.println("--- Testando a Sobrecarga (Overload) ---");

        // O Java sabe exatamente qual metodo chamar com base no que enviamos!

        // Chama o metodo 1 (int)
        System.out.println("10 + 5 = " + calc.calculate(10, 5));

        // Chama o metodo 2 (double)
        System.out.println("2.5 + 3.5 = " + calc.calculate(2.5, 3.5));

        // Chama o metodo 3 (String)
        System.out.println("Textos: " + calc.calculate("Polimorfismo ", "é legal!"));

        // Chama o metodo 4 (Lista)
        List<Integer> minhaLista = Arrays.asList(10, 20, 30, 40);
        System.out.println("Soma da Lista: " + calc.calculate(minhaLista));
    }
}
