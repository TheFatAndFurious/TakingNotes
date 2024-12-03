package DB;

import Entities.NotesEntity;
import exceptions.DataAccessException;

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
public class NotesDAO extends AbstractDAO {
    Connection connection;

    public NotesDAO(Connection connection) {
        super(connection);
    }

    /**
     * Method used to retrieve the last N notes from the database
     * @param limit represents the number of notes we want to retrieve
     * @return a list of Notes
     */
    public List<NotesEntity> getLatestNotes (int limit) throws DataAccessException{
        List<NotesEntity> latestNotes = new ArrayList<>();
        String sqlQuery = "SELECT id, content, timestamp FROM note ORDER BY timestamp DESC LIMIT ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                latestNotes.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Couldn't fetch latest notes", e);
        }
        return latestNotes;
    }

    /**
     * The database returns a ResultSet so we need this helper function to transform it into a Note
     * @param rs is a ResultSet
     * @return a Note
     * @throws SQLException
     */

    @Override
    public String getTableName(){
        return "notes";
    }

    @Override
    protected void setStatementParameters(PreparedStatement smt, Object entity) {

    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE notes SET content = (?) WHERE id = (?)";
    }

    @Override
    protected String getAllSQL() {
        return "SELECT id, content, timestamp FROM notes";
    }

    @Override
    protected NotesEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        NotesEntity note = new NotesEntity();
        note.setID(rs.getLong("id"));
        note.setContent(rs.getString("content"));
        note.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        return note;
    }

    @Override
    protected String getByIdSQL() {
        return "SELECT id, content, timestamp FROM notes WHERE id = (?)";
    }
}
