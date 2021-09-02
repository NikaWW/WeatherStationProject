package sample;

import java.time.LocalTime;

/**
 * Interface with method to update time for a atomic clock.
 * @author  Weronika Warwas
 */
public interface ObserverTime {
    /**
     * Method to specifying time to update.
     * @param time time to update
     */
    void updateT(LocalTime time);
}

