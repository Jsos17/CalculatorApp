/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import calculatorapp.logic.Expression;
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
public class ExpressionDao implements MathDao {

    private MathDatabase mathDB;

    public ExpressionDao(MathDatabase mathDB) {
        this.mathDB = mathDB;
    }

    @Override
    public boolean save(String symbolicExpression) throws SQLException {
        if (symbolicExpression.length() <= 1000) {
            try (Connection conn = mathDB.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO Expression (symbols) VALUES (?)")) {
                stmt.setString(1, symbolicExpression);
                stmt.executeUpdate();

                return true;
            }
        } else {
            return false;
        }
    }

    public boolean saveAll(List<String> symbolicExpressions) throws SQLException {
        try (Connection conn = mathDB.getConnection()) {
            for (int i = 0; i < symbolicExpressions.size(); i++) {
                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Expression (symbols) VALUES (?)")) {
                    String s = symbolicExpressions.get(i);
                    if (s.length() <= 1000) {
                        stmt.setString(1, s);
                        stmt.executeUpdate();
                    }
                } catch (SQLException e) {
                    throw new SQLException();
                }
            }
        }

        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (Connection conn = mathDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Expression WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;
        }
    }

    public ArrayList<Expression> findMatches(String partialExpression) throws SQLException {
        ArrayList<Expression> foundExpressions = new ArrayList<>();

        try (Connection conn = mathDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Expression WHERE symbols LIKE ?")) {
            stmt.setString(1, "%" + partialExpression + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    foundExpressions.add(new Expression(rs.getInt("id"), rs.getString("symbols")));
                }
            } catch (SQLException e) {
                throw new SQLException();
            }
        }

        return foundExpressions;
    }

    @Override
    public ArrayList<Expression> findAll() throws SQLException {
        ArrayList<Expression> expressions = new ArrayList<>();
        try (Connection conn = mathDB.getConnection();
                ResultSet rs = conn.prepareStatement("SELECT * FROM Expression").executeQuery()) {

            while (rs.next()) {
                expressions.add(new Expression(rs.getInt("id"), rs.getString("symbols")));
            }
        }

        return expressions;
    }

    public int countExpressionsInDatabase() throws SQLException {
        int count = 0;
        try (Connection conn = mathDB.getConnection();
                ResultSet rs = conn.prepareStatement("SELECT COUNT(*) FROM Expression").executeQuery()) {

            count = rs.getInt(1);
        }

        return count;
    }
}
