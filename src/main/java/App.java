import DB.DatabaseInit;
import DB.NotesDAO;
import Entities.NotesEntity;
import Services.NoteService;

import java.sql.SQLException;

public class App {
    NoteService noteService;
    NotesDAO notesDAO;

    public App() throws SQLException {
        this.notesDAO = new NotesDAO(DatabaseInit.Initialize());
        this.noteService = new NoteService(notesDAO);
    }

    public void runApp(String[] args) throws SQLException {
        NotesDAO notesDAO = new NotesDAO(DatabaseInit.Initialize());
        if(args.length != 0){
            noteService.createNote(args[0]);
            var test = notesDAO.getAll();
            test.forEach(note -> System.out.println(note.getContent()));
        }
        }
    }
