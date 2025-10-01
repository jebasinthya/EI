import java.util.*;

class Logger {
    private static Logger instance;
    private Logger() {}
    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }
    public void log(String message) { System.out.println("Log: " + message); }
}

public class SingletonPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.print("Enter log message: ");
        String msg = sc.nextLine();
        logger1.log(msg);

        System.out.println("logger1 == logger2? " + (logger1 == logger2));
        sc.close();
    }
}
