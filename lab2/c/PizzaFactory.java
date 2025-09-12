package c;

public class PizzaFactory {
    public Pizza createPizza(String type){

        if(type == null){
            return null;
            
        } else if (type.equalsIgnoreCase("CHEESE")) {
            return new Cheese();

        } else if (type.equalsIgnoreCase("PEPPERONI")) {
            return new Pepperoni();

        } else if (type.equalsIgnoreCase("HOT OIL")) {
            return new Hotoil();

        } else if (type.equalsIgnoreCase("GLUTENFREE")) {
            return new GlutenFree();

        } else if (type.equalsIgnoreCase("SAUSAGE")) {
            return new Sausage();

        } else if (type.equalsIgnoreCase("BUFFALO CHICKEN")) {
            return new BufChick();

        } else if (type.equalsIgnoreCase("NASHVILLE HOT")) {
            return new NashHot();

        } else if (type.equalsIgnoreCase("MARGHERITA")) {
            return new Margherita();

        } else {
            System.out.println("Sorry but we don't serve that!");
            return null;
        }

    }
}