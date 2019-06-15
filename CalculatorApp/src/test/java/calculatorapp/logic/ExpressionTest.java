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
    public void getExpressionTest() {
        assertEquals("42*7^5", e.getExpression());
    }

    @Test
    public void toStringAsExpected1() {
        assertEquals("42*7^5", e.toString());
    }

    @Test
    public void toStringAsExpected2() {
        Expression e2 = new Expression(1, "(2^2^2^2)*1.5");
        assertEquals("(2^2^2^2)*1.5", e2.toString());
    }

    @Test
    public void toStringAsExpected3() {
        Expression eLong = new Expression(423, "((5.175+5.7)*2/3+8^3.2-6*(27/127)*45-20000+1.4^2-32^0.2+9/4.8+1011.76^1.2+42-56/0.42-45+56)*1.34+2^2.5-5-100+663/123*544*6/1002+((8.105+5.7)*2/3+8^3.9-6*(27/127)*45-10000+1.4^2-67^0.1+6/4.8+1011.76^1.2+56-56/0.123+3456)*0.45-6");
        assertEquals("((5.175+5.7)*2/3+8^3.2-6*(27/127)*45-20000+1.4^2-32^0.2+9/4.8+1011.76^1.2+42-56/0.42-45+56)*1.34+2^2.5-5-100+663/123*544*6/1002+((8.105+5.7)*2/3+8^3.9-6*(27/127)*45-10000+1.4^2-67^0.1+6/4.8+1011.76^1.2+56-56/0.123+3456)*0.45-6", eLong.toString());
    }
}
