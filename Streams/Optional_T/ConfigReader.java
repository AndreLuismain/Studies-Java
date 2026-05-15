/*
Crie um ConfigReader que busca uma propriedade em:
(1) variáveis de ambiente, (2) arquivo de properties local,
(3) valores padrão hard-coded. Cada fonte retorna Optional<String>.
Use optional.or(() -> nextSource.find(key)) para encadear. Crie também
um getRequired(key) que lança MissingConfigException (customizada)
se nenhuma fonte tiver o valor.

Data: 14/05/2026
 */



import java.util.Map;
import java.util.Optional;

// Exceção customizada para configurações ausentes
class MissingConfigException extends RuntimeException {
    public MissingConfigException(String key) {
        super("Configuração obrigatória não encontrada em nenhuma fonte: '" + key + "'");
    }
}

public class ConfigReader {

    // Simulação de um arquivo de properties local (em disco)
    private final Map<String, String> fileProperties = Map.of(
            "database.url", "jdbc:postgresql://localhost:5432/local_db",
            "app.timeout", "3000"
    );

    // Simulação de valores padrão hard-coded no sistema
    private final Map<String, String> defaultProperties = Map.of(
            "app.timeout", "5000",
            "app.theme", "dark"
    );

    // --- 1. FONTE A: Variáveis de Ambiente ---
    private Optional<String> getFromEnv(String key) {
        // System.getenv pode retornar null se a variável do SO não existir
        System.out.println("[Log] Consultando Variáveis de Ambiente para: " + key);
        return Optional.ofNullable(System.getenv(key));
    }

    // --- 2. FONTE B: Arquivo Local ---
    private Optional<String> getFromFile(String key) {
        System.out.println("[Log] Consultando Arquivo Local para: " + key);
        return Optional.ofNullable(fileProperties.get(key));
    }

    // --- 3. FONTE C: Padrões Hard-coded ---
    private Optional<String> getFromDefaults(String key) {
        System.out.println("[Log] Consultando Valores Padrão para: " + key);
        return Optional.ofNullable(defaultProperties.get(key));
    }

    /**
     * O PIPELINE DE FALLBACK COM .or()
     * Tenta o Env -> Se vazio, tenta Arquivo -> Se vazio, tenta Padrões.
     */
    public Optional<String> getConfig(String key) {
        return getFromEnv(key)
                .or(() -> getFromFile(key))
                .or(() -> getFromDefaults(key));
    }

    /**
     * CONTRATO ESTRITO (FAIL-FAST)
     * Utiliza a busca encadeada e lança erro customizado se nenhuma fonte tiver o dado.
     */
    public String getRequired(String key) {
        return getConfig(key)
                .orElseThrow(() -> new MissingConfigException(key));
    }

    // --- Testando a Mágica em Ação ---
    public static void main(String[] args) {
        ConfigReader reader = new ConfigReader();

        System.out.println("--- Teste 1: Propriedade presente no Arquivo ---");
        // Nota: Como 'database.url' não existe no Env do seu SO, ele fará o fallback pro arquivo.
        String dbUrl = reader.getRequired("database.url");
        System.out.println("Resultado: " + dbUrl);

        System.out.println("\n--- Teste 2: Propriedade presente apenas no Default ---");
        // Falha no Env, Falha no Arquivo, Encontra no Default.
        String theme = reader.getRequired("app.theme");
        System.out.println("Resultado: " + theme);

        System.out.println("\n--- Teste 3: Propriedade Inexistente (Deve lançar erro) ---");
        try {
            reader.getRequired("chave.inexistente");
        } catch (MissingConfigException e) {
            System.err.println("Erro capturado com sucesso: " + e.getMessage());
        }
    }
}