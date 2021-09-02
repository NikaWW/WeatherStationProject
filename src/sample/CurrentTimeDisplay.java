package sample;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class created to display atomic clock with the current time.
 * Class implements Interface ObserverTime to get updated time.
 * @author  Weronika Warwas
 */

public class CurrentTimeDisplay implements ObserverTime {

    private TextArea currentTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  //time formatter

    /**
     *Class constructor specifying a place to display atomic clock.
     * @param currentTime The text field in which the atomic clock is displayed
     */
    public CurrentTimeDisplay(TextArea currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * A method that adds the value of the current time to the text field.
     * @param time value of current time
     */

    @Override
    public void updateT(LocalTime time) {
        Platform.runLater(() -> {
            currentTime.clear();
            currentTime.appendText(time.format(dtf));  //fromatter time
        });
    }
}
