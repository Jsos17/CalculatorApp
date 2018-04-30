/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import static org.junit.Assert.assertEquals;
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
        inputParser = new InputParser(calc);
        exprEval = new ExpressionEvaluator(calc, inputParser);
    }

    @Test
    public void expressionEvaluationWorksCorrectly1() {
        String expression = "sin(0.5)";
        assertEquals(0.4794, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly2() {
        String expression = "log(1000)";
        assertEquals(3, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly3() {
        String expression = "cos(3)+sin(0.5)";
        assertEquals(-0.5106, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly4() {
        String expression = "ln(43)*ln(17)";
        assertEquals(10.656, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly5() {
        String expression = "(tan(43)*42)/4";
        assertEquals(-15.733, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly6() {
        String expression = "tan(43)";
        assertEquals(-1.4984, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly7() {
        String expression = "log(log(10^10))";
        assertEquals(1, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly9() {
        String expression = "cos(3+(1-3)^2)+sin(45*0.5)";
        assertEquals(0.2667, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly10() {
        String expression = "ln(cos(3+(1-3)^2)+sin(45*0.5))";
        assertEquals(-1.3215, exprEval.expressionEvaluation(expression), 0.001);
    }

    @Test
    public void expressionEvaluationWorksCorrectly11() {
        String expression = "sqrt(log(((cos(3+(6-3)^2.4)+sin(4.3*0.73))+14.62)^4))";
        assertEquals(2.15017, exprEval.expressionEvaluation(expression), 0.001);
    }
}
