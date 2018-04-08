/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpssilve
 */
public class InputHandlerTest {
    private InputHandler inHandlr;
    private CalculatorService calculator;
    
    @Before
    public void setUp() {
        calculator = new CalculatorService();
        inHandlr = new InputHandler(calculator);
    }
    
    @Test
    public void checksBracketingCorrectly1() {
        String expression = "((x+y)+y)-u)";
        assertFalse(inHandlr.correctBracketing(expression));
    }
    
    @Test
    public void checksBracketingCorrectly2() {
        String expression = "(((x+y)+y)-u)";
        assertTrue(inHandlr.correctBracketing(expression));
    }
    
    @Test
    public void checksBracketingCorrectly3() {
        String expression = "";
        assertTrue(inHandlr.correctBracketing(expression));
    }
    
    @Test
    public void checksBracketingCorrectly4() {
        String expression = "(h((9(((((((((9((((((h((((((((6))))9)))))))))))))h)))))h))))";
        assertTrue(inHandlr.correctBracketing(expression));
    }
    
    @Test
    public void checksBracketingCorrectly5() {
        String expression = ")(";
        assertFalse(inHandlr.correctBracketing(expression));
    }
    
    @Test
    public void checksMathOperatorsCorrectly1() {
        String expression = "6+5-7";
        assertTrue(inHandlr.correctOperatorPlacement(expression));
    }
    
    @Test
    public void checksMathOperatorsCorrectly2() {
         String expression = "+";
         assertFalse(inHandlr.correctOperatorPlacement(expression));
    }
    
    @Test
    public void checksMathOperatorsCorrectly3() {
         String expression = "8*4/78-3+5*6789+1*27/42";
         assertTrue(inHandlr.correctOperatorPlacement(expression));
    }
    
    @Test
    public void checksMathOperatorsCorrectly4() {
        String expression = "643434433+552423422+7+";
        assertFalse(inHandlr.correctOperatorPlacement(expression));
         
    }
    
    @Test
    public void checksMathOperatorsCorrectly5() {
         String expression = "*67";
         assertFalse(inHandlr.correctOperatorPlacement(expression));
    }
    
//    @Test // private method testing???????
//    public void isANumberWorksCorrectly1() {
//    }
    
    @Test
    public void evaluateExpressionDoubleWorks1() {
        String expression = "9+6";
        assertEquals(15.0, inHandlr.evaluateExpressionDouble(expression), 0.000_000_1);
    }
    
    @Test // note: currently expressions are evaluated from left to right where + - * / have the same precedence
    public void evaluateExpressionDoubleWorks2() {
        String expression = "8*9-15+3/20";
        assertEquals(3.0, inHandlr.evaluateExpressionDouble(expression), 0.000_000_1);
    }
}
