package u;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import c.*;

public class FactoryFoodInspector {
    private PizzaFactory factory;
    
    @Before
    public void setUp() {
        factory = new PizzaFactory();
    }
    
    @Test
    public void testCreateCheesePizza() {
        Pizza pizza = factory.createPizza("CHEESE");
        assertNotNull(pizza);
        assertEquals("Cheese", pizza.name);
    }
    
    @Test
    public void testCreatePepperoniPizza() {
        Pizza pizza = factory.createPizza("PEPPERONI");
        assertNotNull(pizza);
        assertEquals("Pepperoni", pizza.name);
    }
    
    @Test
    public void testCreateHotOilPizza() {
        Pizza pizza = factory.createPizza("HOT OIL");
        assertNotNull(pizza);
        assertEquals("Hot Oil", pizza.name);
    }
    
    @Test
    public void testCreateBuffaloChickenPizza() {
        Pizza pizza = factory.createPizza("BUFFALO CHICKEN");
        assertNotNull(pizza);
        assertEquals("Buffalo Chicken", pizza.name);
    }
    
    @Test
    public void testCaseInsensitiveInput() {
        Pizza pizza1 = factory.createPizza("cheese");
        Pizza pizza2 = factory.createPizza("ChEeSe");
        Pizza pizza3 = factory.createPizza("CHEESE");
        
        assertNotNull(pizza1);
        assertNotNull(pizza2);
        assertNotNull(pizza3);
        assertEquals("Cheese", pizza1.name);
        assertEquals("Cheese", pizza2.name);
        assertEquals("Cheese", pizza3.name);
    }
    
    @Test
    public void testInvalidPizzaType() {
        Pizza pizza = factory.createPizza("INVALID");
        assertNull(pizza);
    }
    
    @Test
    public void testNullInput() {
        Pizza pizza = factory.createPizza(null);
        assertNull(pizza);
    }
    
    @Test
    public void testEmptyStringInput() {
        Pizza pizza = factory.createPizza("");
        assertNull(pizza);
    }
    
    @Test
    public void testAllValidPizzaTypes() {
        String[] validTypes = {
            "CHEESE", "PEPPERONI", "HOT OIL", "GLUTENFREE", 
            "SAUSAGE", "BUFFALO CHICKEN", "NASHVILLE HOT", "MARGHERITA"
        };
        
        for (String type : validTypes) {
            Pizza pizza = factory.createPizza(type);
            assertNotNull("Failed to create pizza of type: " + type, pizza);
        }
    }
}