package calculatorapp.domain;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 *
 * @author jpssilve
 */
public class InputParser {

    private CalculatorService calculator;

    public InputParser(CalculatorService calculator) {
        this.calculator = calculator;
    }

    // @Djikstra as source and wikipedia pseudo-code as basis for implementation
    private ArrayDeque<String> shuntingYard(String expression) {
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

        while (!stack.empty()) {
            output.addLast(Character.toString(stack.pop()));
        }

        return output;
    }

    // wikipedia pseudo-code as basis for implementation
    private double postfixEvaluator(ArrayDeque<String> queue) {
        Stack<Double> values = new Stack();

        while (!queue.isEmpty()) {
            String mathObject = queue.pollFirst();

            if (stringIsANumber(mathObject)) {
                values.push(Double.parseDouble(mathObject));
            } else if (stringIsAMathOperator(mathObject)) {
                char operator = mathObject.charAt(0);
                if (values.size() >= 2) {
                    double x1 = values.pop();
                    double x2 = values.pop();
                    double value = callTheRightFunction(operator, x2, x1);
                    values.push(value);
                } else {
                    return Double.NaN;
                }
            }
        }

        if (!values.empty()) {
            return values.pop();
        } else {
            return Double.NaN;
        }
    }

    public double expressionEvaluation(String expression) {
        if (bracketingEquals(expression) && numbersAndBracketsCorrect(expression)
                && correctOperatorAndDotPlacement(expression)) {
            ArrayDeque<String> queue = shuntingYard(expression);
            return postfixEvaluator(queue);
        } else {
            return Double.NaN;
        }
    }

    public boolean bracketingEquals(String expression) {
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

    public boolean numbersAndBracketsCorrect(String expression) {
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

    public boolean correctOperatorAndDotPlacement(String expression) {
        int index = 0;
        int i = 0;
        while (i < expression.length()) {
            char character = expression.charAt(i);
            boolean mathOp = isAMathOperator(character);
            boolean isDot = (character == '.');

            if (mathOp || isDot) {
                if (i == 0 || i == expression.length() - 1) {
                    return false;
                }

                char before = expression.charAt(i - 1);
                char after = expression.charAt(i + 1);
                if (mathOp && ((!isANumber(before) && before != ')') || (!isANumber(after) && after != '('))) {
                    return false;
                }

                if (isDot && (before == '.' || after == '.')) {
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

    private double callTheRightFunction(char operator, double x1, double x2) {
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
                return this.calculator.exp(x1, x2);
            default:
                return Double.NaN;
        }
    }

    private boolean isAMathOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isANumber(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                || c == '5' | c == '6' || c == '7' || c == '8' || c == '9') {
            return true;
        } else {
            return false;
        }
    }

    private boolean stringIsAMathOperator(String candidate) {
        if (candidate.length() == 1) {
            return isAMathOperator(candidate.charAt(0));
        } else {
            return false;
        }
    }

    private boolean stringIsANumber(String candidate) {
        if (candidate.length() == 0 || candidate.charAt(candidate.length() - 1) == '.') {
            return false;
        } else {
            try {
                Double.parseDouble(candidate);
                return true;
            } catch (Exception e) {
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
