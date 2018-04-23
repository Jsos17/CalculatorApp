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
public class ExpressionTest {
    
    private Expression e;
    
    @Before
    public void setUp() {
        e = new Expression(42, "42*7^5");
    }
    
    @Test
    public void getIdTest() {
        assertEquals(42, e.getId());
    }
    
    @Test
    public void setIdTest() {
        e.setId(37);
        assertEquals(37, e.getId());
    }
    
    @Test
    public void getExpressionTest() {
        assertEquals("42*7^5", e.getExpression());
    }
    
    @Test
    public void setExpressionTest() {
        e.setExpression("3*6");
        assertEquals("3*6", e.getExpression());
    } 
}
