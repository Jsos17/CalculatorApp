package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Percent implements Function {

    @Override
    public double calculate(double[] arguments) {
        return arguments[0] / 100.0;
    }
}
