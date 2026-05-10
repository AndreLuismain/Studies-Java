/*
Modele expressões matemáticas: sealed interface
Expr permits Num, Add, Mul, Neg. record Num(double value),
record Add(Expr left, Expr right), record Mul(Expr left, Expr right),
record Neg(Expr expr). Escreva double eval(Expr e) com switch pattern
matching recursivo. Escreva também String prettyPrint(Expr e). Monte e
avalie: Mul(Add(Num(2), Num(3)), Neg(Num(4))) → deve dar -20.
Data: 08/05/2026
 */


//A hierarquia selada que define a nossa árvore de sintaxe abstrata (AST)
sealed interface Expr permits Num, Add, Mul, Neg {}

record Num(double value) implements Expr {}
record Add(Expr left, Expr right) implements Expr {}
record Mul(Expr left, Expr right) implements Expr {}
record Neg(Expr expr) implements Expr {}


//Classe MAIN
public class Hard {

    //Metodo de avaliação recursiva com Pattern Matching
    public static double eval(Expr e) {
        return switch (e) {
            case Num n -> n.value();
            case Add a -> eval(a.left()) + eval(a.right());
            case Mul m -> eval(m.left()) * eval(m.right());
            case Neg n -> -eval(n.expr());
        };
    }

    //Metodo para imprimir a expressão (também recursivo)
    public static String prettyPrint(Expr e) {
        return switch (e) {
            // Formata para tirar as casas decimais de números inteiros para ficar mais bonito
            case Num n -> n.value() % 1 == 0 ?
                    String.valueOf((int) n.value()) :
                    String.valueOf(n.value());
            case Add a -> "(" + prettyPrint(a.left()) + " + " + prettyPrint(a.right()) + ")";
            case Mul m -> "(" + prettyPrint(m.left()) + " * " + prettyPrint(m.right()) + ")";
            case Neg n -> "(-" + prettyPrint(n.expr()) + ")";
        };
    }
    //Classe main
    public static void main(String[] args) {
        // 4. Montando a expressão: Mul(Add(Num(2), Num(3)), Neg(Num(4)))
        Expr expression = new Mul(
                new Add(new Num(2), new Num(3)),
                new Neg(new Num(4))
        );

        //Avaliando e imprimindo
        double result = eval(expression);
        String text = prettyPrint(expression);

        System.out.println("Expressão Montada: " + text);
        System.out.println("Resultado da Avaliação: " + result);
    }
}