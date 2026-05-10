/*
Crie interface Validator<T> com método validate(T value)
retornando boolean. Adicione default method and(Validator<T> other)
que retorna um novo Validator que exige ambos passarem.
Adicione or() e negate(). Crie validadores para String (notBlank,
minLength, maxLength, emailFormat) e componha regras:
notBlank.and(minLength(3)).and(emailFormat).
Data: 07/05/2026

 */


// 1. A Interface Funcional
@FunctionalInterface
interface Validator<T> {

    // O método principal que faz a validação
    boolean validate(T value);

    // Default method AND (E)
    default Validator<T> and(Validator<T> other) {
        // Retorna um novo Validator que checa a regra atual E a regra 'other'
        return (T value) -> this.validate(value) && other.validate(value);
    }

    // Default method OR (OU)
    default Validator<T> or(Validator<T> other) {
        return (T value) -> this.validate(value) || other.validate(value);
    }

    // Default method NEGATE (NÃO)
    default Validator<T> negate() {
        return (T value) -> !this.validate(value);
    }
}

// 2. Classe utilitária com as regras para Strings
class StringValidators {

    // notBlank e emailFormat não dependem de parâmetros externos,
    // então podem ser constantes estáticas
    public static final Validator<String> notBlank = value ->
            value != null && !value.trim().isEmpty();

    public static final Validator<String> emailFormat = value ->
            value != null && value.matches("^[A-Za-z0-9+_.-]+@(.+)$");

    // minLength e maxLength precisam de um número,
    // então são métodos que retornam um Validator
    public static Validator<String> minLength(int min) {
        return value -> value != null && value.length() >= min;
    }

    public static Validator<String> maxLength(int max) {
        return value -> value != null && value.length() <= max;
    }
}

// 3. Classe Principal para Teste
public class Hard {
    public static void main(String[] args) {

        // COMPOSIÇÃO DE REGRAS! (A mágica acontece aqui)
        Validator<String> emailRule = StringValidators.notBlank
                .and(StringValidators.minLength(3))
                .and(StringValidators.emailFormat);

        // Testando a regra composta
        System.out.println("--- Testes de Validação de E-mail ---");

        String email1 = "joao@email.com";
        System.out.println("Testando '" + email1 + "': " + emailRule.validate(email1)); // true

        String email2 = "a@";
        System.out.println("Testando '" + email2 + "': " + emailRule.validate(email2)); // false (falha no emailFormat e no minLength)

        String email3 = "   ";
        System.out.println("Testando '" + email3 + "': " + emailRule.validate(email3)); // false (falha no notBlank)

        // Criando outra regra usando OR e NEGATE apenas para demonstrar
        Validator<String> loginRule = StringValidators.minLength(5)
                .or(StringValidators.emailFormat.negate()); // "Tem que ter pelo menos 5 letras OU NÃO ser um formato de email"
    }
}
