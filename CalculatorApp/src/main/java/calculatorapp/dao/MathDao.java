package calculatorapp.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Just the interface for Dao classes, which enables easier expansion of the
 * app.
 *
 * @author jpssilve
 */
public interface MathDao<T> {

    boolean save(String expression) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean delete(Integer id) throws SQLException;

}
