package calculatorapp.logic;

import calculatorapp.functions.Abs;
import calculatorapp.functions.Addition;
import calculatorapp.functions.Base10Log;
import calculatorapp.functions.Cos;
import calculatorapp.functions.Division;
import calculatorapp.functions.Exponentiation;
import calculatorapp.functions.Function;
import calculatorapp.functions.Multiplication;
import calculatorapp.functions.NaturalLog;
import calculatorapp.functions.Negation;
import calculatorapp.functions.Percent;
import calculatorapp.functions.Sin;
import calculatorapp.functions.SquareRoot;
import calculatorapp.functions.Subtraction;
import calculatorapp.functions.Tan;
import java.util.HashMap;

/**
 * Provides all the Calculator operations in double precision.
 *
 * @author jpssilve
 */
public class CalculatorService {

    private HashMap<String, Function> functions;

    /**
     * Parameterless constructor.
     *
     */
    public CalculatorService() {
        functions = new HashMap<>();
        functions.put("+", new Addition());
        functions.put("-", new Subtraction());
        functions.put("*", new Multiplication());
        functions.put("/", new Division());
        functions.put("^", new Exponentiation());
        functions.put("%", new Percent());
        functions.put("sin", new Sin());
        functions.put("cos", new Cos());
        functions.put("tan", new Tan());
        functions.put("ln", new NaturalLog());
        functions.put("log", new Base10Log());
        functions.put("abs", new Abs());
        functions.put("sqrt", new SquareRoot());
        functions.put("neg", new Negation());
    }

    public double execute(String function, double[] arguments) {
        Function f = functions.getOrDefault(function, null);
        if (f == null) {
            return Double.NaN;
        }

        return f.calculate(arguments);
    }
}
