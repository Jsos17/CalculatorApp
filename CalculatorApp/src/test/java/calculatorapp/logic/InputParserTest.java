/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpssilve
 */
public class InputParserTest {

    private InputParser inParser;

    @Before
    public void setUp() {
        inParser = new InputParser();
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
    public void numbersBracketsCheckedCorrectly2() {
        String expression = "((((((((((((((((((((((((((6))))))))))))))))))))))))))";
        assertTrue(inParser.numbersAndBracketsCorrect(expression));
    }

    @Test
    public void numbersBracketsCheckedCorrectly3() {
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
    public void checksDotPlacementCorrectly8() {
        String expression = "6*(67+7...0)-(8+9)/8";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
    }

    @Test
    public void checksDotPlacementCorrectly9() {
        String expression = "6.6";
        assertTrue(inParser.correctOperatorAndDotPlacement(expression));
    }

    @Test
    public void checksDotPlacementCorrectly10() {
        String expression = ".9.";
        assertFalse(inParser.correctOperatorAndDotPlacement(expression));
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
        assertFalse(inParser.operatorHelper('+', '*'));
    }
//
//    @Test
//    public void dotAndOperatorHelperCornerCase2() {
//        assertFalse(inParser.dotAndOperatorHelper(true, false, '8', '*'));
//    }
//
//    @Test
//    public void dotAndOperatorHelperCornerCase3() {
//        assertFalse(inParser.dotAndOperatorHelper(true, false, '/', '6'));
//    }

    @Test
    public void stringIsANumberCornerCase1() {
        assertFalse(inParser.stringIsANumber(""));
    }

    @Test
    public void stringIsANumberCornerCase2() {
        assertFalse(inParser.stringIsANumber("6876."));
    }
}
