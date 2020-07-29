package lesson2.database.dao;

import lesson2.database.DataBaseManager;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class ProductDAO {

    //товары, которые приобрел покупатель, по имени покупателя;
    public List getProducts(String buyer) {
        List products = null;
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();

            String script = "Select p.name FROM Product p join p.buyers b WHERE b.name = :name";
            products = session.createQuery(script)
                    .setParameter("name", buyer)
                    .getResultList();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //удалять из базы товары по названию;
    public void deleteProduct(String product) {
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();

            String script = "Delete from Product p WHERE p.name = :name";
            session.createQuery(script)
                    .setParameter("name", product)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //реализовать возможность “покупки товара” по id покупателя и товара.
    public void buy(Long buyerId, Long productId) {
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();
            String script = "INSERT INTO buyers_products (buyer_id, product_id, date_buy) VALUES (:buyer_id, :product_id, :date_buy)";
            session.createNativeQuery(script)
                    .setParameter("buyer_id", buyerId)
                    .setParameter("product_id", productId)
                    .setParameter("date_buy", new Date())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //По паре покупатель-товар нужна детализация: стоимость товара на момент покупки клиентом;
    public Object getCost(Long buyerId, Long productId, Date date) {
        Object cost = null;
        try (Session session = DataBaseManager.getInstance().getSession()) {
            session.beginTransaction();
            String script = "Select b.name as buyer, pr.name as product, c.cost from buyers_products as bp " +
                    "INNER JOIN buyers as b on bp.buyer_id = b.id " +
                    "INNER JOIN products as pr on bp.product_id = pr.id " +
                    "INNER JOIN costs as c on bp.product_id = c.product_id " +
                    "where bp.buyer_id = :buyer_id and bp.product_id = :product_id and c.date_cost <= :date_buy " +
                    "order by c.date_cost desc Limit 1";
            List costs = session.createNativeQuery(script)
                    .setParameter("buyer_id", buyerId)
                    .setParameter("product_id", productId)
                    .setParameter("date_buy", date)
                    .getResultList();
            if (!costs.isEmpty())
                cost = costs.get(0);
            session.getTransaction().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cost;
    }

}
