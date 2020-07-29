package lesson2;

import lesson2.database.DataBaseManager;
import lesson2.util.CommandHandler;

import java.sql.SQLException;

public class MainApp {

    private static void run(String[] args) throws SQLException {
        DataBaseManager.getInstance();
        new CommandHandler();
    }

    public static void main(String[] args) {
        try {
            run(args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
