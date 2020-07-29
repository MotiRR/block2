package lesson2.util;


import java.util.Scanner;

public class CommandHandler {

    private String[] argsConsole;
    private Service service = new Service();

    public CommandHandler() {
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                argsConsole = in.nextLine().split(" ");
                service.perform(argsConsole);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
