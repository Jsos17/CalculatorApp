package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Base10Log implements Function {

    @Override
    public double calculate(double[] arguments) {
        if (arguments[0] <= 0) {
            return Double.NaN;
        }

        return Math.log10(arguments[0]);
    }

}
