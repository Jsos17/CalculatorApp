package calculatorapp.functions;

/**
 *
 * @author jpssilve
 */
public class Sin implements Function {

    @Override
    public double calculate(double[] arguments) {
        return Math.sin(arguments[0]);
    }

}
