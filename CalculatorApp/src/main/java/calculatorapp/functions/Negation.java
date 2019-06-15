package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Negation implements Function {

    @Override
    public double calculate(double[] arguments) {
        return -1 * arguments[0];
    }

}
