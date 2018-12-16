package AbteilungsMitarbeiterVisualizR;

public class Log {
    public static void error(String message, Exception cause) {
        System.out.println(message);
    }

    public static void console(String message) {System.out.println(message);}
}
