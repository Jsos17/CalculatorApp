/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jpssilve
 */
public class ExpressionEvaluator {

    private CalculatorService calculator;
    private InputParser inputParser;

    public ExpressionEvaluator(CalculatorService calculator, InputParser inputParser) {
        this.calculator = calculator;
        this.inputParser = inputParser;
    }

    protected boolean isAFunction(String maybeF) {
        return maybeF.equals("sqrt") || maybeF.equals("sin") || maybeF.equals("cos") || maybeF.equals("tan")
                || maybeF.equals("ln") || maybeF.equals("log");
    }

    protected boolean startsAFunction(char start) {
        return start == 's' || start == 'c' || start == 't' || start == 'l';
    }

    protected ArrayList<String> tokenizeExpression(String expression) {
        ArrayList<String> tokens = new ArrayList<>();

        int i = 0;
        int index = 0;
        while (i < expression.length()) {
            char character = expression.charAt(i);

            if (isANumber(character)) {
                index = i;
                while (i < expression.length() && (isANumber(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }

                if (stringIsANumber(expression.substring(index, i))) {
                    tokens.add(expression.substring(index, i));
                }

                continue;
            } else if (isAMathOperator(character) || character == '(' || character == ')') {
                tokens.add(Character.toString(character));
            } else if (startsAFunction(character)) {
                index = i;
                while (i < expression.length() && expression.charAt(i) != '(') {
                    i++;
                }

                if (isAFunction(expression.substring(index, i))) {
                    tokens.add(expression.substring(index, i));
                }
                continue;
            }

            i++;
        }

        return tokens;
    }

    protected ArrayDeque<String> shuntingYardWithFunctions(ArrayList<String> tokens) {
        ArrayDeque<String> outputQueue = new ArrayDeque();
        Stack<String> stack = new Stack();

        for (int j = 0; j < tokens.size(); j++) {
            String token = tokens.get(j);

            if (stringIsANumber(token)) {
                outputQueue.addLast(token);
            } else if (isAFunction(token) || token.equals("(")) {
                stack.push(token);
            } else if (stringIsAMathOperator(token)) {
                while (!stack.empty() && !stack.peek().equals("(") && (isAFunction(stack.peek()) || hasHigherPrecedence(stack.peek().charAt(0), token.charAt(0)))) {
                    outputQueue.addLast(stack.pop());
                }

                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    outputQueue.addLast(stack.pop());
                }

                if (!stack.empty()) {
                    stack.pop();
                }
            }
        }

        while (!stack.empty()) {
            outputQueue.addLast(stack.pop());
        }

        return outputQueue;
    }

    protected double postfixEvaluator(ArrayDeque<String> output) {
        Stack<Double> values = new Stack();
        while (!output.isEmpty()) {
            String mathObject = output.pollFirst();

            if (stringIsANumber(mathObject)) {
                values.push(Double.parseDouble(mathObject));
            } else if (isAFunction(mathObject)) {
                if (!values.empty()) {
                    double x = values.pop();
                    double value = executeTheRightFunction(mathObject, x);
                    values.push(value);
                }

            } else if (stringIsAMathOperator(mathObject)) {
                if (values.size() >= 2) {
                    double x1 = values.pop();
                    double x2 = values.pop();
                    double value = executeTheRightOperation(mathObject.charAt(0), x2, x1);
                    values.push(value);
                }
            }
        }

        if (values.size() == 1) {
            return values.pop();
        } else {
            return Double.NaN;
        }
    }

    public double expressionEvaluation(String expression) {
        if (expression.length() > 1000) {
            return Double.POSITIVE_INFINITY;
        } else if (this.inputParser.bracketingEquals(expression)) {
            ArrayDeque<String> queue = shuntingYardWithFunctions(tokenizeExpression(expression));
            return postfixEvaluator(queue);
        } else {
            return Double.NaN;
        }
    }

    protected double executeTheRightFunction(String function, double x) {
        switch (function) {
            case "sqrt":
                return this.calculator.squareRoot(x);
            case "sin":
                return this.calculator.sin(x);
            case "cos":
                return this.calculator.cos(x);
            case "tan":
                return this.calculator.tan(x);
            case "ln":
                return this.calculator.naturalLog(x);
            case "log":
                return this.calculator.base10log(x);
            default:
                return Double.NaN;
        }
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
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                || c == '6' || c == '7' || c == '8' || c == '9';
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

    protected double stringToNumber(String candidate) {
        if (candidate.length() == 0 || candidate.charAt(candidate.length() - 1) == '.') {
            return Double.NaN;
        } else {
            try {
                return Double.parseDouble(candidate);
            } catch (NumberFormatException e) {
                return Double.NaN;
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
