/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayList;

/**
 * The class functions as the memory for CalculatorApp.
 *
 * @author jpssilve
 *
 */
public class ExpressionMemory {

    private ArrayList<String> memExpressions;
    private ExpressionEvaluator exprEval;
    private int memoryLimit;

    /**
     * The constructor gets an InputParser and an int value as parameters.
     * MemoryLimit tells how many expressions are kept in memory and thus shown
     * to the user.
     *
     * @param exprEval
     * @param memoryLimit
     */
    public ExpressionMemory(ExpressionEvaluator exprEval, int memoryLimit) {
        this.memExpressions = new ArrayList<>();
        this.exprEval = exprEval;
        this.memoryLimit = memoryLimit;
    }

    /**
     *
     * @return all expressions in memory as Strings
     */
    public ArrayList<String> getMemExpressionsArrayList() {
        return this.memExpressions;
    }

    /**
     * The method adds the expression it gets as a parameter into the ArrayList
     * that represents the memory.
     *
     * @param expression
     */
    public void addToMemory(String expression) {
        Double result = this.exprEval.expressionEvaluation(expression);
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            return;
        }

        this.memExpressions.add(expression);
        if (this.memExpressions.size() > this.memoryLimit) {
            this.memExpressions.remove(0);
        }
    }

    /**
     * Removes all entries from the ArrayList.
     */
    public void clearMemory() {
        this.memExpressions.clear();
    }

    /**
     * Returns current limit for how many expressions are kept in memory.
     *
     * @return
     */
    public int getMemoryLimit() {
        return this.memoryLimit;
    }

    /**
     * Sets the desired size for how many expressions are kept in memory.
     *
     * @param value
     */
    public void setMemoryLimit(int value) {
        if (value >= 0) {
            this.memoryLimit = value;
        }

        while (this.memExpressions.size() > this.memoryLimit) {
            this.memExpressions.remove(0);
        }
    }
}
