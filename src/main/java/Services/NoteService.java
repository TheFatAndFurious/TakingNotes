package Services;

import DB.NotesDAO;
import Entities.NotesEntity;
import exceptions.DataAccessException;
import exceptions.ServiceException;

public class NoteService {
    NotesDAO notesDAO;

    public NoteService(NotesDAO notesDAO){
        this.notesDAO = notesDAO;
    }

    public void createNote (String content) throws ServiceException {
        NotesEntity note = new NotesEntity();
        note.setContent(content);
        try{
            notesDAO.save(note);
        } catch (DataAccessException e) {
            throw new ServiceException("Couldn't create note", e);
        }
    }

    public void listAllNotes() throws ServiceException {
        try{
            var notes = notesDAO.getAll();
            notes.forEach(note -> System.out.println(note.getId() + " - " + note.getContent()));
        } catch (DataAccessException e) {
            throw new ServiceException("Couldn't list all notes", e);
        }
    }

    public void listLatestNotes(int limit) throws ServiceException {
        try{
            var notes = notesDAO.getLatestsNotes(limit);
            notes.forEach(note -> System.out.println(note.getId() + " - " + note.getContent()));
        } catch (DataAccessException e) {
            throw new ServiceException("Couldn't list latest notes", e);
        }
    }
}
