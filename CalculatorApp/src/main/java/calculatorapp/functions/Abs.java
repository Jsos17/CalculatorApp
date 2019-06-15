package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Abs implements Function {

    @Override
    public double calculate(double[] arguments) {
        return Math.abs(arguments[0]);
    }

}
