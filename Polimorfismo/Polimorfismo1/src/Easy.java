/*
Crie Animal com speak() retornando "...". Subclasses: Dog ("Au!"),
Cat ("Miau"), Duck ("Quack"). No main: crie List<Animal> animals
com instâncias mistas. Itere chamando speak(). Adicione um novo animal
sem alterar o código de iteração — essa é a essência do polimorfismo.
Data:07/05/2026
 */


import java.util.ArrayList;
import java.util.List;

// Classe Pai
class Animal {
    String name; // Declarado apenas AQUI. Todos os filhos herdam isso!

    // Trocamos 'void' por 'String' para RETORNAR o texto
    String speak() {
        return "...";
    }
}

class Dog extends Animal {
    @Override
    String speak() {
        return "Au!";
    }
}

class Cat extends Animal {
    @Override
    String speak() {
        return "Miau!";
    }
}

class Duck extends Animal {
    @Override
    String speak() {
        return "Quack!"; // Corrigido para Quack
    }
}

// 🌟 O NOVO ANIMAL: Criamos uma Vaca para testar o polimorfismo
class Cow extends Animal {
    @Override
    String speak() {
        return "Muuu!";
    }
}

// Classe MAIN
public class Easy {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Duck());

        // Adicionamos a vaca na lista
        animals.add(new Cow());

        System.out.println("--- Os animais estão falando ---");

        //POLIMORFISMO:
        for (Animal animal : animals) {
            // Como o metodo retorna o som, nós usamos o println aqui
            System.out.println(animal.speak());
        }
    }
}
