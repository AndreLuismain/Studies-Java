/*
Taske:
Crie Animal com name e metodo speak().
Crie Dog e Cat estendendo Animal.
Em seguida, tente criar RobotDog extends Dog
e perceba o problema: robô realmente 'é um' Dog?
Documente no código por que essa herança não faz sentido.

Documentação:
Um RobotDog NÃO é um Cachorro no sentido biológico, logo, não é um Animal.
A herança falha no teste "É UM" (Is-A). Se RobotDog estende Dog (que estende Animal),
ele herda características orgânicas que um robô não possui. A melhor forma de representar
isso no mundo real seria usar Interfaces (ex: uma interface 'Falar' ou 'AcaoCanina'),
permitindo que o robô lata sem precisar pertencer à árvore genealógica de 'Animal'.

Data: 06/05/2026
 */

class Animal {
    protected String name;

    //Construtor
    public Animal(String name) {
        this.name = name;
    }
    //Metodo falar
    public void speak() {
        System.out.println("I'm a Animal");
    }
}
class Dog extends Animal {
    private String name;

    //Construtor subclasse Dog
    public Dog(String name) {
        super(name);
    }
    @Override
    public void speak() {
        System.out.println("Au Au");
    }
}
class Cat extends Animal {
    private String name;

    //Construtor subclasse Cat
    public Cat(String name) {
        super(name);
    }
    @Override
    public void speak() {
        System.out.println("Miau Miau");
    }
}
//Tentativa de criar
class RobotDog extends Dog {
    private String name;
    public RobotDog(String name) {
        super(name);
    }
    @Override
    public void speak() {
        System.out.println("Robozin travadin");
    }
}

//CLass Main
public class Easy {
    public static void main(String[] args) {
        Animal gato1 = new Cat("Gatin1");
        Animal cachorro1 = new Dog("Cachorrin1");
        Animal roboDog1 = new RobotDog("RoboDog1");
        gato1.speak();
        cachorro1.speak();
        roboDog1.speak();
        System.out.println(gato1.name);
        System.out.println(cachorro1.name);
        System.out.println(roboDog1.name);
    }
}

