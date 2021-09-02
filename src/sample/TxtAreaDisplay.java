package sample;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class created to display updated weather conditons.
 * Class implements Interface Observer to get updated weather condtions.
 * @author  Weronika Warwas
 */

public class TxtAreaDisplay implements Observer {

    private TextArea txtTemp;
    private TextArea txtPressure;
    private TextArea txtHumidity;
    private TextArea txtTemp_max;
    private TextArea txtTemp_min;
    private TextField txtTime;
    private TextArea txtF;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private java.text.DecimalFormat df=new java.text.DecimalFormat();

    /**
     * Class constructor specifying a place to display weather conditions
     * @param txtTemp The text area in which the updated temperature is displayed
     * @param txtPressure The text area in which the updated pressure is displayed
     * @param txtHumidity The text area in which the updated humidity is displayed
     * @param txtTemp_max The text area in which the updated maximal temperature is displayed
     * @param txtTemp_min The text area in which the updated minimal temperature is displayed
     * @param txtTime The text filed in which the updated time is displayed
     * @param txtF The text area in which the updated temperature in fahrenheits is displayed
     */

    public TxtAreaDisplay(TextArea txtTemp, TextArea txtPressure, TextArea txtHumidity, TextArea txtTemp_max, TextArea txtTemp_min, TextField txtTime,TextArea txtF ) {
        this.txtTemp = txtTemp;
        this.txtPressure = txtPressure;
        this.txtHumidity = txtHumidity;
        this.txtTemp_max = txtTemp_max;
        this.txtTemp_min = txtTemp_min;
        this.txtTime = txtTime;
        this.txtF=txtF;
    }

    /**
     * Method filling text fields with updated weather conditions.
     * @param temp temperature
     * @param pre pressure
     * @param hum humidity
     * @param t_max maximum value of temperature
     * @param t_min minimum value of temperature
     * @param time current time
     * @param response full respons from server
     */

    @Override
    public void updateWeather(Double temp, Double pre, Double hum, Double t_max, Double t_min, LocalTime time, StringBuffer response) {

        Platform.runLater(() -> {
            txtTemp.clear();    //clearing all text fields
            txtHumidity.clear();
            txtPressure.clear();
            txtTemp_max.clear();
            txtTemp_min.clear();
            txtTime.clear();
            txtF.clear();

            txtPressure.appendText(String.valueOf(pre));   //Filling text fields with current weather conditions
            txtHumidity.appendText(String.valueOf(hum));
            txtTemp.appendText(String.valueOf(temp));
            txtTemp_max.appendText(String.valueOf(t_max));
            txtTemp_min.appendText(String.valueOf(t_min));
            txtF.appendText(String.valueOf(df.format(((temp*1.8)+32))));    // conversion temperature units from celcius to fahrenheit
            txtTime.appendText(String.valueOf(time.format(dtf)));
        });
    }

    /**
     * Methods filling text fields with weather conditions from Json file.
     * @param temp1Values array list with temperatures values
     * @param p1Values array list with pressure values
     * @param h1Values array list with humidity values
     * @param t1maxValues array list with minimum temperatures values
     * @param t1minValues array list with maximum temperatures values
     */

    public void fromJson(ArrayList<Double> temp1Values, ArrayList<Double> p1Values, ArrayList<Double> h1Values,ArrayList<Double> t1maxValues,ArrayList<Double> t1minValues ){
        int i =0;
        txtTemp.clear();
        txtHumidity.clear();
        txtPressure.clear();
        txtTemp_max.clear();
        txtTemp_min.clear();
        txtF.clear();
        i=temp1Values.size();
        txtTemp.appendText(String.valueOf(temp1Values.get(i-1)));
        txtPressure.appendText(String.valueOf(p1Values.get(i-1)));
        txtHumidity.appendText(String.valueOf(h1Values.get(i-1)));
        txtTemp_max.appendText(String.valueOf(t1maxValues.get(i-1)));
        txtTemp_min.appendText(String.valueOf(t1minValues.get(i-1)));
        txtF.appendText(String.valueOf((temp1Values.get(i-1)*1.8)+32));
    }

}//end class



