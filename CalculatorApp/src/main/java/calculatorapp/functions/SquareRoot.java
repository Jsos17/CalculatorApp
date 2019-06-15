package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class SquareRoot implements Function {

    @Override
    public double calculate(double[] arguments) {
        if (arguments[0] >= 0) {
            return Math.sqrt(arguments[0]);
        }

        return Double.NaN;
    }

}
