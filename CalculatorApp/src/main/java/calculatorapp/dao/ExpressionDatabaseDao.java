/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpssilve
 */
public class ExpressionDatabaseDao implements ExpressionDao {

    private ExpressionDatabase exprDB;

    public ExpressionDatabaseDao(ExpressionDatabase exprDB) {
        this.exprDB = exprDB;
    }

    @Override
    public boolean save(String symbolicExpression) throws SQLException {
        try (Connection conn = exprDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Expression (symbols) VALUES (?)")) {
            stmt.setString(1, symbolicExpression);
            stmt.executeUpdate();

            return true;
        }
    }

    public boolean saveAll(List<String> symbolicExpressions) throws SQLException {
        try (Connection conn = exprDB.getConnection()) {
            for (int i = 0; i < symbolicExpressions.size(); i++) {
                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Expression (symbols) VALUES (?)")) {
                    stmt.setString(1, symbolicExpressions.get(i));
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new SQLException();
                }
            }
        }

        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll() throws SQLException {
        try (Connection conn = exprDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Expression")) {
            stmt.executeLargeUpdate();
            return true;
        }
    }

    public ArrayList<String> findMatches(String partialExpression) throws SQLException {
        ArrayList<String> foundExpressions = new ArrayList<>();

        try (Connection conn = exprDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Expression WHERE symbols LIKE ?")) {
            stmt.setString(1, "%" + partialExpression + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    foundExpressions.add(rs.getString("symbols"));
                }
            } catch (SQLException e) {
                throw new SQLException();
            }
        }

        return foundExpressions;
    }

    @Override
    public ArrayList<String> findAll() throws SQLException {
        ArrayList<String> symbolicExpressions = new ArrayList<>();
        try (Connection conn = exprDB.getConnection();
                ResultSet rs = conn.prepareStatement("SELECT * FROM Expression").executeQuery()) {

            while (rs.next()) {
                symbolicExpressions.add(rs.getString("symbols"));
            }
        }

        return symbolicExpressions;
    }

    public int countExpressionsInDatabase() throws SQLException {
        int count = 0;
        try (Connection conn = exprDB.getConnection();
                ResultSet rs = conn.prepareStatement("SELECT COUNT(*) FROM Expression").executeQuery()) {

            count = rs.getInt(1);
        }

        return count;
    }
}
