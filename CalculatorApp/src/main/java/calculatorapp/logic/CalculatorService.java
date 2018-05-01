package calculatorapp.logic;

/**
 * Provides all the Calculator operations
 *
 * @author jpssilve
 */
public class CalculatorService {

    public CalculatorService() {
    }

    /**
     *
     * @param x
     * @param y
     * @return double precision addition
     */
    public double addDouble(double x, double y) {
        return x + y;
    }

    /**
     *
     * @param x
     * @param y
     * @return double precision subtraction
     */
    public double subtractDouble(double x, double y) {
        return x - y;
    }

    /**
     *
     * @param x
     * @param y
     * @return double precision multiplication
     */
    public double multiplyDouble(double x, double y) {
        return x * y;
    }

    /**
     *
     * @param x
     * @param y
     * @return double precision division
     */
    public double divideDouble(double x, double y) {
        if (y == 0) {
            return Double.NaN;
        } else {
            return x / y;
        }
    }

    /**
     *
     * @param x
     * @return a number as a percentage
     */
    public double percent(double x) {
        return x / 100.0;
    }

    /**
     *
     * @param x
     * @param y
     * @return the x modulo y
     */
    public long modulo(long x, long y) {
        return x % y;
    }

    /**
     *
     * @param x
     * @return the natural logarithm of a number
     */
    public double naturalLog(double x) {
        if (x <= 0) {
            return Double.NaN;
        } else {
            return Math.log(x);
        }
    }

    /**
     *
     * @param x
     * @return the base 10 logarithm of a number
     */
    public double base10log(double x) {
        if (x <= 0) {
            return Double.NaN;
        } else {
            return Math.log10(x);
        }
    }

    /**
     *
     * @param x
     * @return the absolute value of a number
     */
    public double abs(double x) {
        return Math.abs(x);
    }

    /**
     * @param base
     * @param exponent
     * @return a number raised to the desired power
     */
    public double exponentiation(double base, double exponent) {
        if (base == 0 && exponent == 0) {
            return Double.NaN;
        } else {
            return Math.pow(base, exponent);
        }
    }

    /**
     * Returns the square root of a number if it is non-negative.
     *
     * @param x
     * @return
     */
    public double squareRoot(double x) {
        if (x >= 0) {
            return Math.sqrt(x);
        } else {
            return Double.NaN;
        }
    }

    /**
     *
     * @param x is in radians
     * @return the sin value of the radian parameter
     */
    public double sin(double x) {
        return Math.sin(x);
    }

    /**
     *
     * @param x is in radians
     * @return the cos value of the radian parameter
     */
    public double cos(double x) {
        return Math.cos(x);
    }

    /**
     * Due to double precision, situations where cos x = 0 in tan x = sin x /
     * cos x should not arise.
     *
     * @param x is in radians
     * @return
     */
    public double tan(double x) {
        return Math.tan(x);
    }

    /**
     * This function return the parameter multiplied by two.
     *
     * @param x
     * @return the negation of a number
     */
    public double negate(double x) {
        return -1 * x;
    }
}
