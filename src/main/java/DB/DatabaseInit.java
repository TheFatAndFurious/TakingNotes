package DB;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class used to initialize an H2 database, 3 tables are being created:
 * one for the notes, one for the keywords and the third one as a joint table in case a note has several keywords
 */
public  class DatabaseInit {
    private static final String DB_URL = Config.getProperties("db.url");
    private static final  String user = Config.getProperties("db.user");
    private static final String password = Config.getProperties("db.password");

    public static Connection Initialize() throws SQLException {

        Connection connection = DriverManager.getConnection(DB_URL, user, password);

        try(Statement statement = connection.createStatement()) {

            String createNotesTable = "CREATE TABLE IF NOT EXISTS notes (" +
                                        "id IDENTITY, " +
                                        "content TEXT, " +
                                        "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            String createKeywordsTable = "CREATE TABLE IF NOT EXISTS keywords (" +
                                            "id IDENTITY, " +
                                            "name VARCHAR(255) UNIQUE)";
            String createNoteKeywordsTable = "CREATE TABLE IF NOT EXISTS note_keywords(" +
                                            "note_id BIGINT, " +
                                            "keyword_id BIGINT, " +
                                            "FOREIGN KEY (note_id) REFERENCES notes(id), " +
                                            "FOREIGN KEY (keyword_id) REFERENCES keywords(id))";
            statement.execute(createNotesTable);
            statement.execute(createKeywordsTable);
            statement.execute(createNoteKeywordsTable);
        } catch (RuntimeException e) {
            connection.close();
            throw new RuntimeException(e);
        }
        return connection;
    }
}
