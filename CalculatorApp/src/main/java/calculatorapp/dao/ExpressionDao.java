/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import java.util.List;

/**
 *
 * @author jpssilve
 */
public interface ExpressionDao {
    
    boolean save(String expression);
    
    List<String> findAll();
    
    boolean deleteLast(String expression);
    
    boolean deleteAll();
    
    int countExpressionsInDatabase();
    
}
