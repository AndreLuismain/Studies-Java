/*
Você tem findUserById(int id) que retorna null quando não encontra.
Refatore para retornar Optional<User>. No código que consome:
compare as três formas:
(1) isPresent() + get() — ruim,
(2) orElse(defaultUser),
(3) orElseThrow(() -> new UserNotFoundException(id)).
Documente quando usar cada uma.

Data: 14/05/2026
 */


import java.util.Optional;

// Modelagem limpa com Record
record User1(int id, String name) {}

// Exceção customizada de negócio
class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User not found with ID: " + id);
    }
}

class UserService {
    // Simulação de um metodo legado ou chamada de banco que retorna null
    private User findUserByIdLegacy(int id) {
        if (id == 1) {
            return new User(1, "Ana Silva");
        }
        return null; // Risco de NullPointerException aqui
    }
    //Transformamos o provável null em um contrato seguro usando Optional.ofNullable()
    public Optional<User> findUserById(int id) {
        User user = findUserByIdLegacy(id);

        // Se 'user' for null, devolve Optional.empty().
        // Se tiver objeto, devolve Optional.of(user).
        return Optional.ofNullable(user);
    }
}

public class Easy1 {
    public static void main(String[] args) {
        UserService service = new UserService();
        int idBuscado = 99; // ID que sabemos que retornará vazio

        // O serviço nos entrega a caixa blindada
        Optional<User> userOpt = service.findUserById(idBuscado);

        /* ====================================================================
         * ABORDAGEM 1: isPresent() + get() ❌ (RUIM / ANTI-PADRÃO)
         * ====================================================================
         * Análise: Por que os seniores torcem o nariz para isso?
         * Porque é puramente imperativo. Você apenas trocou "if (u != null)"
         * por "if (u.isPresent())", gerando verbosidade sem aproveitar o
         * verdadeiro poder funcional da API.
         */
        System.out.println("--- 1. Abordagem: isPresent() + get() ---");
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            System.out.println("Sucesso: " + u.name());
        } else {
            System.out.println("Falha: Usuário não encontrado (Tratado com if/else manual).");
        }


        /* ====================================================================
         * ABORDAGEM 2: orElse(defaultUser) ✔️ (SEGURO E DIRETO)
         * ====================================================================
         * Análise: Excelente para cenários onde o sistema pode continuar
         * funcionando graciosamente com um valor de "fallback" (padrão).
         * Desempacota em uma única linha limpa.
         */
        System.out.println("\n--- 2. Abordagem: orElse(defaultUser) ---");
        User defaultUser = new User(0, "Usuário Anônimo");

        User usuarioResolvido = userOpt.orElse(defaultUser);

        System.out.println("Usuário Ativo no Sistema: " + usuarioResolvido.name());


        /* ====================================================================
         * ABORDAGEM 3: orElseThrow(...) 🚀 (PADRÃO SÊNIOR / FAIL-FAST)
         * ====================================================================
         * Análise: A favorita para regras de negócio estritas. Se a ausência
         * do usuário impede o fluxo (ex: finalizar uma compra ou responder
         * um endpoint REST com erro 404), interrompemos imediatamente
         * lançando uma exceção de negócio clara.
         */
        System.out.println("\n--- 3. Abordagem: orElseThrow ---");
        try {
            User usuarioEstrito = userOpt.orElseThrow(
                    () -> new UserNotFoundException(idBuscado)
            );
            System.out.println("Sucesso: " + usuarioEstrito.name());
        } catch (UserNotFoundException e) {
            // Capturado aqui apenas para demonstrar a saída no console
            System.out.println("Fluxo interrompido com sucesso -> " + e.getMessage());
        }
    }
}