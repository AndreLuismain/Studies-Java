/*
Você tem Car (com drive()) e Boat (com sail()).
Tente modelar um AmphibiousVehicle sem estender
nenhum dos dois. Crie interfaces Drivable e Sailable,
e use composição: AmphibiousVehicle tem instâncias de
CarEngine e BoatHull como fields, delegando para cada um.
Data: 06/05/2026
 */

//Interface motores
interface Drivable{
    void drive();
}
interface Sailable{
    void sail();
}
//Veiculo carro que usa implemento
class Car implements Drivable{
    @Override
    public void drive(){
        System.out.println("Drivable!");
    }
}
//veiculo barco que usa implemento
class Boat implements Sailable{
    @Override
    public void sail(){
            System.out.println("Boat!");
    }
}
//Veiculo anfibio que usa composição
//COMPOSIÇÃO: O veículo TEM UM motor e TEM UM casco
class AmphibiousVehicle implements Drivable, Sailable{
    private final CarEngine engine;
    private final BoatHull hull;

    public AmphibiousVehicle() {
        this.engine = new CarEngine();
        this.hull = new BoatHull();
    }
    //DELEGAÇÃO: Quando mandam o anfíbio dirigir, ele manda o motor trabalhar
    @Override
    public void drive() {
        System.out.println("Iniciando modo terrestre...");
        this.engine.drive(); // Repassa a responsabilidade
    }
    //DELEGAÇÃO: Quando mandam o anfíbio navegar, ele manda o casco trabalhar
    @Override
    public void sail() {
        System.out.println("Iniciando modo aquático...");
        this.hull.sail(); // Repassa a responsabilidade
    }
}
// Peça 1: O Motor do carro
class CarEngine implements Drivable {
    @Override
    public void drive() {
        System.out.println("Motor do carro roncando na terra!");
    }
}

// Peça 2: O Casco do barco
class BoatHull implements Sailable {
    @Override
    public void sail() {
        System.out.println("Casco do barco cortando as águas!");
    }
}

//Classe Main
public class Medium {
    public static void main(String[] args) {
        Car carro1 = new Car();
        Boat barco1 = new Boat();
        AmphibiousVehicle anfibio = new AmphibiousVehicle();

        carro1.drive();
        barco1.sail();

        System.out.println("--- Testando o Anfíbio ---");
        //AmphibiousVehicle implementa os métodos e os delega
        anfibio.drive();
        anfibio.sail();
    }
}
