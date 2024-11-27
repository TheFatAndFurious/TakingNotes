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



    @Override
    public NotesEntity save(NotesEntity entity) throws SQLException {
        String sqlStatement = "INSERT INTO notes (content) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getContent());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Failure to save note: no rows have been affected");
            }
            try (ResultSet keys = statement.getGeneratedKeys()){
                if(keys.next()){
                    entity.setID(keys.getLong(1));
                } else {
                    throw new SQLException("Failure to save note, no ID have been generated");
                }
            return entity;
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

    //NOTE: check if we could pass a string as a param instead of an entity
    /** Method used to update an existing note
     *
     * @param id is the note id
     * @throws SQLException
     */
    @Override
    public void update(NotesEntity entity, Long id) throws SQLException {
        String sqlStatement = "UPDATE notes SET (content) = ? WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setLong(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0){
                throw new SQLException("Error updating note, no rows have been affected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to retrieve the last N notes from the database
     * @param limit represents the number of notes we want to retrieve
     * @return a list of Notes
     */
    public List<NotesEntity> getLatestsNotes (int limit){
        List<NotesEntity> latestsNotes = new ArrayList<>();
        String sqlQuery = "SELECT id, content, timestamp FROM note ORDER BY timestamp DESC LIMIT ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                latestsNotes.add(mapResultSetToNote(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return latestsNotes;
    }


    /**
     * Method used to retrive all notes from the database
     * @return a list of notes
     * @throws SQLException
     */
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


    /**
     * Method used to retrieve notes by their ID
     * @param aLong which corrresponds to the note's ID
     * @return a single Note
     * @throws SQLException
     */
    @Override
    public NotesEntity getById(Long aLong) throws SQLException {
        String sqlStatement = "SELECT id, content, timestamp FROM notes WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return mapResultSetToNote(resultSet);
            } else {
                return null;
            }
        }
    }

    /**
     * The database returns a ResultSet so we need this helper function to transform it into a Note
     * @param rs is a ResultSet
     * @return a Note
     * @throws SQLException
     */
    private NotesEntity mapResultSetToNote(ResultSet rs) throws SQLException {
        NotesEntity note = new NotesEntity();
        note.setID(rs.getLong("id"));
        note.setContent(rs.getString("content"));
        note.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        return note;
    }
}
