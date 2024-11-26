import DB.DatabaseInit;
import DB.NotesDAO;
import Services.NoteService;

import java.sql.SQLException;
import java.util.Objects;

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
            var command = args[0];
            if(Objects.equals(command, "list")){
                noteService.listAllNotes();
            }
            if (command.equals("notes")){
                noteService.createNote(args[0]);
                var test = notesDAO.getAll();
                test.forEach(note -> System.out.println(note.getContent()));
            }
        }
        }
    }
