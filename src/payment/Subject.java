package payment;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer ob) {
        if (!observers.contains(ob)) {
            observers.add(ob);
        }
    }

    public void removeObserver(Observer ob) {
        if (observers.contains(ob)) {
            observers.remove(ob);
        }
    }

    public void notifyObservers(int invoiceNo) {
        for (Observer o : observers) {
            o.update(invoiceNo);
        }
    }
}
