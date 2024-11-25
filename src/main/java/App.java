import DB.DatabaseInit;
import DB.NotesDAO;
import Entities.NotesEntity;

import java.sql.SQLException;

public class App {

    public void runApp(String[] args) throws SQLException {
        NotesDAO notesDAO = new NotesDAO(DatabaseInit.Initialize());
        if(args.length != 0){
            NotesEntity note = new NotesEntity();
            note.setContent(args[0]);
            var doesItWork = notesDAO.save(note);
            System.out.println(doesItWork.getId() + " -- " + doesItWork.getContent() + " -- ");
        }
        }
    }
