import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInit {
    private static final String DB_URL =  "jdbc:h2:./data/notesdb";
    private static final  String user = "user";
    private static final String password = "soSecret";

    public static void Initialize() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL, user, password);
            Statement statement = connection.createStatement()) {

            String createNotesTable = "CREATE TABLE IF NOT EXISTS notes (" +
                                        "id IDENTITY PRIMARY KEY, " +
                                        "content TEXT, " +
                                        "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            String createKeywordsTable = "CREATE TABLE IF NOT EXISTS keywords (" +
                                            "id IDENTITY PRIMARY_KEY, " +
                                            "name VARCHAR(255) UNIQUE)";
        }

    }
}
