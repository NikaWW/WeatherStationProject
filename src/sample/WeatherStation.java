package sample;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class WeatherStation imlemnets interface @code Runnable} and Observer.
 * The class contains methods for determining the time interval of communication with Open Weather Map server.
 * @author  Weronika Warwas
 */

public class WeatherStation implements Runnable, Observable {


    private volatile ArrayList<Observer> observers = new ArrayList<>();
    private Thread worker;
    protected volatile boolean isRunning = false;
    private int interval;
    private String city ;


    /**
     * Class constructor specifying time interval.
     */
    public WeatherStation(){
        interval=60*1000;
    }

    /**
     *Class constructor specifying  the value of the time interval and the name of the city
     * @param city city name
     * @param interval interval time
     */

    public WeatherStation(String city, int interval) {
        if(interval< 60000 ) {
            throw new IllegalArgumentException("Time can't be shorter then 1 minute");
        }
        this.interval=interval;
        this.city = city;
        observers=new ArrayList<>();
    }

    /**
     *Method setting time interval.
     * @param interval interval time
     */

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * Method returning a city
     * @return name of city
     */

    public String getCity() {
        return city;
    }

    /**
     * Thread to start observers work.
     */
    public void start() {
        worker = new Thread(this, "Weather thread");
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
     * Methods adds a new observer provided that doesn't exist observer with same name.
     * @param observer observer to add
     */

    @Override
    public void addObserver(Observer observer) {
        if(!observers.contains(observer)) observers.add(observer);     //jezeli nie ma takiego obiektu na liscie dodaje
    }

    /**
     * Methods remove observer provided that exists observer to remove.
     * @param observer observer to remove
     */

    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer)) observers.remove(observer);    //usuniecie subskrybenta
    }

    /**
     * Methods update all observers.
     */

    @Override
    public void updateObserver() {
        Weather w = new Weather(getCity());
        for(Observer o : observers){
            w.weatherData(getCity());
            LocalTime time= LocalTime.now();
            o.updateWeather(w.getTemperature(),w.getPressure(),w.getHumidity(),w.getTemp_max(),w.getTemp_min(),time,w.getResponse());
        }
    }


    @Override
    public void run() { Weather w = new Weather(getCity());
        isRunning = true;
        while (isRunning==true) {
            try {
                updateObserver();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted");
            }
        }
    }


}
