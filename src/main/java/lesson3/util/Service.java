package lesson3.util;

import lesson3.database.dao.BetDAO;
import lesson3.database.dao.UserDAO;
import lesson3.database.dto.User;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;

public class Service {

    private BetDAO betDAO = new BetDAO();
    private UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateBet(Long id, User user, BigDecimal rate, LockModeType lockModeType) {
        betDAO.update(id, user, rate, lockModeType);
    }

}
