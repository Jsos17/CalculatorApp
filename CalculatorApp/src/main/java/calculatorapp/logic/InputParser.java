package calculatorapp.logic;

import java.util.Stack;

/**
 * The class InputParser is responsible for checking that the input is valid
 * before it is evaluated by ExpressionEvaluator.
 *
 * @author jpssilve
 */
public class InputParser {

    /**
     * The constructor receives no parameters.
     *
     */
    public InputParser() {
    }

    /**
     * Checks that the functions inputted by the user are valid. This is done
     * because the user can delete characters and could thus input values like
     * tantan(6), which otherwise would produce the value 6 because of the way
     * expressions are evaluated.
     *
     * @param expression the mathematical expression to be checked as a String
     * @return true or false based on whether the expression is valid or not
     */
    protected boolean correctFunctions(String expression) {
        int index = 0;
        int i = 0;
        while (i < expression.length()) {
            if (startsAFunction(expression.charAt(i))) {
                index = i;
                while (i < expression.length() && expression.charAt(i) != '(') {
                    i++;
                }

                if (isAFunction(expression.substring(index, i))) {
                    continue;
                } else {
                    return false;
                }
            }

            i++;
        }

        return true;
    }

    /**
     * Checks that all opened brackets are closed properly.
     *
     * @param expression the mathematical expression to be checked as a String
     * @return a boolean value
     */
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

    /**
     * Checks that numbers are placed correctly among the brackets, i.e.
     * brackets containing nothing not allowed, numbers after right brackets not
     * allowed, right brackets followed by anything other than other right
     * brackets or mathematical oerators not allowed.
     *
     * @param expression the mathematical expression to be checked as a String
     * @return true or false
     */
    protected boolean numbersAndBracketsCorrect(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char c1 = expression.charAt(i);
            char c2 = expression.charAt(i + 1);

            if ((c1 == '(' && c2 == ')') || (c1 == ')' && c2 == '(')) {
                return false;
            } else if ((isANumber(c1) && c2 == '(')
                    || (c1 == ')' && !isAMathOperator(c2) && c2 != ')')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks that dots and operators are placed correctly.
     *
     * @param expression the mathematical expression to be checked as a String
     * @return true or false
     */
    protected boolean correctOperatorAndDotPlacement(String expression) {
        boolean[] shouldContinue = new boolean[1];
        shouldContinue[0] = true;

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (isAMathOperator(character)
                    && ((i == 0 || i == expression.length() - 1) || !operatorHelper(expression.charAt(i - 1), expression.charAt(i + 1)))) {
                return false;

            } else if (character == '.'
                    && ((i == 0 || i == expression.length() - 1) || expression.charAt(i + 1) == '.')) {
                return false;

            } else if (isANumber(character)) {
                i = numberHelper(expression, i, shouldContinue);

                if (!shouldContinue[0]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * A helper function for the correctOperatorAndDotPlacement method.
     *
     * @param before the char immediately before the current charAt(i) in the
     * expression
     * @param after the char immediately after the current charAt(i) in the
     * expression
     * @return true or false
     */
    protected boolean operatorHelper(char before, char after) {
        if ((!isANumber(before) && before != ')')) {
            return false;
        }

        return isANumber(after) || startsAFunction(after) || after == '(';
    }

    private int numberHelper(String expression, int i, boolean[] shouldContinue) {
        int index = i;
        while (i < expression.length() && (isANumber(expression.charAt(i)) || expression.charAt(i) == '.')) {
            i++;
        }

        shouldContinue[0] = stringIsANumber(expression.substring(index, i));

        return i - 1;
    }

    /**
     * Checks whether the input is among the supported functions
     *
     * @param maybeFunc a function candidate in String form
     * @return true or false
     */
    protected boolean isAFunction(String maybeFunc) {
        return maybeFunc.equals("sqrt") || maybeFunc.equals("sin") || maybeFunc.equals("cos") || maybeFunc.equals("tan")
                || maybeFunc.equals("ln") || maybeFunc.equals("log") || maybeFunc.equals("abs") || maybeFunc.equals("neg") || maybeFunc.equals("%");
    }

    /**
     * Checks if the function candidate starts with the first letter of the
     * currently supported functions.
     *
     * @param firstLetter the first letter of the function candidate
     * @return true or false
     */
    protected boolean startsAFunction(char firstLetter) {
        return firstLetter == 's' || firstLetter == 'c' || firstLetter == 't' || firstLetter == 'l' || firstLetter == 'a' || firstLetter == 'n' || firstLetter == '%';
    }

    /**
     * Checks whether the parameter is a mathematical operator that is currently
     * supported.
     *
     * @param c the candidate as a char
     * @return true or false
     */
    protected boolean isAMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * Checks whether the parameter is a single digit number
     *
     * @param c a number candidate
     * @return true or false
     */
    protected boolean isANumber(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    /**
     * Checks whether the parameter String can be converted to a double value.
     *
     * @param candidate as a String
     * @return true or false
     */
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
