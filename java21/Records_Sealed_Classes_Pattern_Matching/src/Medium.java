/*
Crie uma hierarquia sealed: Shape com Circle(double radius),
Rectangle(double w, double h), Triangle(double base, double height).
Escreva um metodo describeShape(Shape s) usando switch com pattern
matching que retorna uma String com nome e área. O compilador deve
garantir que todos os casos são cobertos — tente remover um e veja o erro.
Data: 08/05/2026
 */


sealed interface Shape permits Circle, Rectangle, Triangle {}

//Records para implementar a classe selada
record Circle(double radius) implements Shape {}
record Rectangle(double w, double h) implements Shape {}
record Triangle(double base, double height) implements Shape {}

//classe MAIN
public class Medium {

    //Metodo retornando uma String e usa o switch expression
    public static String describeShape(Shape s) {
        // O return vai direto no switch
        return switch (s) {
            case Circle c -> "Círculo - Área: " + (Math.PI * Math.pow(c.radius(), 2));
            case Rectangle r -> "Retângulo - Área: " + (r.w() * r.h());
            case Triangle t -> "Triângulo - Área: " + ((t.base() * t.height()) / 2.0);
        };
    }
    //Classe main
    public static void main(String[] args) {

        //Dando as medidas
        Shape c = new Circle(5.0);
        Shape r = new Rectangle(4.0, 6.0);
        Shape t = new Triangle(3.0, 8.0);

        //Mostrando os resultados
        System.out.println(describeShape(c));
        System.out.println(describeShape(r));
        System.out.println(describeShape(t));
    }
}