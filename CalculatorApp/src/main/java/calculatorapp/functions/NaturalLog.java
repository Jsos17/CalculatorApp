package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class NaturalLog implements Function {

    @Override
    public double calculate(double[] arguments) {
        if (arguments[0] <= 0) {
            return Double.NaN;
        }
        return Math.log(arguments[0]);
    }

}
