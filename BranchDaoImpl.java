package dao.impl;


import dao.custom.BranchDao;
import dao.util.SessionDao;
import etntity.Branch;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import static dao.util.SessionDao.executeTransaction;


public class BranchDaoImpl implements BranchDao {
    @Override
    public void save(Branch entity) throws SQLException {
        SessionDao.executeTransaction(session -> session.save(entity));
    }

    @Override
    public void update(Branch entity) throws SQLException {
        SessionDao.executeTransaction(session -> {
            session.update(entity);
            return null;
        });
    }

    @Override
    public void delete(int id) throws SQLException {
        SessionDao.executeTransaction(session -> {
            Branch entity = null;
            try {
                entity = getbyId(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.delete(entity);
            return null;
        });
    }

    public Branch getbyId(int id) throws SQLException {
        return executeTransaction(session -> session.get(Branch.class,id));
    }

    @Override
    public List<Branch> loadAll() throws SQLException {
        return   SessionDao.executeTransaction(session -> {
            Query query = session.createQuery("FROM Branch ");
            return query.getResultList();

        });
    }

    @Override
    public Branch get(String data) throws SQLException {
        return executeTransaction(session -> {
            Query query = session.createQuery("from Branch where branchName=:branchName");
            query.setParameter("branchName", data);
            List<Branch> branches= query.getResultList();
            for (Branch branch : branches){
                return branch;
            }
            System.out.println("ok");
            return null;
        });
    }
}
