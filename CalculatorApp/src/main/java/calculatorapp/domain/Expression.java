/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.domain;

/**
 *
 * @author jpssilve
 */
public class Expression {
    private int id;
    private String expression;
    
    public Expression(int id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    public int getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
