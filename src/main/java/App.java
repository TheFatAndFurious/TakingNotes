import DB.DatabaseInit;
import DB.NotesDAO;
import Entities.NotesEntity;
import Services.NoteService;

import java.sql.SQLException;

public class App {
    NoteService noteService;
    NotesDAO notesDAO;
    DatabaseInit databaseInit;

    public App() throws SQLException {
        this.databaseInit = new DatabaseInit();
        this.notesDAO = new NotesDAO(databaseInit.Initialize());
        this.noteService = new NoteService(notesDAO);
    }

    public void runApp(String[] args) throws SQLException {
        if(args.length != 0){
            noteService.createNote(args[0]);
            var test = notesDAO.getAll();
            test.forEach(note -> System.out.println(note.getContent()));
        }
        }
    }
