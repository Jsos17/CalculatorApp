/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.logic;

/**
 * The class encapsulates the Expression object/SQL table.
 *
 * @author jpssilve
 */
public class Expression {

    private int id;
    private String expression;

    /**
     * An Expression object is created based on values retrieved from a
     * database.
     *
     * @param id the primary key of the Expression in the database
     * @param expression the actual mathematical expression in String form
     */
    public Expression(int id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    /**
     *
     * @return the primary key of the Expression in the Expression table
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the "symbols" of the Expression i.e. the mathematical expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Overrides toString() method and is used to properly show expressions in
     * the lists of the gui.
     *
     * @return the expressions in String form
     */
    @Override
    public String toString() {
        return this.expression;
    }
}
