package c;

public interface Gas {
    void setCostPerGallon(double cost);
    double getCostPerGallon();
    double calculateMPG(double miles, double gallons);
    double calculateTotalCostG(double miles, double mpg);
}