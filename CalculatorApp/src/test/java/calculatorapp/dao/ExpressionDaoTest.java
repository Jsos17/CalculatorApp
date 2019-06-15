package calculatorapp.dao;

import calculatorapp.logic.Expression;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author jpssilve
 */
public class ExpressionDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File dbFile;
    MathDatabase mathDB;
    ExpressionDao eDao;
    ArrayList<String> es;
    String tooLong;

    @Before
    public void setUp() throws IOException, SQLException, ClassNotFoundException {
        dbFile = testFolder.newFile("mathTest.db");
        mathDB = new MathDatabase("jdbc:sqlite:" + dbFile.getAbsolutePath());

        try (Connection conn = mathDB.getConnection()) {
            mathDB.initDatabase();
        }

        eDao = new ExpressionDao(mathDB);
        es = new ArrayList<>();
        es.add("75*4+(34-27)^2");
        es.add("(1-3)^2");
        es.add("10000*0.56+56/2");
        es.add("1*2*3*4*5*6*7");

        tooLong = "";
        for (int i = 1; i <= 200; i++) {
            tooLong += "1234567890";
        }
    }

    @Test
    public void saveWorks1() throws SQLException {
        eDao.save("75*4+(34-27)^2");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Expression")) {
            ResultSet rs = stmt.executeQuery();
            assertEquals("75*4+(34-27)^2", rs.getString("symbols"));
        }
    }

    @Test
    public void saveWorks2() throws SQLException {
        eDao.save("75*4+(34-27)^2");
        assertEquals("75*4+(34-27)^2", eDao.findAll().get(0).getExpression());
    }

    @Test
    public void tooLongExpressionIsNotSaved() throws SQLException {
        assertFalse(eDao.save(tooLong));
    }

    @Test
    public void tooLongExpressionIsNotSavedInSaveAll() throws SQLException {
        ArrayList<String> testEs = new ArrayList<>();
        testEs.add(tooLong);
        testEs.addAll(es);
        testEs.add(tooLong);
        eDao.saveAll(testEs);
        assertEquals(4, eDao.findAll().size());
    }

    @Test
    public void saveAllWorks1() throws SQLException {
        eDao.saveAll(es);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Expression");
                ResultSet rs = stmt.executeQuery();) {
            ArrayList<String> testEs = new ArrayList<>();

            while (rs.next()) {
                testEs.add(rs.getString("symbols"));
            }

            for (int i = 0; i < testEs.size(); i++) {
                assertEquals(es.get(i), testEs.get(i));
            }
        }
    }

    @Test
    public void findAllWorks() throws SQLException {
        eDao.saveAll(es);
        ArrayList<Expression> found = new ArrayList<>();
        found.addAll(eDao.findAll());

        for (int i = 0; i < found.size(); i++) {
            assertEquals(es.get(i), found.get(i).getExpression());
        }
    }

    @Test
    public void saveAllWorks2() throws SQLException {
        eDao.saveAll(es);
        ArrayList<Expression> test = eDao.findAll();

        for (int i = 0; i < test.size(); i++) {
            assertEquals(es.get(i), test.get(i).getExpression());
        }
    }

    @Test
    public void deleteWorks1() throws SQLException {
        eDao.saveAll(es);
        eDao.delete(1);
        eDao.delete(3);

        assertEquals(2, eDao.findAll().size());
    }

    @Test
    public void deleteWorks2() throws SQLException {
        eDao.saveAll(es);
        eDao.delete(1);
        eDao.delete(3);
        ArrayList<Expression> found = new ArrayList<>();
        found.addAll(eDao.findAll());

        assertEquals(2, found.get(0).getId());
        assertEquals(4, found.get(1).getId());
    }

    @Test
    public void findPartialExpressionWorks() throws SQLException {
        eDao.saveAll(es);
        eDao.save("5/6");
        eDao.save("77/5");
        ArrayList<Expression> found = new ArrayList<>();
        found.addAll(eDao.findMatches("/"));

        assertEquals("10000*0.56+56/2", found.get(0).getExpression());
        assertEquals("5/6", found.get(1).getExpression());
        assertEquals("77/5", found.get(2).getExpression());
    }

    @Test
    public void countExpressionsInDatabaseWorks() throws SQLException {
        eDao.saveAll(es);
        eDao.saveAll(es);
        eDao.saveAll(es);
        eDao.save("8^42");
        eDao.save("100*42");

        assertEquals(14, eDao.countExpressionsInDatabase());
    }

    @After
    public void tearDown() {
        dbFile.delete();
    }
}
