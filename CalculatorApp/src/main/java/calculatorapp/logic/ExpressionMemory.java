/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

import java.util.ArrayList;

/**
 *
 * @author jpssilve
 */
public class ExpressionMemory {
    private ArrayList<String> memExpressions;
    private InputParser inParser;
    private int memoryLimit;
    
    public ExpressionMemory(InputParser inParser, int memoryLimit) {
        this.memExpressions = new ArrayList<>();
        this.inParser = inParser;
        this.memoryLimit = memoryLimit;
    }

    public ArrayList<String> getMemExpressionsArrayList() {
        return this.memExpressions;
    }
    
    public void addToMemory(String expression) {
        if (Double.isNaN(this.inParser.expressionEvaluation(expression))) {
            return;
        }
        
        this.memExpressions.add(expression);
        if (this.memExpressions.size() > this.memoryLimit) {
            this.memExpressions.remove(0);
        }
    }
    
    public void clearMemory() {
        this.memExpressions.clear();
    }

    public int getMemoryLimit() {
        return this.memoryLimit;
    }
    
    public void setMemoryLimit(int value) {
        if (value >= 0) {
            this.memoryLimit = value;
        }
    }
}
