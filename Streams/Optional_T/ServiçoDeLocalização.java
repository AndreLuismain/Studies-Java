/*
Você tem: Optional<User> findUser(id), Optional<Address> getAddress(user),
Optional<String> getCity(address). Escreva código para obter a cidade do
usuário de forma encadeada sem nenhum isPresent/get explícito.
Use flatMap para os Optionals aninhados e map para transformações simples.
Termine com orElse("Cidade desconhecida").

Data: 14/05/2026
 */


import java.util.Optional;

// Modelagem de exemplo
record User(int id, String name) {}
record Address(String street, String city) {}

class ServiçoDeLocalização {
    // --- Métodos Simulados que retornam Optional ---
    public Optional<User> findUser(int id) {
        // Simula encontrar o usuário
        return id == 1 ? Optional.of(new User(1, "Ana")) : Optional.empty();
    }

    public Optional<Address> getAddress(User user) {
        // Simula encontrar o endereço do usuário
        return user.name().equals("Ana") ?
                Optional.of(new Address("Av. Central", "São Paulo")) : Optional.empty();
    }

    public Optional<String> getCity(Address address) {
        // Simula extrair a cidade (supondo que a cidade pudesse ser nula/vazia no banco)
        return Optional.ofNullable(address.city());
    }

    // --- O CÓDIGO CONSUMIDOR (A SOLUÇÃO) ---
    public String obterCidadeDoUsuario(int id) {
        return findUser(id)
                // 1. Desempacota o User e chama getAddress (que devolve Optional<Address>)
                // Usamos flatMap para evitar Optional<Optional<Address>>
                .flatMap(this::getAddress) // Atalho para: user -> getAddress(user)

                // 2. Desempacota o Address e chama getCity (que devolve Optional<String>)
                // Usamos flatMap novamente pelo mesmo motivo
                .flatMap(this::getCity)    // Atalho para: address -> getCity(address)

                // 3. Se em QUALQUER etapa anterior a caixa se esvaziou, ele cai direto aqui
                .orElse("Cidade desconhecida");
    }

    // --- Testando na Prática ---
    public static void main(String[] args) {
        ServiçoDeLocalização serviço = new ServiçoDeLocalização();

        // Cenário de Sucesso (ID 1 existe, tem endereço e tem cidade)
        System.out.println("Resultado ID 1: " + serviço.obterCidadeDoUsuario(1));
        // Saída: Resultado ID 1: São Paulo

        // Cenário de Falha (ID 99 não existe, o fluxo é interrompido no primeiro passo)
        System.out.println("Resultado ID 99: " + serviço.obterCidadeDoUsuario(99));
        // Saída: Resultado ID 99: Cidade desconhecida
    }
}
