package DB;

import exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements GenericDAO<T, Long> {
    protected final Connection connection;

    public AbstractDAO(Connection connection){
        this.connection = connection;
    }

    protected abstract String getTableName();
    protected abstract void setStatementParameters(PreparedStatement smt, T entity);
    protected abstract String getUpdateSQL();
    protected abstract String getAllSQL();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    protected abstract String getByIdSQL();

    @Override
    public T save(T entity) throws DataAccessException {
        String sqlStatement = "INSERT INTO " + getTableName() + "(...) VALUES  (...) ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
            setStatementParameters(preparedStatement, entity);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0){
                throw new DataAccessException("Error: no rows have been affected");
            }
            try (ResultSet keys = preparedStatement.getGeneratedKeys()){
                if(keys.next()){
                    entity.setID(keys.getLong(1));
                } else {
                    throw new DataAccessException("Failure to save note, no ID has been generated");
                }
            }
            return entity;
        } catch (SQLException e){
            throw new DataAccessException("Error: couldn't save the " + entity);
        }
    }

    @Override
    public void delete(Long id) throws DataAccessException {
        String sqlStatement = "DELETE FROM " + getTableName() + " WHERE id = (?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0){
                throw new DataAccessException("Could not delete entry: " + id + ", no rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error: could not delete entry" + id, e);
        } ;
    }

    @Override
    public void update(String content, Long id) throws DataAccessException {
        String sqlStatement = getUpdateSQL();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setString(1, content);
            preparedStatement.setLong(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0){
                throw new DataAccessException("Error, could not update row " + id + ", no rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error, could not update row " + id ,e);
        } ;
    }

    @Override
    public List<T> getAll() throws DataAccessException {
        String sqlStatement = getAllSQL();
        List<T> allEntries = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while(resultSet.next()){
                allEntries.add(mapResultSetToEntity(resultSet));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Could not get all entries", e);
        } ;
        return allEntries;
    }

    @Override
    public T getById(Long id) throws DataAccessException {
        String sqlStatement =  getByIdSQL();
        T entry;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            entry = mapResultSetToEntity(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException("Error, could not fetch " + id , e);
        }
        return entry;
    }

}
