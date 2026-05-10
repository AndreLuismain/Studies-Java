/*
Crie um record Point(double x, double y).
Adicione um metodo customizado distanceTo(Point other).
Compare com o que seria necessário escrever como classe normal.
Crie uma lista de pontos e encontre o par mais próximo.
 */

import java.util.List;

// Criar o record é só isso. Ele já gera construtor, getters, toString, equals e hashCode.
record Point(double x, double y) {

    // Podemos adicionar métodos customizados normalmente dentro de um record
    public double distanceTo(Point other) {
        // Math.hypot(dx, dy) calcula a raiz quadrada da soma dos quadrados
        return Math.hypot(this.x - other.x, this.y - other.y);
    }
}


record PointClass(double x, double y) {
    // 1. Construtor

    // 3. Método customizado
    public double distanceTo(PointClass other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    // 4. equals() para comparar se os valores são iguais (e não a referência de memória)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointClass that = (PointClass) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    // 6. toString() para imprimir de forma legível
    @Override
    public String toString() {
        return "PointClass[x=" + x + ", y=" + y + "]";
    }
}



public class Easy {
    public static void main(String[] args) {
        // Criando uma lista de pontos
        List<Point> points = List.of(
                new Point(2.0, 3.0),
                new Point(12.0, 30.0),
                new Point(40.0, 50.0),
                new Point(5.0, 1.0),   // Próximo do (2.0, 3.0)
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)    // Muito próximo do (2.0, 3.0)
        );

        // Variáveis para guardar o resultado
        Point closest1 = null;
        Point closest2 = null;
        double minDistance = Double.MAX_VALUE;

        // Loop duplo para comparar cada ponto com todos os outros
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {

                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double currentDistance = p1.distanceTo(p2);

                // Se achou uma distância menor, atualiza os dados
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    closest1 = p1;
                    closest2 = p2;
                }
            }
        }

        // Imprimindo o resultado. Note como o toString() do record já formata bonito
        System.out.println("Ponto 1: " + closest1);
        System.out.println("Ponto 2: " + closest2);
        System.out.printf("Menor distância: %.2f\n", minDistance);
    }
}
