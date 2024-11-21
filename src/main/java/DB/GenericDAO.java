package DB;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, ID> {
    void save(T entity) throws SQLException;
    void delete(ID id) throws SQLException;
    void update(ID id) throws SQLException;
    List<T> getAll() throws SQLException;
    T getById(ID id) throws SQLException;
}
