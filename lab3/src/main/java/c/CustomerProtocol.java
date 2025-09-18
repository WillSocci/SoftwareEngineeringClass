package c;
import java.util.Scanner;

public class CustomerProtocol {
    public static void main (String[] args){

        int count = 0;
        boolean hungry = true;
        Scanner scanner = new Scanner(System.in);

        while(hungry) {
            System.out.print("What Kind Of Pizza Would You Like? ~~~~ ");
            String pizzaType = scanner.nextLine();
            System.out.println(pizzaType + " coming right up!");

            PizzaStore store = new PizzaStore(new PizzaFactory());
            Pizza pizza = store.orderPizza(pizzaType);

            count++;
            System.out.print("Are you still hungry? (y/n)  ");
            String cont = scanner.nextLine();

            if (cont.equalsIgnoreCase("n")){
                System.out.println("Thanks for dining with us! You ate " + count + " pizzas!");
                hungry = false;
            }
        }



    }
}