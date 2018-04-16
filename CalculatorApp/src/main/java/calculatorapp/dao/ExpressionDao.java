/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jpssilve
 */
public interface ExpressionDao {
    
    boolean save(String expression) throws SQLException;
    
    List<String> findAll() throws SQLException;
    
    boolean deleteLast(String expression) throws SQLException;
    
    boolean deleteAll() throws SQLException;
    
}
