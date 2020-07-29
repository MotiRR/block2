package lesson2.database.dao;

import lesson2.database.DataBaseManager;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;


public class BuyerDAO {

    //имена покупателей, купивших указанный товар, по названию товара;
    public List getBuyers(String product) {
        List buyers = null;
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();

            String script = "Select b.name FROM Buyer b join b.products p WHERE p.name = :name";
            buyers = session.createQuery(script)
                    .setParameter("name", product)
                    .getResultList();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyers;
    }

    //удалять из базы покупателей по имени;
    public void deleteBuyer(String buyer) {
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();
            String script = "Delete FROM Buyer b WHERE b.name = :name";
            session.createQuery(script)
                    .setParameter("name", buyer)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
