package sample;

import javafx.application.Platform;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * A class that records responses from the server about weather conditions.
 * @author  Weronika Warwas
 */

public class SaveJson implements Observer {

    private ArrayList<StringBuffer> data=new ArrayList();

    /**
     * Method saving response form server about weather
     * @return array list with responses from Open Weather Map server
     */

    public ArrayList<StringBuffer> getData() {
        return data;
    }

    /**
     * Method collecting updated responses from server
     * @param response response from server
     */

    @Override
    public void updateWeather(Double temp, Double pre, Double hum, Double t_max, Double t_min, LocalTime time, StringBuffer response) {
        Platform.runLater(() -> {
            data.add(response);
        });
    }

}//end class
