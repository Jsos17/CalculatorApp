
package calculatorapp.domain;

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
    
    public double divideDouble(double x, double y) throws IllegalArgumentException {
        if (y == 0) {
            throw new  IllegalArgumentException("Divide by zero");
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
    
    public double naturalLog(long x) throws IllegalArgumentException {
        if (x <= 0) {
            throw new IllegalArgumentException();
        } else {
            return Math.log(x);
        }
    }
    
    public double base10log(long x) throws IllegalArgumentException {
        if (x <= 0) {
            throw new IllegalArgumentException();
        } else {
            return Math.log10(x);
        }
    }
}
