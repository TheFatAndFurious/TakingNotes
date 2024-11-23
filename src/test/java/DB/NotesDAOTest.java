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
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }
}