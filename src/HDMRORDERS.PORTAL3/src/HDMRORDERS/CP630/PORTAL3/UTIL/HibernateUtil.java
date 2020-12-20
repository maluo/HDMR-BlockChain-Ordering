package HDMRORDERS.CP630.PORTAL3.UTIL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
 
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");//./HDMRORDERS.PORTAL3/src/
            sessionFactory = configuration.buildSessionFactory();
        }
 
        return sessionFactory;
    }
}
