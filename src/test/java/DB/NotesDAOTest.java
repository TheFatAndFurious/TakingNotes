package DB;

import Entities.NotesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class NotesDAOTest {
    private NotesDAO notesDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseInit.Initialize();
        notesDAO = new NotesDAO(connection);
    }

    @Test
    void save() throws SQLException {
        NotesEntity testEntity = new NotesEntity();
        testEntity.setContent("this is the test entity");
        notesDAO.save(testEntity);

        assertNotNull(testEntity, "The saved note should not be null");
        assertNotNull(testEntity.getId(), "The ID should not be null after saving");
        assertTrue(testEntity.getId() > 0, "The generated Id should be greater than 0");
        assertEquals("this is the test entity", testEntity.getContent(), "The content should be the same");
    }

    @Test
    void delete() throws SQLException {
        NotesEntity testNote = new NotesEntity();
        testNote.setContent("test for deletion");
        var newEntry = notesDAO.save(testNote);

        notesDAO.delete(newEntry.getId());
        var hasBeenDeleted = notesDAO.getById(1L);
        assertNull(hasBeenDeleted, "Should be null after deletion");
    }

    @Test
    void update() throws SQLException {
        NotesEntity testNote = new NotesEntity();
        testNote.setContent("test for the update");
        var newEntry = notesDAO.save(testNote);

        newEntry.setContent("i've been updated");
        notesDAO.update(newEntry, newEntry.getId());

        var testIfUpdated = notesDAO.getById(newEntry.getId());
        assertEquals("i've been updated", testIfUpdated.getContent(), "The content should be the same");

    }

    @Test
    void getAll() throws SQLException {
        NotesEntity testNote = new NotesEntity();
        testNote.setContent("testing the getAll");
        notesDAO.save(testNote);

        NotesEntity testNote2 = new NotesEntity();
        testNote2.setContent("testing the getAll again");
        notesDAO.save(testNote2);

        var allEntries = notesDAO.getAll();

        assertNotNull(allEntries, "Shouldnt be null");
        assertEquals(2, allEntries.size(), "Should have 2 entries");
        assertTrue(allEntries.stream().anyMatch(n -> n.getContent().equals(testNote.getContent())), "Should have the same content");
    }

    @Test
    void getById() {
    }
}