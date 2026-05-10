/*
Crie BankAccount com owner, balance e accountNumber.
Implemente:
(1) construtor completo,
(2) construtor sem saldo (padrão 0.0),
(3) construtor só com owner (gere o accountNumber automaticamente com UUID).
Use this() para encadear.
Adicione métodos deposit() e withdraw() com validação.
Data: 05/05/2026
*/


import java.util.UUID;

//Classe central da conta
class BankAccount {
    // 1. Atributos em camelCase e privados (Encapsulamento)
    private String owner;
    private double balance;
    private UUID accountNumber;

    // Construtor 1 - Completo
    public BankAccount(String owner, double balance, UUID accountNumber) {
        this.owner = owner;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Construtor 2 - Sem balance (padrão 0.0)
    public BankAccount(String owner, UUID accountNumber) {
        // O this() chama o Construtor 1, passando 0.0 como saldo
        this(owner, 0.0, accountNumber);
    }

    // Construtor 3 - Só com Owner
    public BankAccount(String owner) {
        // O this() chama o Construtor 2, gerando o UUID automaticamente
        this(owner, UUID.randomUUID());
    }

    // Metodo depositar (Com validação)
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Depósito de R$" + amount + " realizado.");
        } else {
            System.out.println("Erro: O valor do depósito deve ser maior que zero.");
        }
    }

    // Metodo retirar (Com validação)
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Erro: O valor do saque deve ser maior que zero.");
        } else if (amount > this.balance) { // Valida se tem saldo suficiente
            System.out.println("Erro: Saldo insuficiente para o saque de R$" + amount);
        } else {
            this.balance -= amount;
            System.out.println("Saque de R$" + amount + " realizado.");
        }
    }

    // Getters para acessar as variáveis privadas na classe Main
    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }
}

// Main
public class Medium {
    public static void main(String[] args) {
        // Testando Construtor 1 (Completo)
        System.out.println("--- Conta 1 (John) ---");
        BankAccount bankAccount1 = new BankAccount("John", 100.0, UUID.randomUUID());
        bankAccount1.deposit(150.2);

        // Teste de saque válido
        bankAccount1.withdraw(50.0);

        // Teste de saque inválido (saldo insuficiente)
        bankAccount1.withdraw(500.0);

        System.out.println("Saldo final do John: " + bankAccount1.getBalance());

        System.out.println("\n--- Conta 2 (Maria) ---");
        // Testando Construtor 3 (Só com Owner - Cria com UUID automático e Saldo 0.0)
        BankAccount bankAccount2 = new BankAccount("Maria");
        System.out.println("Dono da conta: " + bankAccount2.getOwner());
        System.out.println("UUID gerado: " + bankAccount2.getAccountNumber());
        System.out.println("Saldo inicial: " + bankAccount2.getBalance()); // Deve ser 0.0

        // Teste de depósito negativo
        bankAccount2.deposit(-50.0);
    }
}
