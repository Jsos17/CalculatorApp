package calculatorapp.logic;

import java.util.Stack;

/**
 * The class InputParser is responsible for checking that the input is valid
 * before it is evaluated by ExpressionEvaluator.
 * 
 * Among notable methods are checking that bracketing is equal
 * @see  bracketingEquals
 *
 * @author jpssilve
 */
public class InputParser {

    /**
     * The constructor gets no parameters.
     *
     */
    public InputParser() {
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
        if (mathOp && ((!isANumber(before) && before != ')') || (!isANumber(after) && !(startsAFunction(after)) && after != '('))) {
            return false;
        }

        return !(isDot && (before == '.' || after == '.'));
    }

    protected boolean isAFunction(String maybeFunc) {
        return maybeFunc.equals("sqrt") || maybeFunc.equals("sin") || maybeFunc.equals("cos") || maybeFunc.equals("tan")
                || maybeFunc.equals("ln") || maybeFunc.equals("log") || maybeFunc.equals("abs") || maybeFunc.equals("neg") || maybeFunc.equals("%");
    }

    protected boolean startsAFunction(char start) {
        return start == 's' || start == 'c' || start == 't' || start == 'l' || start == 'a' || start == 'n' || start == '%';
    }

    protected boolean isAMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    protected boolean isANumber(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
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
}
