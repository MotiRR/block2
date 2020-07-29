package lesson2.util;

import lesson2.database.dao.BuyerDAO;
import lesson2.database.dao.ProductDAO;

import java.util.Date;
import java.util.List;

public class Service {

    /*

    /deleteConsumer (/deleteProduct) имя_элемента - предоставить возможность удалять из базы товары/покупателей по названию/имени;
    /buy
    */
    private BuyerDAO buyerDAO = new BuyerDAO();
    private ProductDAO productDAO = new ProductDAO();

    public void perform(String[] argsConsole) {
        if (argsConsole[0].equals("/showProductsByConsumer"))
            showProductsByConsumer(argsConsole[1]);
        if (argsConsole[0].equals("/showConsumersByProductTitle"))
            showConsumersByProductTitle(argsConsole[1]);
        if (argsConsole[0].equals("/deleteConsumer"))
            deleteConsumer(argsConsole[1]);
        if (argsConsole[0].equals("/deleteProduct"))
            deleteProduct(argsConsole[1]);
        if (argsConsole[0].equals("/buy"))
            buy(Long.parseLong(argsConsole[1]), Long.parseLong(argsConsole[2]));
        if (argsConsole[0].equals("/cost"))
            getCost(Long.parseLong(argsConsole[1]), Long.parseLong(argsConsole[2]));
    }

    /**
     * Печатает в консоль товары, которые приобрел покупатель, по имени покупателя.
     *
     * @param buyer - имя покупателя
     */
    private void showProductsByConsumer(String buyer) {
        // /showProductsByConsumer Alex
        List products = productDAO.getProducts(buyer);
        for (Object name : products)
            System.out.println(name);
    }

    /**
     * Печатает имена покупателей, купивших указанный товар, по названию товара.
     *
     * @param product - название товара
     */
    private void showConsumersByProductTitle(String product) {
        // /showConsumersByProductTitle car
        List buyers = buyerDAO.getBuyers(product);
        for (Object name : buyers)
            System.out.println(name);
    }

    /**
     * Удаляет из базы покупателя по имени.
     *
     * @param buyer - имя покупателя
     */
    private void deleteConsumer(String buyer) {
        // /deleteConsumer Andrey
        buyerDAO.deleteBuyer(buyer);
    }

    /**
     * Удаляет из базы товар по названию.
     *
     * @param product - название товара
     */
    private void deleteProduct(String product) {
        // /deleteProduct car
        productDAO.deleteProduct(product);
    }

    /**
     * Покупка товара по id покупателя и товара.
     *
     * @param buyerId   - id покупателя;
     * @param productId - id товара.
     */
    private void buy(Long buyerId, Long productId) {
        // /buy 1 3
        productDAO.buy(buyerId, productId);
    }

    /**
     * Печатает цену товара на момент покупки покупателем.
     *
     * @param buyerId   - id покупателя;
     * @param productId - id товара.
     */
    private void getCost(Long buyerId, Long productId) {
        // /cost 1 1
        Object[] cost = (Object[]) productDAO.getCost(buyerId, productId, new Date());
        if (cost != null)
            System.out.println(new StringBuilder().append(cost[0]).append(" ")
                    .append(cost[1]).append(" ")
                    .append(cost[2]));
    }
}
