/*
Crie abstract class Shape com: field color,
metodo abstrato calculateArea(), e metodo concreto describe()
que usa calculateArea() internamente (template method).
Implemente Circle, Rectangle e Triangle.
Perceba que describe() funciona para todos sem override —
é a vantagem sobre interface.
Data: 07/05/2026
 */


// Classe Abstrata
abstract class Shape {
    String color;

    // Construtor para garantir que toda forma tenha uma cor
    public Shape(String color) {
        this.color = color;
    }

    // Metodo abstrato: As subclasses precisam ensinar COMO calcular a área
    abstract double calculateArea();

    // Método concreto (Template Method)
    public void describe() {
        System.out.println("Sou uma forma da cor: " + this.color);
        // Aqui está o Template Method em ação:
        // Ele chama o calculateArea() interno, que vai se comportar
        // de acordo com a classe filha que estiver sendo instanciada.
        System.out.println("A minha área total é: " + calculateArea());
        System.out.println("-----------------------------------");
    }
}

// Implemento 1
class Circle1 extends Shape {
    double radius;

    public Circle1(String color, double radius) {
        super(color); // Repassa a cor para a classe pai (Shape)
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * (radius * radius);
    }
}

// Implemento 2
class Rectangle1 extends Shape {
    double width;
    double height;

    public Rectangle1(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return width * height;
    }
}

// Implemento 3
class Triangle1 extends Shape {
    double base;
    double height;

    public Triangle1(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return (base * height) / 2.0;
    }
}

// Classe MAIN
public class Medium {
    public static void main(String[] args) {
        // Instanciamos as formas passando suas cores e medidas
        Shape circle = new Circle1("Vermelho", 5.0);
        Shape rectangle = new Rectangle1("Azul", 4.0, 5.0);
        Shape triangle = new Triangle1("Verde", 3.0, 6.0);

        // Chamamos o método describe() para todos.
        circle.describe();
        rectangle.describe();
        triangle.describe();
    }
}