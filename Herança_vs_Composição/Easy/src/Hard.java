/*
Crie um Logger que usa composição de:
Formatter (interface: JsonFormatter, PlainFormatter) e
Output (interface: ConsoleOutput, FileOutput).
O Logger recebe ambos no construtor. Crie um LoggerBuilder
para montar combinações. Mostre como trocar o formatter em
runtime sem mudar o Logger — isso seria impossível com herança.
Data: 06/05/2026
 */

// O Contrato (Ação genérica)
interface Formatter {
    String formatar(String mensagem);
}
// O Contrato 2
interface Output {
    void escrever(String texto);
}

// Peça 1: Formata em JSON
class JsonFormatter implements Formatter {
    @Override
    public String formatar(String mensagem) {
        return "{ \"log\": \"" + mensagem + "\" }";
    }
}

// Peça 2: Formata em texto puro
class PlainFormatter implements Formatter {
    @Override
    public String formatar(String mensagem) {
        return "[LOG] " + mensagem;
    }
}

// Peça 1: Escreve no console
class ConsoleOutput implements Output {
    @Override
    public void escrever(String texto) {
        System.out.println(texto);
    }
}

// Peça 2: Escreveria em um arquivo (vamos simular com um print diferente por enquanto)
class FileOutput implements Output {
    @Override
    public void escrever(String texto) {
        System.out.println("Salvando no arquivo log.txt: " + texto);
    }
}
//Classe que chama as interfaces
class Logger {
    // COMPOSIÇÃO: O Logger "TEM UM" formato e "TEM UMA" saída
    private Formatter formatter;
    private Output output;

    // Construtor recebe as peças
    public Logger(Formatter formatter, Output output) {
        this.formatter = formatter;
        this.output = output;
    }

    // Metodo principal que o usuário vai chamar
    public void log(String mensagem) {
        // 1. Ele pede pro formatter formatar
        String mensagemFormatada = formatter.formatar(mensagem);
        // 2. Ele pede pro output escrever
        output.escrever(mensagemFormatada);
    }

    // COMO TROCAR EM RUNTIME?
    // Basta criar um metodo que substitui a peça atual por uma nova!
    public void setFormatter(Formatter novoFormatter) {
        this.formatter = novoFormatter;
    }
}

class LoggerBuilder {
    // Valores padrão caso o usuário não defina
    private Formatter formatter = new PlainFormatter();
    private Output output = new ConsoleOutput();

    public LoggerBuilder comFormatter(Formatter formatter) {
        this.formatter = formatter;
        return this; // Retorna o próprio builder para encadear as chamadas
    }

    public LoggerBuilder comOutput(Output output) {
        this.output = output;
        return this;
    }

    public Logger construir() {
        return new Logger(formatter, output);
    }
}

//Classe Main
public class Hard {
    public static void main(String[] args) {
        System.out.println("=== Instanciando o Logger via Builder ===");

        // Usando o Builder para montar um Logger que cospe JSON no Console
        Logger logger = new LoggerBuilder()
                .comFormatter(new JsonFormatter())
                .comOutput(new ConsoleOutput())
                .construir();

        // 1. O sistema inicia e gera um log em JSON
        logger.log("Sistema de controle iniciado no servidor.");
        logger.log("Conectando ao banco de dados PostgreSQL...");

        System.out.println("\n=== Trocando o Formato em Runtime ===");

        // 2. Aqui acontece a mágica. O sistema continua rodando (runtime),
        // o objeto Logger é o mesmo na memória, mas nós trocamos uma das "peças" dele.
        logger.setFormatter(new PlainFormatter());

        // 3. Os novos logs saem com o formato atualizado
        logger.log("Conexão estabelecida com sucesso.");
        logger.log("Iniciando rotinas de processamento híbrido.");
    }
}