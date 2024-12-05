package DB;

import Entities.Identifiable;
import exceptions.DataAccessException;
import java.util.List;

public interface GenericDAO<T, ID> {
    Identifiable save(T entity) throws DataAccessException;
    void delete(ID id) throws DataAccessException;
    void update(String content, ID id) throws DataAccessException;
    List<T> getAll() throws DataAccessException;
    T getById(ID id) throws DataAccessException;
}
