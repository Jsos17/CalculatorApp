package calculatorapp.logic;

/**
 * Provides all the Calculator operations in double precision.
 *
 * @author jpssilve
 */
public class CalculatorService {

    /**
     * Parameterless constructor.
     *
     */
    public CalculatorService() {
    }

    /**
     * The sum of two numbers in double precision.
     *
     * @param x a double value
     * @param y a double value
     * @return double precision addition
     */
    public double addDouble(double x, double y) {
        return x + y;
    }

    /**
     * Subtracts y from x in double precision.
     *
     * @param x a double value
     * @param y a double value
     * @return double precision subtraction
     */
    public double subtractDouble(double x, double y) {
        return x - y;
    }

    /**
     * Basic double precision multiplication.
     *
     * @param x a double value
     * @param y a double value
     * @return double precision multiplication
     */
    public double multiplyDouble(double x, double y) {
        return x * y;
    }

    /**
     * Basic double precision division.
     *
     * @param x a double value
     * @param y a double value
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
     * Calculates a percentage value in decimal form.
     *
     * @param x a double value
     * @return a percentage as decimal value
     */
    public double percent(double x) {
        return x / 100.0;
    }

    /**
     * Calculates the natural logarithm of a number, if it is non-negative.
     *
     * @param x a double value
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
     * Calculates the base 10 logarithm of a number, if it is non-negative.
     *
     * @param x a double value
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
     * Calculates the absolute value of a number.
     *
     * @param x a double value
     * @return the absolute value of a number
     */
    public double abs(double x) {
        return Math.abs(x);
    }

    /**
     * Raises a number (base) to the desired power.
     *
     * @param base to be used as a double alue
     * @param exponent as a double value
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
     * Calculates the square root of a number in double precision, if it is
     * non-negative or else returns NaN.
     *
     * @param x is any double value
     * @return the square root of a number if it is non-negative.
     */
    public double squareRoot(double x) {
        if (x >= 0) {
            return Math.sqrt(x);
        } else {
            return Double.NaN;
        }
    }

    /**
     * The sine value of a double number in double precision.
     *
     * @param x is a double value in radians
     * @return the sin value of the radian parameter
     */
    public double sin(double x) {
        return Math.sin(x);
    }

    /**
     * The cosine value of a double number in double precision.
     *
     * @param x is a double value in radians
     * @return the cos value of the radian parameter
     */
    public double cos(double x) {
        return Math.cos(x);
    }

    /**
     * Due to double precision, situations where cos x = 0 in tan x = sin x /
     * cos x should not arise i.e. divide by zero should not arise since it
     * would require pi / 2 type of value which cannot be achieved exactly.
     *
     * @param x is a double value in radians
     * @return the tan value of the radian parameter
     */
    public double tan(double x) {
        return Math.tan(x);
    }

    /**
     * This function returns the parameter multiplied by minus 1 and mainly
     * serves to provide handling of negative numbers in expression evaluation.
     *
     * @param x a double value
     * @return the negation of a number
     */
    public double negate(double x) {
        return -1 * x;
    }
}
