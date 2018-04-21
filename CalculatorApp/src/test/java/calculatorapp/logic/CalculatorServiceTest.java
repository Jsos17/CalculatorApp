/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import calculatorapp.logic.CalculatorService;
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
        assertEquals(4.9257, calcServ.addDouble(1.2765, 3.6492), 0.0001);
    }

    @Test
    public void subtractDoubleIsCorrect() {
        assertEquals(-2.3727, calcServ.subtractDouble(1.2765, 3.6492), 0.0001);
    }

    @Test
    public void multiplyDoubleIsCorrect() {
        assertEquals(39.20180351, calcServ.multiplyDouble(5.49717, 7.13127), 0.0001);
    }

    @Test
    public void divideDoubleIsCorrect() {
        assertEquals(0.95192568, calcServ.divideDouble(6.78149, 7.12397), 0.0001);
    }

    @Test
    public void cannotDivideByZero() {
        assertEquals(Double.NaN, calcServ.divideDouble(5.0, 0), 0.001);
    }

    @Test
    public void percentageIsCorrect() {
        assertEquals(42.0, calcServ.percent(0.42), 0.0001);
    }

    @Test
    public void moduloIsCorrect() {
        assertEquals(44, calcServ.modulo(101, 57));
    }

    @Test
    public void naturalLogNonPositiverArgumentsNotAllowed() {
        assertEquals(Double.NaN, calcServ.naturalLog(-1), 0.001);
    }

    @Test
    public void naturaLogIsCorrect() {
        assertEquals(4.5217886, calcServ.naturalLog(92), 0.001);
    }

    @Test
    public void base10LogNonPositiveArgumentsNotAllowed() {
        assertEquals(Double.NaN, calcServ.base10log(-10), 0.001);
    }

    @Test
    public void base10LogIsCorrect() {
        assertEquals(3, calcServ.base10log(1000), 0.0001);
    }

    @Test
    public void absIsCorrect1() {
        assertEquals(3.4, calcServ.abs(-3.4), 0.00001);
    }

    @Test
    public void absIsCorrect2() {
        assertEquals(0.000001, calcServ.abs(-0.000001), 0.000_001);
    }

    @Test
    public void exponentiationIsCorrect1() {
        assertEquals(14641, calcServ.exp(11, 4), 0.0001);
    }

    @Test
    public void exponentiationIsCorrect2() {
        assertEquals(1520.301258, calcServ.exp(3.7, 5.6), 0.0001);
    }
    
    @Test
    public void exponentiationIsCorrect3() {
        assertEquals(Double.NaN, calcServ.exp(0, 0), 0.0001);
    }
    
    @Test
    public void exponentiationIsCorrect4() {
        assertEquals(Double.NaN, calcServ.exp(0.0000, 0.0000), 0.0001);
    }
}
