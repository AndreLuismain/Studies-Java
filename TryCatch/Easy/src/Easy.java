/*
Leia um arquivo de texto de duas formas:
(1) com try-catch-finally manual, chamando reader.close() no finally,
(2) com try-with-resources. Observe como o código fica mais limpo.
Em seguida, abra dois recursos no mesmo try (try (FileReader r = ...; BufferedReader br = ...))
e perceba que ambos são fechados na ordem inversa de abertura.

Data: 16/05/2026
 */

import java.io.*;

public class Easy {
    public static void main(String[] args) {
        String caminhoDoArquivo = "Arquivo1.txt";

        //Metodo 1 com try-catch-finally
        // Declaramos a variável fora do bloco para o finally conseguir enxergá-la
        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new FileReader("mensagem.txt"));
            String linha;

            System.out.println("--- Lendo mensagem.txt (Modo Manual) ---");
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());

        } finally {
            System.out.println("-> Entrando no finally para fechar recursos...");
            // Precisamos garantir que não estamos chamando .close() em algo nulo
            if (leitor != null) {
                try {
                    leitor.close(); // Fecha o arquivo
                    System.out.println("-> BufferedReader fechado com sucesso.");
                } catch (IOException e) {
                    // Trata um possível erro DURANTE o fechamento do arquivo
                    System.err.println("Erro ao tentar fechar o arquivo: " + e.getMessage());
                }
            }
        }

        //Metodo 2 com try-with-resources
        System.out.println("--- Lendo Arquivo1.txt (Try-with-resources) ---");

        // Abrimos os dois recursos separados por ponto e vírgula (;).
        // 1º: Abrimos o FileReader (fr)
        // 2º: Abrimos o BufferedReader (br), que depende do 'fr'
        try (
                FileReader fr = new FileReader("Arquivo1.txt");
                BufferedReader br = new BufferedReader(fr)
        ) {

            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }

            System.out.println("-> Fim da leitura. O Java assumirá o controle do fechamento agora.");

        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }

        /*
         * BASTIDORES DO JAVA:
         * Ao sair do bloco try, o Java faz o seguinte silenciosamente:
         * 1. Chama br.close()
         * 2. Chama fr.close()
         * Tudo isso com tratamento de erro embutido, sem poluir seu código!
         */
    }
}
