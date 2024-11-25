package Services;

import DB.NotesDAO;
import Entities.NotesEntity;

import java.sql.SQLException;

public class NoteService {
    NotesDAO notesDAO;

    public NoteService(NotesDAO notesDAO){
        this.notesDAO = notesDAO;
    }

    public void createNote (String content) throws SQLException {
        NotesEntity note = new NotesEntity();
        note.setContent(content);
        var newNote = notesDAO.save(note);
    }
}
