package Services;

import DB.NotesDAO;
import Entities.NotesEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class NoteService {
    NotesDAO notesDAO;

    public NoteService(NotesDAO notesDAO){
        this.notesDAO = notesDAO;
    }

    public void createNote (String content) throws SQLException {
        NotesEntity note = new NotesEntity();
        note.setContent(content);
        notesDAO.save(note);
    }

    public void listAllNotes() throws SQLException {
        var notes = notesDAO.getAll();
        notes.forEach(note -> System.out.println(note.getId() + " - " + note.getContent()));
    }

    public void listMultipleNotesByDate() throws SQLException {

    }
}
