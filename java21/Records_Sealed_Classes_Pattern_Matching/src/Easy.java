/*
Crie sealed interface Result<T> permits Success, Failure.
record Success<T>(T value) e record Failure<T>(String error).
Crie um metodo divide(int a, int b) que retorna Result<Integer>.
Use um switch com pattern matching para processar o resultado e
imprimir mensagens diferentes para cada caso.
Data: 08/05/2026
 */


//Classe inicial
sealed interface Result<T> permits Success, Failure{}

// Passamos o <T> para o Result para manter a tipagem forte
record Success<T>(T value) implements Result<T> {}
record Failure<T>(String error) implements Result<T> {}


//Classe Main
public class Easy {

    //Metodo de divisão fica aqui e RETORNA um Result
    public static Result<Integer> divide(int a, int b) {
        if (b == 0) {
            // Se der erro, retornamos o Failure com a mensagem
            return new Failure<>("Erro: Não é possível dividir por zero.");
        }
        // Se der certo, retornamos o Success com o resultado do cálculo
        return new Success<>(a / b);
    }

    public static void main(String[] args) {
        //Vamos testar as duas situações
        Result<Integer> resultadoSucesso = divide(10, 2);
        Result<Integer> resultadoFalha = divide(10, 0);

        System.out.println("--- Testando o Sucesso ---");
        processarResultado(resultadoSucesso);

        System.out.println("\n--- Testando a Falha ---");
        processarResultado(resultadoFalha);
    }

    //O Switch com Pattern Matching avalia a classe e já extrai as variáveis
    public static void processarResultado(Result<Integer> resultado) {
        switch (resultado) {
            // Se for Success, ele já cria a variável 's' pra gente usar
            case Success<Integer> s -> System.out.println("Operação bem sucedida! Resultado: " + s.value());

            // Se for Failure, ele já cria a variável 'f' pra gente usar
            case Failure<Integer> f -> System.out.println("Falha na operação! Motivo: " + f.error());
        }
    }
}