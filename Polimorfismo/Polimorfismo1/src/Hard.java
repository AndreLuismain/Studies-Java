/*
Crie um sistema de renderização: abstract class Shape com
metodo abstrato render(Canvas canvas). Canvas é uma classe
com métodos drawCircle(), drawRect() etc. Implemente Circle,
Rectangle, Polygon. Crie SceneRenderer que recebe List<Shape>
e chama render() em cada um — sem nenhum instanceof ou cast.
Compare com uma versão "ruim" com if-else e documente o porquê
do polimorfismo ganhar.
Data: 07/05/2026
 */


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

//Classe canva para dizer como desenhar usando a classe pronta canva
class Canvas {
    private Graphics g;

    public Canvas(Graphics g) {
        this.g = g;
    }

    public void drawCircle(int centerX, int centerY, int radius) {
        if (g == null) return; // Proteção caso seja teste no console
        int diameter = radius * 2;
        g.drawOval(centerX - radius, centerY - radius, diameter, diameter);
        System.out.println("Desenhando Círculo no Canvas");
    }

    public void drawRect(int x, int y, int width, int height) {
        if (g == null) return;
        g.drawRect(x, y, width, height); // Usando a fórmula certa do Java!
        System.out.println("Desenhando Retângulo no Canvas");
    }

    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        if (g == null) return;
        g.drawPolygon(xPoints, yPoints, nPoints); // Fórmula certa para Polígonos!
        System.out.println("Desenhando Polígono no Canvas");
    }
}

//Classe Abstrata Shape (Define o contrato)
abstract class Shape {
    public abstract void render(Canvas canvas);
}

//Forma Concreta 1 (Elas conhecem seus dados e repassam para o Canvas)
class Circle extends Shape {
    int x, y, radius;
    public Circle(int x, int y, int radius) { this.x = x; this.y = y; this.radius = radius; }

    @Override
    public void render(Canvas canvas) {
        canvas.drawCircle(x, y, radius);
    }
}
//Forma Concreta 2 (Elas conhecem seus dados e repassam para o Canvas)
class Rectangle extends Shape {
    int x, y, width, height;
    public Rectangle(int x, int y, int width, int height) { this.x = x; this.y = y; this.width = width; this.height = height; }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(x, y, width, height);
    }
}
//Forma Concreta 3 (Elas conhecem seus dados e repassam para o Canvas)
class Polygon extends Shape {
    int[] xPoints, yPoints;
    int nPoints;
    public Polygon(int[] xPoints, int[] yPoints, int nPoints) { this.xPoints = xPoints; this.yPoints = yPoints; this.nPoints = nPoints; }

    @Override
    public void render(Canvas canvas) {
        canvas.drawPolygon(xPoints, yPoints, nPoints);
    }
}

//SceneRenderer (A mágica do Polimorfismo acontecendo e de forma simples)
class SceneRenderer {
    public void renderAll(List<Shape> shapes, Canvas canvas) {
        for (Shape shape : shapes) {
            // O renderer não faz ideia de qual forma está chamando.
            // Ele só confia no contrato da classe abstrata!
            shape.render(canvas);
        }
    }
}

//Classe MAIN
public class Hard {
    public static void main(String[] args) {
        // Criando nosso Canvas (Passamos null no Graphics apenas para simular no console)
        Canvas meuCanvas = new Canvas(null);

        // Criando a cena com formas variadas
        List<Shape> cena = new ArrayList<>();
        cena.add(new Circle(10, 10, 5));
        cena.add(new Rectangle(0, 0, 100, 50));
        cena.add(new Polygon(new int[]{0, 5, 10}, new int[]{0, 10, 0}, 3)); // Um triângulo

        //Renderizando
        SceneRenderer renderer = new SceneRenderer();
        renderer.renderAll(cena, meuCanvas);
    }
}
