package u;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import c.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StoreFoodInspector {
    
    private PizzaStore store;
    private PizzaFactory factory;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @Before
    public void setUp() {
        factory = new PizzaFactory();
        store = new PizzaStore(factory);
        
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @After
    public void tearDown() {
        if (originalOut != null) {
            System.setOut(originalOut);
        }
    }
    
    @Test
    public void testOrderValidCheesePizza() {
        Pizza pizza = store.orderPizza("CHEESE");
        
        assertNotNull("Pizza should not be null", pizza);
        assertEquals("Pizza name should be Cheese", "Cheese", pizza.name);
        
        String output = outputStream.toString();
        assertContainsAllProcessingSteps(output, "Cheese");
    }
    
    @Test
    public void testOrderValidPepperoniPizza() {
        Pizza pizza = store.orderPizza("PEPPERONI");
        
        assertNotNull("Pizza should not be null", pizza);
        assertEquals("Pizza name should be Pepperoni", "Pepperoni", pizza.name);
        
        String output = outputStream.toString();
        assertContainsAllProcessingSteps(output, "Pepperoni");
    }
    
    @Test
    public void testOrderValidHotOilPizza() {
        Pizza pizza = store.orderPizza("HOT OIL");
        
        assertNotNull("Pizza should not be null", pizza);
        assertEquals("Pizza name should be Hot Oil", "Hot Oil", pizza.name);
        
        String output = outputStream.toString();
        assertTrue("Should contain prepare message", output.contains("Preparing Hot Oil pizza..."));
    }
    
    @Test
    public void testOrderValidBuffaloChickenPizza() {
        Pizza pizza = store.orderPizza("BUFFALO CHICKEN");
        
        assertNotNull("Pizza should not be null", pizza);
        assertEquals("Pizza name should be Buffalo Chicken", "Buffalo Chicken", pizza.name);
        
        String output = outputStream.toString();
        assertTrue("Should contain prepare message", output.contains("Preparing Buffalo Chicken pizza..."));
    }
    
    @Test
    public void testOrderInvalidPizza() {
        Pizza pizza = store.orderPizza("INVALID_TYPE");
        
        assertNull("Pizza should be null for invalid type", pizza);
        
        String output = outputStream.toString();
        assertTrue("Should contain error message", 
                   output.contains("Sorry but we don't serve that!") || 
                   output.contains("We don't have that pizza!"));
    }
    
    @Test
    public void testOrderEmptyStringPizza() {
        Pizza pizza = store.orderPizza("");
        
        assertNull("Pizza should be null for empty string", pizza);
        
        String output = outputStream.toString();
        assertTrue("Should contain error message", 
                   output.contains("Sorry but we don't serve that!") || 
                   output.contains("We don't have that pizza!"));
    }
    
    @Test
    public void testCaseInsensitiveOrdering() {
        Pizza pizza1 = store.orderPizza("cheese");
        Pizza pizza2 = store.orderPizza("ChEeSe");
        Pizza pizza3 = store.orderPizza("CHEESE");
        
        assertNotNull("Lowercase should work", pizza1);
        assertNotNull("Mixed case should work", pizza2);
        assertNotNull("Uppercase should work", pizza3);
        
        assertEquals("All should be Cheese", "Cheese", pizza1.name);
        assertEquals("All should be Cheese", "Cheese", pizza2.name);
        assertEquals("All should be Cheese", "Cheese", pizza3.name);
    }
    
    @Test
    public void testAllValidPizzaTypes() {
        String[] validTypes = {
            "CHEESE", "PEPPERONI", "HOT OIL", "GLUTENFREE", 
            "SAUSAGE", "BUFFALO CHICKEN", "NASHVILLE HOT", "MARGHERITA"
        };
        
        for (String type : validTypes) {
            outputStream.reset();
            
            Pizza pizza = store.orderPizza(type);
            assertNotNull("Pizza should not be null for type: " + type, pizza);
            
            String output = outputStream.toString();
            assertTrue("Should contain preparation steps for " + type, 
                       output.contains("Preparing"));
            assertTrue("Should contain baking steps for " + type, 
                       output.contains("Baking"));
            assertTrue("Should contain cutting steps for " + type, 
                       output.contains("Cutting"));
            assertTrue("Should contain boxing steps for " + type, 
                       output.contains("Boxing"));
        }
    }
    
    private void assertContainsAllProcessingSteps(String output, String pizzaType) {
        assertTrue("Should contain prepare message", output.contains("Preparing " + pizzaType + " pizza..."));
        assertTrue("Should contain bake message", output.contains("Baking " + pizzaType + " pizza..."));
        assertTrue("Should contain cut message", output.contains("Cutting " + pizzaType + " pizza..."));
        assertTrue("Should contain box message", output.contains("Boxing " + pizzaType + " pizza..."));
    }
}