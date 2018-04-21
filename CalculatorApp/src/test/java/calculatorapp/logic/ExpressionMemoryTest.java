/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jpssilve
 */
public class ExpressionMemoryTest {
    private CalculatorService calc;
    private InputParser inParser;
    private ExpressionMemory exprMem;
   
    @Before
    public void setUp() {
        calc = new CalculatorService();
        inParser = new InputParser(calc);
        exprMem = new ExpressionMemory(inParser, 10);
    }
    
    @Test
    public void setMemoryWorks1() {
        exprMem.setMemoryLimit(20);
        assertEquals(20, exprMem.getMemoryLimit());
    }
    
    @Test
    public void setMemoryWorks2() {
        exprMem.setMemoryLimit(-50);
        assertEquals(10, exprMem.getMemoryLimit());
    }
    
    @Test
    public void addToMemoryAddsToListSize1() {
        exprMem.addToMemory("56");
        assertEquals(1, exprMem.getMemExpressionsArrayList().size());
    }
    
    @Test
    public void addToMemoryReturnsRightValue1() {
        exprMem.addToMemory("42");
        assertEquals("42", exprMem.getMemExpressionsArrayList().get(0));
    }
    
    @Test
    public void addToMemoryReturnsRightValue2() {
        for (int i = 0; i <= 8; i++) {
            exprMem.addToMemory(i + "");
        }
        exprMem.addToMemory("(1/5)^2+46");
        assertEquals("(1/5)^2+46", exprMem.getMemExpressionsArrayList().get(9));
    }
    
    @Test
    public void addToMemoryReturnsRightValue3() {
        ArrayList<String> compares = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            compares.add("" + i * 2);
            exprMem.addToMemory("" + i * 2);
        }

        for (int i = 0; i < compares.size(); i++) {
            assertEquals(compares.get(i), exprMem.getMemExpressionsArrayList().get(i));
        }   
    }
    
    @Test
    public void addToMemoryReturnsRightValue4() {
        ArrayList<String> compares = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            compares.add("" + i * 2);
            exprMem.addToMemory("" + i * 2);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(compares.get(i + 10), exprMem.getMemExpressionsArrayList().get(i));
        }   
    }
    
    @Test
    public void addToMemoryReturnsRightValue5() {
        ArrayList<String> compares = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            compares.add("" + i * 2);
            exprMem.addToMemory("" + i * 2);
        }

        for (int i = 0; i < 10; i++) {
            assertNotEquals(compares.get(i), exprMem.getMemExpressionsArrayList().get(i));
        }   
    }
    
    @Test
    public void addToMemoryAddsOnlyValidInputs1() {
        exprMem.addToMemory("(1/5)^2+46");
        exprMem.addToMemory("()^2++46");
        exprMem.addToMemory("-9-0");
        exprMem.addToMemory("2+46");
        exprMem.addToMemory("-9-0");
        assertEquals("2+46", exprMem.getMemExpressionsArrayList().get(1));
    }
    
    @Test
    public void addToMemoryAddsOnlyValidInputs2() {
        exprMem.addToMemory("()^2-46*-1");
        exprMem.addToMemory("()^2++46");
        exprMem.addToMemory("-5*5");
        exprMem.addToMemory("()");
        exprMem.addToMemory("-9-0");
        assertEquals(0, exprMem.getMemExpressionsArrayList().size());
    }
    
    @Test
    public void addToMemoryHonorsLimit1() {
        for (int i = 0; i < 20; i++) {
            exprMem.addToMemory(i + "");
        }
        
        assertEquals(10, exprMem.getMemExpressionsArrayList().size());
    }
    
    @Test
    public void addToMemoryHonorsLimit2() {
        exprMem.setMemoryLimit(0);
        exprMem.addToMemory("1+2");
        assertEquals(0, exprMem.getMemExpressionsArrayList().size());
    }
    
    @Test
    public void addToMemoryHonorsLimit3() {
        for (int i = 0; i <= 9; i++) {
            exprMem.addToMemory(10 * i + "");
        }
        exprMem.addToMemory("110");
        assertEquals("10", exprMem.getMemExpressionsArrayList().get(0));
    }
    
    @Test
    public void clearMemoryWorks() {
        for (int i = 0; i <= 9; i++) {
            exprMem.addToMemory(10 * i + "");
        }
        
        exprMem.clearMemory();
        assertEquals(0, exprMem.getMemExpressionsArrayList().size());
    }
    
}
