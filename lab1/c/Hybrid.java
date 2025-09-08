package c;

public class Hybrid implements Gas, Electric {

    private double costPerGallon;
    private double costPerKWH;

    public void setCostPerGallon(double cost){
        this.costPerGallon = cost;
    }

    public double getCostPerGallon(){
        return costPerGallon;
    }

    public double calculateMPG(double miles, double gallons){
        return miles / gallons;
    }

    public double calculateTotalCostG(double miles, double mpg){
        double gallons = miles / mpg;
        return gallons * costPerGallon;
    }

    public void setCostPerKWH(double cost){
        this.costPerKWH = cost;
    }

    public double getCostPerKWH(){
        return costPerKWH;
    }

    public double calculateMPGe(double miles, double kwh){
        return miles / kwh * 33.7;
    }

    public double calculateTotalCostE(double miles, double mpge){
        double costPerMile = costPerKWH * 33.7 / mpge;
        return miles * costPerMile;
    }
    
}