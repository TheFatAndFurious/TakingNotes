import config.Config;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.*;

import org.junit.jupiter.api.Assertions.*;

public class DatabaseInitTest {
    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        DatabaseInit.Initialize();
        String dbURL = Config.getProperties("db.url");
        String user = Config.getProperties("db.user");
        String password = Config.getProperties("db.password");
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if(connection != null){
            connection.close();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"NOTES", "KEYWORDS", "NOTE_KEYWORDS"})
    public void testTablesExists(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, tableName.toUpperCase(), null)){
            Assertions.assertTrue(resultSet.next(), tableName + "exists");
        }
    }

}