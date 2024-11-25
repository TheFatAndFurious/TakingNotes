import Entities.NotesEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Up and running");
        App app = new App();
        app.runApp(args);
    }
}
