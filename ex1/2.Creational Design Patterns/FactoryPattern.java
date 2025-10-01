import java.util.*;

interface Vehicle { void drive(); }
class Car implements Vehicle { public void drive() { System.out.println("Driving a Car"); } }
class Bike implements Vehicle { public void drive() { System.out.println("Riding a Bike"); } }

class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car": return new Car();
            case "bike": return new Bike();
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter vehicle type (Car/Bike): ");
        String type = sc.nextLine();

        try {
            Vehicle v = VehicleFactory.createVehicle(type);
            v.drive();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
