package DB;

import Entities.NotesEntity;

import java.sql.*;
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

    @Override
    public void delete(Long aLong) throws SQLException {

    }

    @Override
    public void update(Long aLong) throws SQLException {

    }

    @Override
    public List<NotesEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public NotesEntity getById(Long aLong) throws SQLException {
        return null;
    }
}
