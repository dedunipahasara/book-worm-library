package dao.custom;



import dao.util.CrudDao;
import etntity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDao<User> {

    long getUserCount() throws SQLException;
}
