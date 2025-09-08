package c;

public interface Electric {
    void setCostPerKWH(double cost);
    double getCostPerKWH();
    double calculateMPGe(double miles, double kwh);
    double calculateTotalCostE(double miles, double mpge);
}