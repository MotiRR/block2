package lesson3;

import lesson3.database.DataBaseManager;
import lesson3.util.ParallelHandler;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MainApp {

    private static void run(String[] args) throws SQLException {
        DataBaseManager.getInstance();
        ParallelHandler ph = new ParallelHandler();
        //4692
        ph.startOptimistic(1000, BigDecimal.valueOf(100)); // запуск оптимистической блокировки
        //4659 мс
        //ph.startPessimistic(1000, BigDecimal.valueOf(100)); // запуск пессимистической блокировки
    }

    public static void main(String[] args) {
        try {
            run(args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}