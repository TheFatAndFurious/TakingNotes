import DB.DatabaseInit;
import DB.NotesDAO;
import Services.NoteService;
import exceptions.DataAccessException;
import exceptions.ServiceException;

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
                try {
                    noteService.listAllNotes();
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            }
            if (command.equals("notes")){
                try{
                noteService.createNote(args[0]);
                var test = notesDAO.getAll();
                test.forEach(note -> System.out.println(note.getContent()));
                } catch (DataAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        }
    }
