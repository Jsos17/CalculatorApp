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
public interface MathDao<T> {
    
    boolean save(String expression) throws SQLException;
    
    List<T> findAll() throws SQLException;
    
    boolean delete(Integer id) throws SQLException;
    
}
