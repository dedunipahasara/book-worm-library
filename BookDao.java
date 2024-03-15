package dao.custom;



import dao.util.CrudDao;
import etntity.Book;
import etntity.Branch;

import java.sql.SQLException;
import java.util.List;

public interface BookDao extends CrudDao<Book> {


    List<Book> getBookByBranch(Branch branch) throws SQLException;

    List<Book> getByCategory(String category, Branch branch) throws SQLException;


    long getBookCount() throws SQLException;
}
