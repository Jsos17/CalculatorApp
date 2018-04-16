/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jpssilve
 */
public class ExpressionMemory {
    private ObservableList<String> memExpressions;
    private InputParser inParser;
    private int memoryLimit;
    
    public ExpressionMemory(InputParser inParser, int memoryLimit) {
        this.memExpressions = FXCollections.observableArrayList();
        this.inParser = inParser;
        this.memoryLimit = memoryLimit;
    }

    public ObservableList<String> getMemExpressions() {
        return this.memExpressions;
    }
    
    public List<String> getMemExpressionsArrayList() {
        return Collections.unmodifiableList(memExpressions);
    }
    
    public void addToMemory(String expression) {
        if (this.inParser.expressionEvaluation(expression) != Double.NaN) {
            this.memExpressions.add(expression);
        }
        
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
