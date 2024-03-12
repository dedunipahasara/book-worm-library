package dao.util;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao <T> extends SuperDao {
    boolean save(T entity) throws SQLException, ClassNotFoundException ;

    String generateID() throws SQLException, ClassNotFoundException ;

}
