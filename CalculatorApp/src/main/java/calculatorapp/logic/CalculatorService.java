
package calculatorapp.logic;

/**
 *
 * @author jpssilve
 */
public class CalculatorService {
    
    public CalculatorService() {
    }
    
    public long addLong(long x, long y) {
        return x + y;
    }
    
    public long subtractLong(long x, long y) {
        return x - y;
    }
    
    public long multiplyLong(long x, long y) {
        return x * y;
    }
    
    public double addDouble(double x, double y) {
        return x + y;
    }
    
    public double subtractDouble(double x, double y) {
        return x - y;
    }
    
    public double multiplyDouble(double x, double y) {
        return x * y;
    }
    
    public double divideDouble(double x, double y) {
        if (y == 0) {
            return Double.NaN;
        } else {
            return  x / y;
        }
    }
    
    public double percent(double x) {
        return x * 100.0;
    }
    
    public long modulo(long x, long y) {
        return x % y;
    }
    
    public double naturalLog(long x) {
        if (x <= 0) {
            return Double.NaN;
        } else {
            return Math.log(x);
        }
    }
    
    public double base10log(long x) {
        if (x <= 0) {
            return Double.NaN;
        } else {
            return Math.log10(x);
        }
    }
    
    public double abs(double x) {
        return Math.abs(x);
    }
    
    public double exp(double base, double exponent) {
        if (base == 0 && exponent == 0) {
            return Double.NaN;
        } else {
            return Math.pow(base, exponent);
        }
    }
}
