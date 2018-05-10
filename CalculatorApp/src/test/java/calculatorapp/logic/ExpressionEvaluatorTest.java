/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayDeque;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jpssilve
 */
public class ExpressionEvaluatorTest {

    private CalculatorService calc;
    private InputParser inputParser;
    private ExpressionEvaluator exprEval;

    @Before
    public void setUp() {
        calc = new CalculatorService();
        inputParser = new InputParser();
        exprEval = new ExpressionEvaluator(calc, inputParser);
    }

    @Test
    public void expressionEvaluationWorksCorrectly1() {
        String expression = "8*9-15+3/20";
        assertEquals(57.15, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly2() {
        String expression = "16*9-72/6/3*5+86-45+162";
        assertEquals(327.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly3() {
        String expression = "7-92*45/4-20/0+87*0+4";
        assertEquals(Double.NaN, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectlyy4() {
        String expression = "7.9-92.7*4.5/4-20/2.5+87.5*0.1+4.05";
        assertEquals(-91.5875, exprEval.expressionEvaluation(expression), 0.000_001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly5() {
        String expression = "8";
        assertEquals(8.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly6() {
        String expression = "";
        assertEquals(Double.NaN, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly7() {
        String expression = "9.5-10.1*5/0.173+1001*498/0.125";
        assertEquals(3987701.592, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly8() {
        String expression = "((8+9)+13)";
        assertEquals(30.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly9() {
        String expression = "(8-5)*3+(10/5)";
        assertEquals(11.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly10() {
        String expression = "(67/30)*29+6";
        assertEquals(70.76667, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly11() {
        String expression = "((15/(7-(1+1)))*3)-(2+(1+1))";
        assertEquals(5.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly12() {
        String expression = "8()9";
        assertEquals(Double.NaN, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly13() {
        String expression = "3+4*2/(1-5)^2^3";
        assertEquals(3.00012207, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly14() {
        String expression = "(8+2^5)-7+(8*5)^2";
        assertEquals(1633.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly15() {
        String expression = "2^2^2^2";
        assertEquals(65536.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly16() {
        String expression = "2^(2+3^2)";
        assertEquals(2048.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly17() {
        String expression = "((2-67)*3^(2-0)+((100*3)-100))*(0-1)";
        assertEquals(385.0, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly18() {
        String expression = "(1/5)^2+46";
        assertEquals(46.04, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly19() {
        String expression = "()^2++46";
        assertEquals(Double.NaN, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly20() {
        String expression = "((8.105+5.7)*2/3+8^3.9-6*(27/127)*45-10000+1.4^2-67^0.1+6/4.8+1011.76^1.2+56-56/0.123+3456)*0.45-6";
        assertEquals(162.529, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly21() {
        String expression = "((5.175+5.7)*2/3+8^3.2-6*(27/127)*45-20000+1.4^2-32^0.2+9/4.8+1011.76^1.2+42-56/0.42-45+56)*1.34+2^2.5-5-100+663/123*544*6/1002+((8.105+5.7)*2/3+8^3.9-6*(27/127)*45-10000+1.4^2-67^0.1+6/4.8+1011.76^1.2+56-56/0.123+3456)*0.45-6";
        assertEquals(-20441.737, exprEval.expressionEvaluation(expression), 0.01);
    }

    @Test
    public void expressionEvaluationWorksCorrectly22() {
        String tooLong = "";
        for (int i = 1; i <= 200; i++) {
            tooLong += "1234567890";
        }

        assertTrue(Double.isInfinite(exprEval.expressionEvaluation(tooLong)));
    }

    @Test
    public void executeTheRightOperationCornerCase() {
        assertEquals(Double.NaN, exprEval.executeTheRightOperation('?', 0, 0), 0.0001);
    }

    @Test
    public void stringIsAmathOperatorCornerCase1() {
        assertFalse(exprEval.stringIsAMathOperator("++"));
    }

    @Test
    public void postfixEvaluatorCornerCase1() {
        ArrayDeque<String> output = new ArrayDeque<>();
        assertEquals(Double.NaN, exprEval.postfixEvaluator(output), 0.001);
    }

    @Test
    public void postfixEvaluatorCornerCase2() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("9");
        output.addLast("+");
        assertEquals(9.0, exprEval.postfixEvaluator(output), 0.001);
    }

    @Test
    public void postfixEvaluatorCornerCase3() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("^");
        assertEquals(Double.NaN, exprEval.postfixEvaluator(output), 0.001);
    }

    @Test
    public void postfixEvaluatorCornerCase4() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("7");
        assertEquals(7.0, exprEval.postfixEvaluator(output), 0.001);
    }

    @Test
    public void postfixEvaluatorCornerCase5() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("(");
        assertEquals(Double.NaN, exprEval.postfixEvaluator(output), 0.001);
    }

    @Test
    public void expressionEvaluationCornerCase1() {
        assertEquals(Double.NaN, exprEval.expressionEvaluation("8(+6"), 0.001);
    }

    @Test
    public void expressionEvaluationCornerCase2() {
        assertEquals(Double.NaN, exprEval.expressionEvaluation(")8(+6"), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly1() {
        String expression = "sin(0.5)";
        assertEquals(0.4794, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly2() {
        String expression = "log(1000)";
        assertEquals(3, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly3() {
        String expression = "cos(3)+sin(0.5)";
        assertEquals(-0.5106, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly4() {
        String expression = "ln(43)*ln(17)";
        assertEquals(10.656, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly5() {
        String expression = "(tan(43)*42)/4";
        assertEquals(-15.733, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly6() {
        String expression = "tan(43)";
        assertEquals(-1.4984, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly7() {
        String expression = "log(log(10^10))";
        assertEquals(1, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly8() {
        String expression = "abs(0-2)";
        assertEquals(2, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly9() {
        String expression = "neg(2)";
        assertEquals(-2, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly10() {
        String expression = "abs(neg(42))";
        assertEquals(42, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly11() {
        String expression = "neg(1)^7";
        assertEquals(-1, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly12() {
        String expression = "neg(2)^7";
        assertEquals(-128.0, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly13() {
        String expression = "%(128)";
        assertEquals(1.28, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly14() {
        String expression = "%(33)";
        assertEquals(0.33, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void functionEvaluationWorksCorrectly15() {
        String expression = "cos(3+(1-3)^2)+sin(45*0.5)";
        assertEquals(0.2667, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly16() {
        String expression = "ln(cos(3+(1-3)^2)+sin(45*0.5))";
        assertEquals(-1.3215, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly17() {
        String expression = "sqrt(log(((cos(3+(6-3)^2.4)+sin(4.3*0.73))+14.62)^4))";
        assertEquals(2.15017, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void functionEvaluationWorksCorrectly18() {
        String expression = "neg(ln(65+log(1175)+sin(3.47)*cos(1.45))/tan(57))";
        assertEquals(-8.70636, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void functionEvaluationWorksCorrectly19() {
        String expression = "abs(neg(ln(65+log(1175)+sin(3.47)*cos(1.45))/tan(57)))";
        assertEquals(8.70636, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void functionEvaluationWorksCorrectly20() {
        String expression = "sqrt(10^2)";
        assertEquals(10, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void functionEvaluationWorksCorrectly21() {
        String expression = "sqrt(neg(10)^2)";
        assertEquals(10, exprEval.expressionEvaluation(expression), 0.0001);
    }

    @Test
    public void functionEvaluationWorksCorrectly22() {
        String expression = "sqrt(neg(10))";
        assertEquals(Double.NaN, exprEval.expressionEvaluation(expression), 0.0001);
    }
}
