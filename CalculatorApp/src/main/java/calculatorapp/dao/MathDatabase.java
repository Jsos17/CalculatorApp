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
 * This class provides a database connection and initialization for all data
 * access object classes that might need it.
 *
 * @author jpssilve
 */
public class MathDatabase {

    private String databaseAddress;

    /**
     * A database address is required for the constructor.
     *
     * @param databaseAddress as String form and containing the "jdbc:sqlite:"
     * prefix and the actual name/path of the database.
     *
     * @throws ClassNotFoundException if an exception occurs
     */
    public MathDatabase(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Creates table Expression if it does not already exist by calling
     * sqliteCreateExpressions.
     *
     */
    public void initDatabase() {
        List<String> createExpressions = sqliteCreateExpressions();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (int i = 0; i < createExpressions.size(); i++) {
                String sqliteExpression = createExpressions.get(i);
                st.executeUpdate(sqliteExpression);
            }

        } catch (Throwable thrown) {
        }
    }

    /**
     * Returns a connection to the underlying SQL database.
     *
     * @return a Connection to the underlying SQL database
     * @throws SQLException if an exception occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.databaseAddress);
    }

    private List<String> sqliteCreateExpressions() {
        ArrayList<String> list = new ArrayList<>();
        list.add("CREATE TABLE IF NOT EXISTS Expression (id integer PRIMARY KEY, symbols varchar(1000));");
        return list;
    }
}
