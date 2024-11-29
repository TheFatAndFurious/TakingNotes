package DB;

import exceptions.DataAccessException;

import java.sql.*;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T, Long> {
    protected final Connection connection;

    public AbstractDAO(Connection connection){
        this.connection = connection;
    }

    protected abstract String getTableName();
    protected abstract void setStatementParameters(PreparedStatement smt, T entity);

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
    public void delete(Object o) throws DataAccessException {

    }

    @Override
    public void update(Object identity, Object o) throws DataAccessException {

    }

    @Override
    public List getAll() throws DataAccessException {
        return List.of();
    }

    @Override
    public Object getById(Object o) throws DataAccessException {
        return null;
    }

}
