package DB;

import Entities.NotesEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access layer for the Notes, we should be able to:
 *  - create
 *  - read (retrieve one or multiple notes using params like timestamps or keywords)
 *  - delete
 *  - update
 *
 */
public class NotesDAO implements GenericDAO<NotesEntity, Long> {
    Connection connection;

    public NotesDAO(Connection connection){
        this.connection = connection;
    }


    /**
     * Saving a note to the database
     * @param entity
     * @throws SQLException
     */
    @Override
    public void save(NotesEntity entity) throws SQLException {
        String sqlStatement = "INSERT INTO notes (content) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getContent());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()){
                if(keys.next()){
                    entity.setID(keys.getLong(1));
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Error registering the note", e);
        }
    }
    // NOTE: Since this delete() will be used by all entities in the same way, should we create an abstract class that will implements our DAO interface and write a common delete() method ?
    /**
     * Method we will use to delete a note from the database
     * @param id which is the note's ID
     * @throws SQLException
     */
    @Override
    public void delete(Long id) throws SQLException {
        String statement = "DELETE FROM notes where id = (?)";

        try(PreparedStatement statement1 = connection.prepareStatement(statement)){
            statement1.setLong(1, id);
            statement1.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Error deleting note", e);
        }
    }

    @Override
    public void update(Long id) throws SQLException {

    }

    @Override
    public List<NotesEntity> getAll() throws SQLException {
        List<NotesEntity> notes = new ArrayList<>();
        String SQLStatement = "SELECT id, content, timestamp FROM notes";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQLStatement);
            while(resultSet.next()){
                notes.add(mapResultSetToNote(resultSet));
            }
        } catch (SQLException e) {
            throw new SQLException("Error trying to retrieve all notes", e);
        }
        return notes;
    }

    @Override
    public NotesEntity getById(Long aLong) throws SQLException {
        return null;
    }

    private NotesEntity mapResultSetToNote(ResultSet rs) throws SQLException {
        NotesEntity note = new NotesEntity();
        note.setID(rs.getLong("id"));
        note.setContent(rs.getString("content"));
        note.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        return note;
    }
}
