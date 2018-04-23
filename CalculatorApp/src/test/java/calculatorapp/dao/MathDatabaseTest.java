/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapp.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class MathDatabaseTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File dbFile;
    MathDatabase mathDB;
    
    @Before
    public void setUp() throws IOException, ClassNotFoundException, SQLException {
        dbFile = testFolder.newFile("mathTest.db");
        mathDB = new MathDatabase("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }
    
    @Test
    public void initDatabaseWorks() throws SQLException {
        mathDB.initDatabase();
        try (Connection conn = mathDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sqlite_master");
                ResultSet rs = stmt.executeQuery()) {
            assertEquals("CREATE TABLE Expression (id integer PRIMARY KEY, symbols varchar(2000))",rs.getString("sql"));
        }
    }
    
    @After
    public void tearDown() {
        dbFile.delete();
    }
}
