package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Tan implements Function {

    @Override
    public double calculate(double[] arguments) {
        return Math.tan(arguments[0]);
    }

}
