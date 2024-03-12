package dao.util;

import dao.impl.UserDaoImpl;

public class DaoFactory {
    public static DaoFactory daoFactory;

    private DaoFactory(){
    }

    public static DaoFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory= new DaoFactory():daoFactory;
    }

    public SuperDao getDAO(DaoTypes daoTypes){
        switch (daoTypes) {
            case USER:
                return (SuperDao) new UserDaoImpl();

            default:
                return null;
        }
    }

}
