package lesson3.database.dao;

import lesson3.database.DataBaseManager;
import lesson3.database.dto.Bet;
import lesson3.database.dto.User;
import org.hibernate.Session;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.sql.SQLException;

public class BetDAO {

    public static Long countRollBack = 0L;

    public void update(Long id, User user, BigDecimal rate, LockModeType lockModeType) {
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();
            Bet bet = session.find(Bet.class, id, lockModeType);
            try {
                bet.setUser(user);
                bet.setRate(bet.getRate().add(rate));
                session.save(bet);
                session.getTransaction().commit();
            } catch (Exception ignored) {
                System.err.println(ignored.getMessage());
                session.getTransaction().rollback();
                countRollBack += 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
