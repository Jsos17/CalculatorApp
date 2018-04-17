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
public class ExpressionMemoryTest {
    private CalculatorService calc;
    private InputParser inParser;
    private ExpressionMemory exprMem;
   
    @Before
    public void setUp() {
        inParser = new InputParser(calc);
        exprMem = new ExpressionMemory(inParser, 10);
    }
    
    @Test
    public void setMemoryWorks() {
        exprMem.setMemoryLimit(20);
        assertEquals(20, exprMem.getMemoryLimit());
    }
    
}
