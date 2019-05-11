/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

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
    public void addDoubleIsCorrect() {
        assertEquals(4.9257, calcServ.execute("+", new double[]{1.2765, 3.6492}), 0.0001);
    }

    @Test
    public void subtractDoubleIsCorrect() {
        assertEquals(-2.3727, calcServ.execute("-", new double[]{1.2765, 3.6492}), 0.0001);
    }

    @Test
    public void multiplyDoubleIsCorrect() {
        assertEquals(39.20180351, calcServ.execute("*", new double[]{5.49717, 7.13127}), 0.0001);
    }

    @Test
    public void divideDoubleIsCorrect() {
        assertEquals(0.95192568, calcServ.execute("/", new double[]{6.78149, 7.12397}), 0.0001);
    }

    @Test
    public void cannotDivideByZero() {
        assertEquals(Double.NaN, calcServ.execute("/", new double[]{5.0, 0}), 0.001);
    }

    @Test
    public void percentageIsCorrect() {
        assertEquals(0.420, calcServ.execute("%", new double[]{42}), 0.0001);
    }

    @Test
    public void naturalLogNonPositiverArgumentsNotAllowed() {
        assertEquals(Double.NaN, calcServ.execute("ln", new double[]{-1}), 0.001);
    }

    @Test
    public void naturaLogIsCorrect() {
        assertEquals(4.5217886, calcServ.execute("ln", new double[]{92}), 0.001);
    }

    @Test
    public void base10LogNonPositiveArgumentsNotAllowed() {
        assertEquals(Double.NaN, calcServ.execute("log", new double[]{-10}), 0.001);
    }

    @Test
    public void base10LogIsCorrect() {
        assertEquals(3, calcServ.execute("log", new double[]{1000}), 0.0001);
    }

    @Test
    public void absIsCorrect1() {
        assertEquals(3.4, calcServ.execute("abs", new double[]{-3.4}), 0.00001);
    }

    @Test
    public void absIsCorrect2() {
        assertEquals(0.000001, calcServ.execute("abs", new double[]{-0.000001}), 0.000_001);
    }

    @Test
    public void exponentiationIsCorrect1() {
        assertEquals(14641, calcServ.execute("^", new double[]{11, 4}), 0.0001);
    }

    @Test
    public void exponentiationIsCorrect2() {
        assertEquals(1520.301258, calcServ.execute("^", new double[]{3.7, 5.6}), 0.0001);
    }

    @Test
    public void exponentiationIsCorrect3() {
        assertEquals(Double.NaN, calcServ.execute("^", new double[]{0, 0}), 0.0001);
    }

    @Test
    public void exponentiationIsCorrect4() {
        assertEquals(Double.NaN, calcServ.execute("^", new double[]{0.0000, 0.0000}), 0.0001);
    }

    @Test
    public void sinWorks1() {
        assertEquals(0.160890315, calcServ.execute("sin", new double[]{2.98}), 0.000001);
    }

    @Test
    public void sinWorks2() {
        assertEquals(0, calcServ.execute("sin", new double[]{0}), 0.000001);
    }

    @Test
    public void cosWorks1() {
        assertEquals(1, calcServ.execute("cos", new double[]{0}), 0.000001);
    }

    @Test
    public void cosWorks2() {
        assertEquals(-0.999999, calcServ.execute("cos", new double[]{-3.14}), 0.00001);
    }

    @Test
    public void tanWorks1() {
        assertEquals(-0.00159265, calcServ.execute("tan", new double[]{3.14}), 0.000001);
    }

    @Test
    public void tanWorks2() {
        assertEquals(1255.76559, calcServ.execute("tan", new double[]{1.57}), 0.0001);
    }

    @Test
    public void tanWorks3() {
        assertEquals(Math.tan(Math.PI / 2), calcServ.execute("tan", new double[]{Math.PI / 2}), 0.01);
    }

    @Test
    public void negateWorks1() {
        assertEquals(-42.3, calcServ.execute("neg", new double[]{42.3}), 0.0001);
    }

    @Test
    public void negateWorks2() {
        assertEquals(42.3, calcServ.execute("neg", new double[]{-42.3}), 0.0001);
    }

    @Test
    public void squareRootWorks1() {
        assertEquals(Double.NaN, calcServ.execute("sqrt", new double[]{-42.3}), 0.001);
    }

    @Test
    public void squareRootWorks2() {
        assertEquals(8.0, calcServ.execute("sqrt", new double[]{64}), 0.001);
    }

    @Test
    public void squareRootWorks3() {
        assertEquals(34.4901, calcServ.execute("sqrt", new double[]{1189.5672}), 0.0001);
    }
}
