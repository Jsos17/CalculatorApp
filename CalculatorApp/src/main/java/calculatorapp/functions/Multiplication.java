package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Multiplication implements Function {

    @Override
    public double calculate(double[] arguments) {
        return arguments[0] * arguments[1];
    }

}
