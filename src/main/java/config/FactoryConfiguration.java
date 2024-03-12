package config;

import etntity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();

    }
    public static FactoryConfiguration getInstance(){
        return (null==factoryConfiguration?factoryConfiguration=new FactoryConfiguration():factoryConfiguration);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
