import java.util.*;

interface Observer {
    void update(int temperature);
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temp) {
        this.temperature = temp;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) o.update(temperature);
    }
}

class PhoneDisplay implements Observer {
    @Override
    public void update(int temperature) {
        System.out.println("Phone Display: Temperature updated to " + temperature + "°C");
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WeatherStation station = new WeatherStation();
        Observer display = new PhoneDisplay();
        station.registerObserver(display);

        System.out.print("Enter current temperature: ");
        int temp = sc.nextInt();
        station.setTemperature(temp);

        sc.close();
    }
}
