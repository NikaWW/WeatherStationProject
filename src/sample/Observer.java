package sample;

import java.time.LocalTime;

/**
 * Interface with the method for updates weather conditions
 * @author  Weronika Warwas
 */

public interface Observer {

    /**
     * Method specifying weather condition to update.
     * @param temperature temperature
     * @param pressure pressure
     * @param humidity humidity
     * @param t_max maximum value of temperature
     * @param t_min minimum value of temperature
     * @param time time
     * @param response full response from server
     */

    void updateWeather(Double temperature, Double pressure, Double humidity, Double t_max, Double t_min, LocalTime time, StringBuffer response);

}