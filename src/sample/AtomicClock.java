package sample;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class implemnet interface {@code Runnable} and ObserverTime to create a atomic clock.
 * @author  Weronika Warwas
 */

public class AtomicClock implements  Runnable, ObservableTime {

    private volatile ArrayList<ObserverTime> observers = new ArrayList<>();
    private Thread worker;
    protected volatile boolean isRunning = false;
    private int interval;

    /**
     * Constructs initializes interval.
     */

    public AtomicClock() {
        interval = 1000;
    }

    /**
     * Thread to start observers work.
     */

    public void start() {
        worker = new Thread(this, " Clock thread");
        worker.start();
    }

    /**
     * Thread to stop observers work.
     */

    public void stop() {
        isRunning = false;
    }

    /**
     * Thread to interrutp observers work.
     */

    public void interrupt() {
        isRunning = false;
        worker.interrupt();
    }

    /**
     *Methods updates observers every given time interval.
     */

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                Thread.sleep(interval);
                updateObserverT();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Failed to complete operation");
            }
        }
    }

    /**
     * Methods adds a new observer provided that doesn't exist observer with same name.
     * @param observer observer
     */

    @Override
    public void addObserverTime(ObserverTime observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }

    /**
     * Methods remove observer.
     * @param observer observer
     */

    @Override
    public void removeObserver(ObserverTime observer) {
    }

    /**
     * Methods update all observers.
     */

    @Override
    public void updateObserverT() {
        for (ObserverTime o : observers) {
            LocalTime time = LocalTime.now();
            o.updateT(time);
        }
    }
}

