/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpssilve
 */
public class CalculatorServiceTest {
    
    private CalculatorService calcServ;
    
    @Before
    public void setUp() {
        calcServ = new CalculatorService();
    }
    
    @Test
    public void addLongIsCorrect() {
        assertEquals(90, calcServ.addLong(67, 23));
        assertEquals(0, calcServ.addLong(10, -10));
    }
    
    @Test
    public void subtractLongIsCorrect() {
        assertEquals(20, calcServ.subtractLong(168, 148));
    }
    
    @Test
    public void multiplyLongIsCorrect() {
        assertEquals(294, calcServ.multiplyLong(42, 7));
    }
    
    @Test
    public void addDoubleIsCorrect() {
        assertEquals(4.9257, calcServ.addDouble(1.2765, 3.6492), 0.000_000_1);
    }
    
    @Test
    public void subtractDoubleIsCorrect() {
        assertEquals(-2.3727, calcServ.subtractDouble(1.2765, 3.6492), 0.000_000_1);
    }
    
    @Test
    public void multiplyDoubleIsCorrect() {
        assertEquals(39.20180351, calcServ.multiplyDouble(5.49717, 7.13127), 0.000_000_1);
    }
    
    @Test
    public void divideDoubleIsCorrect() {
        assertEquals(0.95192568, calcServ.divideDouble(6.78149, 7.12397), 0.000_000_1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void cannotDivideByZero() {
        calcServ.divideDouble(5.0, 0);
    }
    
    @Test
    public void percentageIsCorrect() {
        assertEquals(42.0, calcServ.percent(0.42), 0.000_000_1);
    }
    
    @Test
    public void moduloIsCorrect() {
        assertEquals(44, calcServ.modulo(101, 57));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void naturalLogNonPositiverArgumentsNotAllowed() {
        calcServ.naturalLog(-1);
    }
    
    @Test
    public void naturaLogIsCorrect() {
        assertEquals(4.5217886, calcServ.naturalLog(92), 0.000_000_1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void base10LogNonPositiveArgumentsNotAllowed() {
        calcServ.base10log(-10);
    }
    
    @Test
    public void base10LogIsCorrect() {
        assertEquals(3, calcServ.base10log(1000), 0.000_000_1);
    }
}
