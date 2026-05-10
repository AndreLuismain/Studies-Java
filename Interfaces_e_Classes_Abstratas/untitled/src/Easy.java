/*
Crie interface Drawable com metodo draw().
Implemente em Circle, Rectangle e Triangle.
Crie um metodo utilitário drawAll(List<Drawable> shapes).
Agora adicione Dog implements Drawable —
perceba que Dog não precisa ser Shape para ser Drawable.
Isso é o poder das interfaces.
 Data:07/05/2026
 */


import java.util.List;
import java.util.Arrays;

//Interface pedida
interface Drawable {
    void draw();
}
//Implemento 1 com Override
class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}
//Implemento 2 com Override
class Rectangle implements Drawable {
    @Override
    public void  draw() {
    System.out.println("Rectangle");
  }
}
//implemento 3 com Override
class Triangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}
//Teste do exercicio com Override
class Dog implements Drawable {
    @Override
    public void draw() {
        System.out.println("Dog");
    }
}

//Classe main
public class Easy {
    // O metodo utilitário que faltava!
    // Ele recebe uma lista de qualquer objeto que seja "Drawable"
    public static void drawAll(List<Drawable> shapes) {
        for (Drawable shape : shapes) {
            shape.draw();
        }
    }

    public static void main(String[] args) {
        // Criamos uma lista contendo todas as nossas instâncias
        List<Drawable> elementsToDraw = Arrays.asList(
                new Circle(),
                new Rectangle(),
                new Triangle(),
                new Dog()
        );

        // Chamamos o método utilitário uma única vez
        System.out.println("--- Iniciando o desenho de todos os itens ---");
        drawAll(elementsToDraw);
    }
}
