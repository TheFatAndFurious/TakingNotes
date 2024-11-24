import Entities.NotesEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("ta soeur");
        if(Objects.equals(args[0], "notes")){
            System.out.println("we so good");
        } else {
            System.out.println("its a miss");
        }
    }
}
