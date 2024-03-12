package bo.custom;

import bo.util.SuperBo;
import dto.UserDto;

import java.sql.SQLException;

public interface UserBo extends SuperBo {
    boolean save(UserDto dto) throws SQLException, ClassNotFoundException;

}
