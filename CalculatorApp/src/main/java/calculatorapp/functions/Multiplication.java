/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
