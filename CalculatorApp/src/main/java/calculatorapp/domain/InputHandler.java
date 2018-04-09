package calculatorapp.domain;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jpssilve
 */
public class InputHandler {

    private CalculatorService calculator;

    public InputHandler(CalculatorService calculator) {
        this.calculator = calculator;
    }

    public double evaluateExpressionDouble(String expression) {
        String[] operands = new String[expression.length()];
        ArrayList<Character> operators = new ArrayList<>();
        int k = 0;
        int j = 0;
        double result = 0;
        if (correctBracketing(expression) && correctOperatorPlacement(expression)) {
            for (int i = 0; i < expression.length(); i++) {
                char character = expression.charAt(i);
                if (isAMathOperator(character)) {
                    operators.add(character);
                    operands[k] = (expression.substring(j, i));
                    k++;
                    j = i + 1;
                } else if (i == expression.length() - 1) {
                    operands[k] = (expression.substring(j));
                    k++;
                }
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            result = callTheRightFunction(operators.get(i), Double.parseDouble(operands[i]), Double.parseDouble(operands[i + 1]));
            operands[i + 1] = "" + result;
        }

        return result;
    }

    public long evaluateExpressionLong(String expression) {
        return 0l;
    }

    public boolean correctBracketing(String expression) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            if (character == '(') {
                stack.push(character);
            } else if (character == ')') {
                if (!stack.empty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public boolean correctOperatorPlacement(String expression) {
        for (int i = 0; i < expression.length(); i++) {

            if (isAMathOperator(expression.charAt(i))) {
                if (i == 0 || i == expression.length() - 1) {
                    return false;
                }

                char before = expression.charAt(i - 1);
                char after = expression.charAt(i + 1);
                if (!isANumber(before) || !isANumber(after)) {
                    return false;
                }
            }
        }

        return true;
    }

    private double callTheRightFunction(char operator, double x1, double x2) {
        switch (operator) {
            case '+':
                return calculator.addDouble(x1, x2);
            case '-':
                return calculator.subtractDouble(x1, x2);
            case '*':
                return calculator.multiplyDouble(x1, x2);
            case '/':
                return calculator.divideDouble(x1, x2);
            default:
                return Double.NaN;
        }
    }

    public boolean isAFunction(String expression) {
        return false;
    }

    private boolean isAMathOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isANumber(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' | c == '6' || c == '7' || c == '8' || c == '9') {
            return true;
        } else {
            return false;
        }
    }
}
