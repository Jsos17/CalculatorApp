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
public class Sin implements Function {

    @Override
    public double calculate(double[] arguments) {
        return Math.sin(arguments[0]);
    }

}
