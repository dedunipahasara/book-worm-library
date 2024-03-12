package bo.impl;

import bo.custom.UserBo;
import dao.custom.UserDao;
import dao.util.DaoFactory;
import dao.util.DaoTypes;
import dto.UserDto;

import java.sql.SQLException;

public class UserBoImpl implements UserBo {

    private final UserDao userDaoImpl = (UserDao) DaoFactory.getDaoFactory().getDAO(DaoTypes.USER);

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
