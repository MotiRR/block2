package lesson3.database.dao;

import lesson3.database.DataBaseManager;
import lesson3.database.dto.User;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserDAO {

    public List<User> getAllUsers() {
        List users = null;
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();
            users = session.createQuery("Select u FROM User u")
                    .getResultList();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
