package u;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import c.Hybrid;

public class HybridTest {
    Hybrid car;

    @Before
    public void setup() {
        car = new Hybrid();
        car.setCostPerGallon(3.50);
        car.setCostPerKWH(0.24);
    }

    @Test
    public void testMPG() {
        assertEquals(20.0, car.calculateMPG(120, 6), 0.001);
        assertEquals(25.0, car.calculateMPG(100, 4), 0.001);
        assertEquals(30.0, car.calculateMPG(150, 5), 0.001);
        assertEquals(15.0, car.calculateMPG(90, 6), 0.001);
        assertEquals(33.33, car.calculateMPG(200, 6), 0.01);
    }

    @Test
    public void testMPGe() {
        assertEquals(144.43, car.calculateMPGe(300, 70), 0.1);
        assertEquals(134.8, car.calculateMPGe(400, 100), 0.1);
        assertEquals(269.6, car.calculateMPGe(600, 75), 0.1);
        assertEquals(107.84, car.calculateMPGe(480, 150), 0.1);
        assertEquals(215.68, car.calculateMPGe(720, 112.5), 0.1);
    }

    @Test
    public void testTotalCostG(){
        assertEquals(70.0, car.calculateTotalCostG(120, 6), 0.01);
        assertEquals(87.5, car.calculateTotalCostG(100, 4), 0.01);
        assertEquals(105.0, car.calculateTotalCostG(150, 5), 0.01);
        assertEquals(52.5, car.calculateTotalCostG(90, 6), 0.01);
        assertEquals(116.66, car.calculateTotalCostG(200, 6), 0.01);
    }

    @Test
    public void testTotalCostE(){
        assertEquals(34.66, car.calculateTotalCostE(300, 70), 0.01);
        assertEquals(32.35, car.calculateTotalCostE(400, 100), 0.01);
        assertEquals(64.7, car.calculateTotalCostE(600, 75), 0.01);
        assertEquals(25.88, car.calculateTotalCostE(480, 150), 0.01);
        assertEquals(51.76, car.calculateTotalCostE(720, 112.5), 0.01);
    }
}