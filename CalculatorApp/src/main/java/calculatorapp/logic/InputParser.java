package calculatorapp.logic;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * The class InputParser is responsible for the evaluation of mathematical
 * expressions in String form.
 *
 * @author jpssilve
 */
public class InputParser {

    private CalculatorService calculator;

    /**
     * The constructor takes a CalculatorService as a parameter
     *
     * @param calculator
     */
    public InputParser(CalculatorService calculator) {
        this.calculator = calculator;
    }

    /**
     * Djikstra's shunting-yard algorithm implementation. The pseudocode found
     * on shunting-yard wikipedia page is the basis for implementation.
     *
     * The method takes a String and produces the mathematical expression in
     * reverse Polish notation also known as postfix notation.
     *
     * @param expression
     * @return
     */
    protected ArrayDeque<String> shuntingYard(String expression) {
        ArrayDeque<String> output = new ArrayDeque();
        Stack<Character> stack = new Stack();

        int i = 0;
        int index = 0;
        while (i < expression.length()) {
            char character = expression.charAt(i);
            if (isANumber(character)) {
                index = i;
                while (i < expression.length() && (isANumber(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }
                output.addLast(expression.substring(index, i));
                continue;
            } else if (isAMathOperator(character)) {
                while (!stack.empty() && hasHigherPrecedence(stack.peek(), character) && stack.peek() != '(') {
                    output.addLast(Character.toString(stack.pop()));
                }
                stack.push(character);
            } else if (character == '(') {
                stack.push(character);
            } else if (character == ')') {
                while (!stack.empty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                    output.addLast(Character.toString(stack.pop()));
                }
            }

            i++;
        }

        return shuntingYardHelper(output, stack);
    }

    private ArrayDeque<String> shuntingYardHelper(ArrayDeque<String> output, Stack<Character> stack) {
        while (!stack.empty()) {
            output.addLast(Character.toString(stack.pop()));
        }

        return output;
    }

    /**
     * This method evaluates a mathematical expression in reverse Polish
     * notation also known as postfix notation.
     *
     * The pseudocode found on the reverse Polish notation wikipedia page is the
     * basis for implementation.
     *
     * @param output
     * @return
     */
    protected double postfixEvaluator(ArrayDeque<String> output) {
        Stack<Double> values = new Stack();
        while (!output.isEmpty()) {
            String mathObject = output.pollFirst();

            if (stringIsANumber(mathObject)) {
                values.push(Double.parseDouble(mathObject));
            } else if (stringIsAMathOperator(mathObject)) {
                if (values.size() >= 2) {
                    double x1 = values.pop();
                    double x2 = values.pop();
                    double value = executeTheRightOperation(mathObject.charAt(0), x2, x1);
                    values.push(value);
                }
            }
        }

        return postfixStackChecker(values);
    }

    private double postfixStackChecker(Stack<Double> values) {
        if (values.size() == 1) {
            return values.pop();
        } else {
            return Double.NaN;
        }
    }

    /**
     * The method evaluates a mathematical expression in String form if it can
     * be parsed.
     *
     * The method calls several methods of the same class.
     *
     * @param expression
     *
     * @return Double.NaN if the expression cannot be parsed, positive infinity
     * if the expression is too long and a double value if the expression can be
     * parsed
     */
    public double expressionEvaluation(String expression) {
        if (expression.length() > 1000) {
            return Double.POSITIVE_INFINITY;
        } else if (bracketingEquals(expression) && numbersAndBracketsCorrect(expression)
                && correctOperatorAndDotPlacement(expression)) {
            ArrayDeque<String> queue = shuntingYard(expression);
            return postfixEvaluator(queue);
        } else {
            return Double.NaN;
        }
    }

    protected boolean bracketingEquals(String expression) {
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

    protected boolean numbersAndBracketsCorrect(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char c1 = expression.charAt(i);
            char c2 = expression.charAt(i + 1);

            if ((c1 == '(' && c2 == ')') || (c1 == ')' && c2 == '(')) {
                return false;
            } else if ((isANumber(c1) && c2 == '(') || c1 == ')' && isANumber(c2)) {
                return false;
            }
        }

        return true;
    }

    protected boolean correctOperatorAndDotPlacement(String expression) {
        int index = 0;
        int i = 0;
        while (i < expression.length()) {
            char character = expression.charAt(i);
            if (isAMathOperator(character) || character == '.') {
                if (i == 0 || i == expression.length() - 1) {
                    return false;
                } else if (!dotAndOperatorHelper(isAMathOperator(character), character == '.', expression.charAt(i - 1), expression.charAt(i + 1))) {
                    return false;
                }
            } else if (isANumber(character)) {
                index = i;
                while (i < expression.length() && (isANumber(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }

                if (stringIsANumber(expression.substring(index, i))) {
                    continue;
                } else {
                    return false;
                }
            }

            i++;
        }

        return true;
    }

    protected boolean dotAndOperatorHelper(boolean mathOp, boolean isDot, char before, char after) {
        if (mathOp && ((!isANumber(before) && before != ')') || (!isANumber(after) && after != '('))) {
            return false;
        }

        if (isDot && (before == '.' || after == '.')) {
            return false;
        }

        return true;
    }

    protected double executeTheRightOperation(char operator, double x1, double x2) {
        switch (operator) {
            case '+':
                return this.calculator.addDouble(x1, x2);
            case '-':
                return this.calculator.subtractDouble(x1, x2);
            case '*':
                return this.calculator.multiplyDouble(x1, x2);
            case '/':
                return this.calculator.divideDouble(x1, x2);
            case '^':
                return this.calculator.exponentiation(x1, x2);
            default:
                return Double.NaN;
        }
    }

    private boolean isAMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean isANumber(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    protected boolean stringIsAMathOperator(String candidate) {
        if (candidate.length() == 1) {
            return isAMathOperator(candidate.charAt(0));
        } else {
            return false;
        }
    }

    protected boolean stringIsANumber(String candidate) {
        if (candidate.length() == 0 || candidate.charAt(candidate.length() - 1) == '.') {
            return false;
        } else {
            try {
                Double.parseDouble(candidate);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    private boolean hasHigherPrecedence(char c1, char c2) {
        if (c1 == '^' && c2 != '^') {
            return true;
        } else if ((c1 == '*' || c1 == '/') && c2 != '^') {
            return true;
        } else if ((c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-')) {
            return true;
        } else {
            return false;
        }
    }
}
