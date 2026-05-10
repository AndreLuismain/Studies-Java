/*
Crie um record Money(BigDecimal amount, String currency)
com: (1) add(Money other) — valide que currencies são iguais,
retorne novo Money, (2) multiply(BigDecimal factor), (3) isGreaterThan(Money other).
Adicione um construtor compacto que rejeita amount negativo.
Demonstre uma série de operações encadeadas num cenário de carrinho de compras.
Data: 05/05/2026
 */


import java.math.BigDecimal;

record Money(BigDecimal amount, String currency) {

    //construtor compacto
    public Money {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("quantia Negativa!");
        }
    }
    //metodo para soma
    public Money add(Money m) {
        if (this.currency.equals(m.currency())) {
            BigDecimal novaSoma = this.amount.add(m.amount());
            return new Money(novaSoma, this.currency);
        }  else {
            throw new IllegalArgumentException("Erro: As moedas são diferentes!");
        }
    }
    //metodo para multiplicação
    public Money multiply(BigDecimal factor) {
        BigDecimal novamultiplicacao = this.amount.multiply(factor);
        return new Money(novamultiplicacao, this.currency);
    }
    //Metodo para testar qual é maior
    public boolean isGreaterThan(Money other) {
        if (this.currency.equals(other.currency())) {
            return this.amount.compareTo(other.amount()) > 0;
        } else {
            throw new IllegalArgumentException("Erro: As moedas diferentes!");
        }
    }
}
//Classe Main
class Hard {
    public static void main(String[] args) {
        //Moedas testes
        Money moeda1 = new Money(new BigDecimal("10.50"), "BRL");
        Money moeda2 = new Money(new BigDecimal("11.0"), "BRL");
        Money moeda3 = new Money(new BigDecimal("9.50"), "BRL");

        //Mostrando as moedas ao usuário
        System.out.println("\nTodas as Moedas: ");
        System.out.println(moeda1);
        System.out.println(moeda2);
        System.out.println(moeda3);

        //Testar a Soma
        Money resultadoSoma = moeda1.add(moeda2);
        System.out.println("\nResultados da SOMA entre moedas 1 e 2: " + resultadoSoma);

        //Testar o multiply
        Money resultadoMulty = moeda1.multiply(moeda2.amount());
        System.out.println("\nResultados da MULTIPLICAÇÃO entre moedas 1 e 2: " + resultadoMulty);

        //testar o isGreaterThan
        Boolean resultadoIsGreaterThan = moeda1.isGreaterThan(moeda2);
        Boolean resultadoIsGreaterThan2 = moeda1.isGreaterThan(moeda3);

        //Mostrando os Resultados isGreater
        System.out.println("\nResultados da COMPARAÇÃO entre moeda 1 e 2:" + resultadoIsGreaterThan);
        System.out.println("Resultados da COMPARAÇÃO entre moeda 1 e 3:" + resultadoIsGreaterThan2);
    }
}
