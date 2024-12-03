package DB;

import Entities.Identifiable;
import Entities.KeywordEntity;
import Entities.NotesEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KeywordDAO extends AbstractDAO {
    public KeywordDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return "keywords";
    }


    @Override
    protected String getUpdateSQL() {
        return "UPDATE keywords SET content = (?) WHERE id = (?)";;
    }

    @Override
    protected String getAllSQL() {
        return "SELECT id, name FROM keywords";
    }

    @Override
    protected Identifiable mapResultSetToEntity(ResultSet rs) throws SQLException {
        KeywordEntity keyword = new KeywordEntity();
        keyword.setId(rs.getLong("id"));
        keyword.setName(rs.getString("content"));
        return keyword;
    }

    @Override
    protected String getByIdSQL() {
        return "SELECT id, name FROM keywords WHERE id = (?)";
    }

    protected String saveSQL(){
        return "INSERT INTO keywords (...) VALUES  (...) ";
    }

}
