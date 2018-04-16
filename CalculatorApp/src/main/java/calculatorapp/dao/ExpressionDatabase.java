/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpssilve
 */
public class ExpressionDatabase {
    
    private String databaseAddress;

    public ExpressionDatabase(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    public void init() {
        List<String> createExpressions = sqliteCreateExpressions();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();
            
            for (int i = 0; i < createExpressions.size(); i++) {
                String sqliteExpression = createExpressions.get(i);
                st.executeUpdate(sqliteExpression);
            }

        } catch (Throwable thrown) {
            System.out.println(thrown.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.databaseAddress);
    }
    
    private List<String> sqliteCreateExpressions() {
        ArrayList<String> list = new ArrayList<>();
        list.add("CREATE TABLE IF NOT EXISTS Expression (id integer PRIMARY KEY, formula varchar(2000));");
        return list;
    }
}
