package dao.impl;

import dao.custom.UserDao;
import etntity.User;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (custId,custName, custAddress,custTel) VALUES (?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getTel());    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
