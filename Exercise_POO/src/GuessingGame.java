import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//Classe pedida
public class GuessingGame {

    //Variáveis pedidas
    private int numeroSecreto;
    private int tentativas;
    private Scanner scanner;

    //Construtor com scanner
    public GuessingGame() {
        this.scanner = new Scanner(System.in);
        this.tentativas = 0;
    }

    //Métodos
    public void setup(int min, int max) {
        Random random = new Random();
        // A fórmula (max - min + 1) + min garante que o min e o max estejam inclusos
        this.numeroSecreto = random.nextInt((max - min) + 1) + min;
        this.tentativas = 0; // Zera o contador para um novo jogo
        System.out.println("Jogo configurado! Pensei em um número entre " + min + " e " + max + ".");
    }

    //Captura a entrada do usuário garantindo robustez (evita quebra por letras/símbolos).
    public int acceptGuess() {
        int palpite = 0;
        boolean entradaValida = false;

        // O laço continua até o usuário digitar um número inteiro válido
        while (!entradaValida) {
            System.out.print("Digite seu palpite: ");
            try {
                palpite = this.scanner.nextInt(); // Tenta ler o inteiro
                entradaValida = true;             // Se deu certo, a entrada é válida
            } catch (InputMismatchException e) {
                // Tratamento de erro caso o usuário digite texto ao invés de número
                System.out.println("[Erro] Entrada inválida! Por favor, digite apenas números inteiros.");
                this.scanner.nextLine(); // Limpa o buffer contaminado do Scanner
            }
        }
        return palpite;
    }

    //Incrementa o contador de palpites do estado do jogo.
    public void countGuess() {
        this.tentativas++;
    }

    /**
     * Avalia o palpite em relação ao número secreto e dá o feedback ao usuário.
     * Retorna true se o jogo deve acabar (acertou) ou false caso contrário.
     */
    public boolean feedback(int palpite) {
        if (palpite == this.numeroSecreto) {
            System.out.println("\n PARABÉNS! Você acertou!");
            System.out.println("O número secreto era: " + this.numeroSecreto);
            System.out.println("Total de tentativas necessárias: " + this.tentativas);
            return true; // Sinaliza vitória
        } else if (palpite > this.numeroSecreto) {
            System.out.println("-> O número secreto é MENOR que " + palpite + ".\n");
            return false;
        } else {
            System.out.println("-> O número secreto é MAIOR que " + palpite + ".\n");
            return false;
        }
    }

    //Fecha o recurso do Scanner (Boa prática de gerenciamento de memória em Java).
    public void closeGame() {
        this.scanner.close();
    }

    //Metodo principal (Ponto de entrada). É o único metodo estático permitido.
    public static void main(String[] args) {
        //Instanciação do objeto (criando o jogo na memória)
        GuessingGame game = new GuessingGame();

        //Configuração do jogo (intervalo de 1(min) a 100(max)) e fim de jogo.
        game.setup(1, 100);
        boolean fimDeJogo = false;

        //Loop principal do jogo para finalizar e dar feedback
        while (!fimDeJogo) {
            int palpiteAtual = game.acceptGuess(); // Pega palpite
            game.countGuess();                     // Conta a tentativa
            fimDeJogo = game.feedback(palpiteAtual); // Exibe feedback e verifica vitória
        }
        //Fechar leitor
        game.closeGame();
    }
}