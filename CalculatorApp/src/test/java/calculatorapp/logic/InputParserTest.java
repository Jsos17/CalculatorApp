/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayDeque;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpssilve
 */
public class InputParserTest {
    private InputParser inParser;
    private CalculatorService calculator;
    
    @Before
    public void setUp() {
        calculator = new CalculatorService();
        inParser = new InputParser(calculator);
    }
    
    @Test
    public void checksBracketingCorrectly1() {
        String expression = "((x+y)+y)-u)";
        assertFalse(inParser.bracketingEquals(expression));
    }
    
    @Test
    public void checksBracketingCorrectly2() {
        String expression = "(((x+y)+y)-u)";
        assertTrue(inParser.bracketingEquals(expression));
    }
    
    @Test
    public void checksBracketingCorrectly3() {
        String expression = "";
        assertTrue(inParser.bracketingEquals(expression));
    }
    
    @Test
    public void checksBracketingCorrectly4() {
        String expression = "((((((((((((((((((((((((((6))))))))))))))))))))))))))";
        assertTrue(inParser.bracketingEquals(expression));
    }
    
    @Test
    public void checksBracketingCorrectly5() {
        String expression = ")(";
        assertFalse(inParser.bracketingEquals(expression));
    }
    
    @Test
    public void numbersBracketsCheckedCorrectly1() {
        String expression = "(4+8((9+0(((((((((9+0((((((4-9((((((((6))))2*9)))))))))))))3+2)))))1))))";
        assertFalse(inParser.numbersAndBracketsCorrect(expression));
    }
    
    @Test
    public void  numbersBracketsCheckedCorrectly2() {
        String expression = "((((((((((((((((((((((((((6))))))))))))))))))))))))))";
        assertTrue(inParser.numbersAndBracketsCorrect(expression));
    }
    
     @Test
    public void  numbersBracketsCheckedCorrectly3() {
        String expression = "(42)(42)";
        assertFalse(inParser.numbersAndBracketsCorrect(expression));
    }

    @Test
    public void checksOperatorPlacementCorrectly1() {
        String expression = "6+5-7";
        assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly2() {
         String expression = "+";
         assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly3() {
         String expression = "8*4/78-3+5*6789+1*27/42";
         assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly4() {
        String expression = "643434433+552423422+7+";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
         
    }
    
    @Test
    public void checksOperatorPlacementCorrectly5() {
         String expression = "*67";
         assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly6() {
         String expression = "6*(67+7)-(8+9)/(8)";
         assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly7() {
         String expression = "6*(6...7+7...)-(.8+9)/(8)";
         assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksOperatorPlacementCorrectly8() {
         String expression = "6*(67+7)-(+9)/(8)";
         assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly1() {
        String expression = "6.5+0.6-9.6*9.6";
        assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly2() {
        String expression = ".9+9";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    @Test
    public void checksDotPlacementCorrectly3() {
        String expression = "..9.+9";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly4() {
        String expression = "87.";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly5() {
        String expression = "";
        assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly6() {
        String expression = "9..9";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }
    
    @Test
    public void checksDotPlacementCorrectly7() {
         String expression = "6*(..67+7...)-(.8+9)/(8)";
         assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }

    @Test
    public void expressionEvaluationWorksCorrectly1() {
        String expression = "8*9-15+3/20";
        assertEquals(57.15, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly2() {
        String expression = "16*9-72/6/3*5+86-45+162";
        assertEquals(327.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
     @Test
    public void expressionEvaluationWorksCorrectly3() {
        String expression = "7-92*45/4-20/0+87*0+4";
        assertEquals(Double.NaN, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectlyy4() {
        String expression = "7.9-92.7*4.5/4-20/2.5+87.5*0.1+4.05";
        assertEquals(-91.5875, inParser.expressionEvaluation(expression), 0.000_001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly5() {
        String expression = "8";
        assertEquals(8.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly6() {
        String expression = "";
        assertEquals(Double.NaN, inParser.expressionEvaluation(expression), 0.0001);
    }
    @Test
    public void expressionEvaluationWorksCorrectly7() {
        String expression = "9.5-10.1*5/0.173+1001*498/0.125";
        assertEquals(3987701.592, inParser.expressionEvaluation(expression), 0.001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly8() {
        String expression = "((8+9)+13)";
        assertEquals(30.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly9() {
        String expression = "(8-5)*3+(10/5)";
        assertEquals(11.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly10() {
        String expression = "(67/30)*29+6";
        assertEquals(70.76667, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly11() {
        String expression = "((15/(7-(1+1)))*3)-(2+(1+1))";
        assertEquals(5.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly12() {
        String expression = "8()9";
        assertEquals(Double.NaN, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly13() {
        String expression = "3+4*2/(1-5)^2^3";
        assertEquals(3.00012207, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly14() {
        String expression = "(8+2^5)-7+(8*5)^2";
        assertEquals(1633.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly15() {
        String expression = "2^2^2^2";
        assertEquals(65536.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly16() {
        String expression = "2^(2+3^2)";
        assertEquals(2048.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly17() {
        String expression = "((2-67)*3^(2-0)+((100*3)-100))*(0-1)";
        assertEquals(385.0, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly18() {
        String expression = "(1/5)^2+46";
        assertEquals(46.04, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test
    public void expressionEvaluationWorksCorrectly19() {
        String expression = "()^2++46";
        assertEquals(Double.NaN, inParser.expressionEvaluation(expression), 0.0001);
    }
    
    @Test 
    public void executeTheRightOperationCornerCase() {
        assertEquals(Double.NaN, inParser.executeTheRightOperation('?', 0, 0), 0.0001);
    }
    
    @Test
    public void stringIsAmathOperatorCornerCase() {
        assertFalse(inParser.stringIsAMathOperator("++"));
    }
    
    @Test
    public void stringIsANumberCornerCase1() {
        assertFalse(inParser.stringIsANumber(""));
    }
    
    @Test
    public void stringIsANumberCornerCase2() {
        assertFalse(inParser.stringIsANumber("6876."));
    }
    
    @Test
    public void postfixEvaluatorCornerCase1() {
        ArrayDeque<String> output = new ArrayDeque<>();
        assertEquals(Double.NaN, inParser.postfixEvaluator(output), 0.001);
    }
    
    @Test
    public void postfixEvaluatorCornerCase2() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("9");
        output.addLast("+");
        assertEquals(9.0, inParser.postfixEvaluator(output), 0.001);
    }
    
    @Test
    public void postfixEvaluatorCornerCase3() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("^");
        assertEquals(Double.NaN, inParser.postfixEvaluator(output), 0.001);
    }
    
    @Test
    public void postfixEvaluatorCornerCase4() {
        ArrayDeque<String> output = new ArrayDeque<>();
        output.addLast("7");
        assertEquals(7.0, inParser.postfixEvaluator(output), 0.001);
    }
    
    @Test
    public void expressionEvaluationCornerCase1() {
        assertEquals(Double.NaN, inParser.expressionEvaluation("8(+6"), 0.001);
    }
    
    @Test
    public void expressionEvaluationCornerCase2() {
        assertEquals(Double.NaN, inParser.expressionEvaluation("8(+6)"), 0.001);
    }
    
    @Test
    public void numbersAndBrackecketsCorrectCornerCase1() {
        assertFalse(inParser.numbersAndBracketsCorrect("8(+8"));
    }
    
    @Test
    public void numbersAndBrackecketsCorrectCornerCase2() {
        assertFalse(inParser.numbersAndBracketsCorrect(")8"));
    }
    
    @Test
    public void numbersAndBrackecketsCorrectCornerCase3() {
        assertFalse(inParser.numbersAndBracketsCorrect("7()8"));
    }
    
    @Test
    public void dotAndOperatorHelperCornerCase1() {
        assertFalse(inParser.dotAndOperatorHelper(true, false, '+', '*'));
    }
    
    @Test
    public void dotAndOperatorHelperCornerCase2() {
        assertFalse(inParser.dotAndOperatorHelper(true, false, '8', '*'));
    }
    
    @Test
    public void dotAndOperatorHelperCornerCase3() {
        assertFalse(inParser.dotAndOperatorHelper(true, false, '/', '6'));
    }
    
    @Test
    public void dotAndOperatorHelperCornerCase4() {
        assertFalse(inParser.dotAndOperatorHelper(false, true, '.', '.'));
    }
    
    @Test
    public void dotAndOperatorHelperImpossibleCase() {
        assertTrue(inParser.dotAndOperatorHelper(true, true, '8', '8'));
    }
    
    @Test
    public void shuntingYardCornerCase1() {
        
    }
}