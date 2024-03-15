package dao.custom;



import dao.util.SuperDao;
import etntity.Book;
import etntity.Transaction;
import etntity.User;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDao extends SuperDao {

    List<Transaction> getByUserId(User user) throws SQLException;

    List<Transaction> getByBookUser(User user, Book book) throws SQLException;

    void save(Transaction transaction) throws SQLException;

    boolean update(Transaction transaction) throws SQLException;

    List<Transaction> getAll() throws SQLException;

    List<Transaction> getOverDue() throws SQLException;

    List<Transaction> getAllByUser(User user) throws SQLException;

    List<Transaction> getTodayCheckOuts() throws SQLException;
}
