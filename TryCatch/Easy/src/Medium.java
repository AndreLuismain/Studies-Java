/*
Crie class DatabaseConnection implements AutoCloseable com:
connect(), executeQuery(String sql), close() (que imprime "Connection closed").
Simule uma conexão real com estado. Use no try-with-resources. Adicione um
TransactionScope implements AutoCloseable que faz commit no close() ou rollback
se houve exceção — use um flag booleano.

Data: 16/05/2026
 */


class DatabaseConnection implements AutoCloseable {
    private boolean isOpen = false;

    public void connect() {
        isOpen = true;
        System.out.println("[DatabaseConnection] Conectado ao banco de dados.");
    }

    public void executeQuery(String sql) {
        if (!isOpen) {
            throw new IllegalStateException("Erro: A conexão está fechada!");
        }
        System.out.println("[DatabaseConnection] Executando: " + sql);
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println("[DatabaseConnection] Connection closed.");
    }
}
class TransactionScope implements AutoCloseable {
    private DatabaseConnection connection;
    private boolean sucesso = false; // Flag para controlar o estado da transação

    public TransactionScope(DatabaseConnection connection) {
        this.connection = connection;
        System.out.println("[TransactionScope] Transação iniciada.");
    }

    // Deve ser chamado explicitamente no final do bloco try, antes de fechar
    public void commit() {
        this.sucesso = true;
    }

    @Override
    public void close() {
        if (sucesso) {
            System.out.println("[TransactionScope] Fazendo COMMIT da transação...");
        } else {
            System.out.println("[TransactionScope] Exceção detectada ou commit ausente. Fazendo ROLLBACK da transação...");
        }
        System.out.println("[TransactionScope] Transação finalizada.");
    }
}

public class Medium {
    public static void main(String[] args) {

        System.out.println("=== TESTE 1: Transação com Sucesso ===");
        // Aninhamos os try-with-resources. A conexão é fechada DEPOIS da transação.
        try (DatabaseConnection db = new DatabaseConnection()) {
            db.connect();

            try (TransactionScope tx = new TransactionScope(db)) {
                db.executeQuery("INSERT INTO usuarios (nome) VALUES ('Alice')");
                db.executeQuery("UPDATE saldo SET valor = valor - 100 WHERE id = 1");

                // Se chegou até aqui sem erros, marcamos a transação para commit
                tx.commit();
            } // O java chama tx.close() aqui

        } // O java chama db.close() aqui
        catch (Exception e) {
            System.err.println("Erro no Teste 1: " + e.getMessage());
        }


        System.out.println("\n=== TESTE 2: Transação com Falha (Rollback) ===");
        try (DatabaseConnection db = new DatabaseConnection()) {
            db.connect();

            try (TransactionScope tx = new TransactionScope(db)) {
                db.executeQuery("INSERT INTO usuarios (nome) VALUES ('Bob')");

                // Simulando um erro (ex: banco caiu, validação falhou, etc)
                boolean deuErro = true;
                if (deuErro) {
                    throw new RuntimeException("Simulação de erro no meio da transação!");
                }

                db.executeQuery("UPDATE saldo SET valor = valor + 100 WHERE id = 2");

                tx.commit(); // Essa linha NUNCA será alcançada devido à exceção
            }

        } catch (Exception e) {
            System.err.println("Erro capturado no Teste 2: " + e.getMessage());
        }
    }
}
