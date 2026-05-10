/*
Crie uma classe Pizza imutável (todos os fields final) com:
size (obrigatório), crust, sauce, List<String> toppings (opcionais).
Implemente uma inner class Pizza.
Builder com métodos encadeáveis (withCrust(), addTopping(), etc.) que termina em build().
No main, crie duas pizzas bem diferentes sem ambiguidade.
Data:05/05/2026
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pizza {
    // 1. Campos final (Garante a Imutabilidade exigida)
    private final String size;
    private final String crust;
    private final String sauce;
    private final List<String> toppings;

    // 2. Construtor privado (Apenas o Builder pode chamar)
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        // Criamos uma cópia imutável da lista para garantir 100% de imutabilidade
        this.toppings = Collections.unmodifiableList(new ArrayList<>(builder.toppings));
    }

    // Getters para ler os dados da Pizza (Não criamos Setters para manter imutável)
    public String getSize() { return size; }
    public String getCrust() { return crust; }
    public String getSauce() { return sauce; }
    public List<String> getToppings() { return toppings; }

    // Metodo apenas para facilitar a impressão no console
    @Override
    public String toString() {
        return "Pizza [" + size + "] - Borda: " + crust + " | Molho: " + sauce + " | Ingredientes: " + toppings;
    }

    // 3. Inner Class estática Builder
    public static class Builder {
        // Obrigatório (final no Builder)
        private final String size;

        // Opcionais (Podemos definir valores padrão)
        private String crust = "Fina padrão";
        private String sauce = "Tomate tradicional";
        private List<String> toppings = new ArrayList<>();

        // O Construtor do Builder exige o que for OBRIGATÓRIO
        public Builder(String size) {
            this.size = size;
        }

        // Métodos encadeáveis: configuram a variável e retornam 'this'
        public Builder withCrust(String crust) {
            this.crust = crust;
            return this; // Retorna o próprio builder para permitir o encadeamento
        }

        public Builder withSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        // 4. O metodo final que gera a Pizza de fato
        public Pizza build() {
            return new Pizza(this); // Passa o Builder configurado para a Pizza
        }
    }
}

// 5. Main (Testando o código)
class Hard {
    public static void main(String[] args) {

        // Criando a Pizza 1: Simples, usando alguns valores padrão do Builder
        Pizza pizzaCalabresa = new Pizza.Builder("Grande")
                .withCrust("Recheada com Catupiry")
                .addTopping("Calabresa")
                .addTopping("Cebola")
                .build(); // <- O build() encerra a corrente e retorna a Pizza

        // Criando a Pizza 2: Bem diferente e complexa
        Pizza pizzaVegana = new Pizza.Builder("Pequena")
                .withCrust("Massa Integral")
                .withSauce("Molho de Pesto")
                .addTopping("Tomate Cereja")
                .addTopping("Rúcula")
                .addTopping("Azeitona Preta")
                .addTopping("Cogumelos")
                .build();

        // Imprimindo os resultados
        System.out.println(pizzaCalabresa);
        System.out.println(pizzaVegana);

        // Se você tentar fazer pizzaCalabresa.getToppings().add("Extra");
        // O Java lançará um erro, provando que a classe é perfeitamente imutável!
    }
}
