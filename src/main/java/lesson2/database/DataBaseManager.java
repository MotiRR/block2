package lesson2.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DataBaseManager {

    private static DataBaseManager instance;
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration()
                    .configure("configs/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static DataBaseManager getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DataBaseManager.class) {
                if (instance == null) {
                    instance = new DataBaseManager();
                }
            }
        }
        return instance;
    }

    public Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    private DataBaseManager() throws SQLException {
        intializeDataBase();
    }

    private void intializeDataBase() throws SQLException {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("db/full.sql")).collect(Collectors.joining(" "));
            session = ourSessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
