package c;

public class CarRunner {
    public static void main(String[] args){
        Hybrid car = new Hybrid();
        car.setCostPerGallon(3.50);
        car.setCostPerKWH(0.24);

        double mpg = car.calculateMPG(120, 6);
        double mpge = car.calculateMPGe(300, 70);

        System.out.println("MPG: " + mpg);
        System.out.println("MPGe: " + mpge);
        System.out.println("Gas trip cost: $" + car.calculateTotalCostG(120, mpg));
        System.out.println("Electric trip cost: $" + car.calculateTotalCostE(300, mpge));
    }
} 