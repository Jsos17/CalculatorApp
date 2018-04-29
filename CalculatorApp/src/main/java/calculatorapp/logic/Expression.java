/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

/**
 *The class encapsulates the Expression object
 * @author jpssilve
 */
public class Expression {
    private int id;
    private String expression;
    /**
     * An Expression object is created based on values retrieved from a database.
     * @param id
     * @param expression 
     */
    public Expression(int id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    /**
     * 
     * @return the id of the Expression in the Expression table 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return the "symbols" of the Expression 
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets the id of the Expression
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the actual symbols contained in the expressions
     * 
     * @param expression 
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    /**
     * Overrides toString() method
     * 
     * @return the expressions in String form  
     */
    @Override
    public String toString() {
        return this.expression;
    }
}
