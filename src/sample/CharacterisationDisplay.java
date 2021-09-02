package sample;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class created to display characterisation of updated weather conditions on charts.
 * Class implements Interface Observer to get updated weather conditions.
 * @author  Weronika Warwas
 */

public class CharacterisationDisplay implements Observer {

    private ArrayList<Double> tempValues = new ArrayList<>(); //temperature
    private ArrayList<Double> hValues = new ArrayList<>();    //humidity
    private ArrayList<Double> pValues = new ArrayList<>();    //pressure
    private TextField tmin;   // minimal value of temperature
    private TextField tmax;   //maximum value of temperature
    private TextField sdt;    //temperature standard deviation
    private TextField hmin;   // minimal value of humidity
    private TextField hmax;   //maximum value of humidity
    private TextField sdh;    // humidity standard deviation
    private TextField pmin;   //minimal value of pressure
    private TextField pmax;   // maximum value of pressure
    private TextField sdp;    // pressure standard deviation
    private TextField nom;    // number of measurement
    DecimalFormat df = new DecimalFormat("####0.00");


    /**
     * Class constructor specifying a place to display characterisation of updated weather conditions.
     * @param tmin The text field in which the minimal value of temperature is displayed
     * @param tmax The text field in which the maximal value of temperature is displayed
     * @param sdt  The text field in which the value of temperature standard deviation is displayed
     * @param hmin The text field in which the minimal value of humidity is displayed
     * @param hmax The text field in which the maximal value of humidity is displayed
     * @param sdh The text field in which the value of humidity standard deviation is displayed
     * @param pmin The text field in which the minimal value of temperature is displayed
     * @param pmax The text field in which the maximal value of pressure is displayed
     * @param sdp The text field in which the value of pressure standard deviation is displayed
     * @param nom The text field in which the number of measurement is displayed
     */

    public CharacterisationDisplay(TextField tmin, TextField tmax, TextField sdt, TextField hmin,
                                   TextField hmax, TextField sdh, TextField pmin, TextField pmax, TextField sdp, TextField nom) {
        this.tmin = tmin;
        this.tmax = tmax;
        this.sdt = sdt;
        this.hmin = hmin;
        this.hmax = hmax;
        this.sdh = sdh;
        this.pmin = pmin;
        this.pmax = pmax;
        this.sdp = sdp;
        this.nom=nom;
    }

    /**
     * Method determines the characterisation of updated weather conditions.
     * @param temp temperature
     * @param pre  pressure
     * @param hum  humidity
     * @param t_max maximum temperature
     * @param t_min minimum  temperature
     * @param time updated time
     * @param response full response from server
     */
    @Override
    public void updateWeather(Double temp, Double pre, Double hum, Double t_max, Double t_min, LocalTime time, StringBuffer response) {
        Platform.runLater(() -> {

            tempValues.add(temp);
            hValues.add(hum);
            pValues.add(pre);
            nom.setText(String.valueOf(tempValues.size()));
            Double mean =0.;
            //******************  TEMPERATURE **************************************
            Double wynik = tempValues.get(0);
            for (int i=1; i<tempValues.size(); i++) {             //find max value
                if (wynik < tempValues.get(i)) {
                    wynik = tempValues.get(i);
                }
            }
            tmax.setText(wynik.toString());

            wynik = tempValues.get(0);                           //find min value
            for (int i=1;  i<tempValues.size(); i++) {
                if (wynik > tempValues.get(i)) {
                    wynik = tempValues.get(i);
                }
            }
            tmin.setText(wynik.toString());

            Double sum=0.;
            for (int i=0;  i<tempValues.size(); i++) {    //mean
                sum=sum+tempValues.get(i);
            }
            mean = sum/tempValues.size();

            sum=0.;
            for (int i=0;  i<tempValues.size(); i++) {    //standard deviation
                sum=sum +Math.pow((tempValues.get(i)-mean),2);
            }
            sdt.setText(String.valueOf(df.format(Math.sqrt(sum/tempValues.size()))));

            //******************  PRESSURE  **************************************
            wynik=pValues.get(0);
            for (int i=1; i<pValues.size(); i++) {             //find max value
                if (wynik < pValues.get(i)) {
                    wynik = pValues.get(i);
                }
            }
            pmax.setText(wynik.toString());

            wynik = pValues.get(0);                          //find min value
            for (int i=1;  i<pValues.size(); i++) {
                if (wynik > pValues.get(i)) {
                    wynik = pValues.get(i);
                }
            }
            pmin.setText(wynik.toString());

            sum=0.;
            for (int i=0;  i<pValues.size(); i++) {    //mean
                sum=sum+pValues.get(i);
            }
            mean = sum/pValues.size();

            sum=0.;
            for (int i=0;  i<pValues.size(); i++) {      //standard deviation
                sum=sum +Math.pow((pValues.get(i)-mean),2);
            }
            sdp.setText(String.valueOf(df.format(Math.sqrt(sum/pValues.size()))));

            //******************   HUMIDITY **************************************
            wynik=hValues.get(0);                            //find max value
            for (int i=1; i<hValues.size(); i++) {
                if (wynik < hValues.get(i)) {
                    wynik = hValues.get(i);
                }
            }
            hmax.setText(wynik.toString());

            wynik = hValues.get(0);                              //find min value
            for (int i=1;  i<hValues.size(); i++) {
                if (wynik > hValues.get(i)) {
                    wynik = hValues.get(i);
                }
            }
            hmin.setText(wynik.toString());

            sum=0.;

            for (int i=0;  i<hValues.size(); i++) {             //mean
                sum=sum+hValues.get(i);
            }
            mean = sum/hValues.size();

            sum=0.;
            for (int i=0;  i<hValues.size(); i++) {             //standard deviation
                sum=sum +Math.pow((hValues.get(i)-mean),2);
            }
            sdh.setText(String.valueOf(df.format(Math.sqrt(sum/hValues.size()))));

        });
    }

    /**
     * Method determines the characterisation of weather conditions from json file.
     * @param temp1Values Array with contains temperature values
     * @param p1Values Array with contains pressure values
     * @param h1Values Array with contains air humidity values
     */
    public void fromJson(ArrayList<Double> temp1Values, ArrayList<Double> p1Values, ArrayList<Double> h1Values){

        Double sum =0.;
        Double mean =0.;
        //******************   TEMPERATURE **************************************
        Double wynik = temp1Values.get(0);
        for (int i=1; i<temp1Values.size(); i++) {            //find max value
            if (wynik < temp1Values.get(i)) {
                wynik = temp1Values.get(i);
            }
        }
        tmax.setText(wynik.toString());

        wynik = temp1Values.get(0);                        //find min value
        for (int i=1;  i<temp1Values.size(); i++) {
            if (wynik > temp1Values.get(i)) {
                wynik = temp1Values.get(i);
            }
        }
        tmin.setText(wynik.toString());


        for (int i=0;  i<temp1Values.size(); i++) {              //mean
            sum=sum+temp1Values.get(i);
        }
        mean = sum/temp1Values.size();

        sum=0.;
        for (int i=0;  i<temp1Values.size(); i++) {             //standard deviation
            sum=sum +Math.pow((temp1Values.get(i)-mean),2);
        }
        sdt.setText(String.valueOf(df.format(Math.sqrt(sum/temp1Values.size()))));

        //******************   PRESSURE **************************************
        wynik=p1Values.get(0);
        for (int i=1; i<p1Values.size(); i++) {                    //find max value
            if (wynik < p1Values.get(i)) {
                wynik = p1Values.get(i);
            }
        }
        pmax.setText(wynik.toString());

        wynik = p1Values.get(0);                                    //find min value
        for (int i=1;  i<p1Values.size(); i++) {
            if (wynik > p1Values.get(i)) {
                wynik = p1Values.get(i);
            }
        }
        pmin.setText(wynik.toString());

        sum=0.;
        for (int i=0;  i<p1Values.size(); i++) {                //mean
            sum=sum+p1Values.get(i);
        }
        mean = sum/p1Values.size();

        sum=0.;
        for (int i=0;  i<p1Values.size(); i++) {             //standard deviation
            sum=sum +Math.pow((p1Values.get(i)-mean),2);
        }
        sdp.setText(String.valueOf(df.format(Math.sqrt(sum/p1Values.size()))));

        //******************   HUMIDITY **************************************
        wynik=h1Values.get(0);                           //find max value
        for (int i=1; i<h1Values.size(); i++) {
            if (wynik < h1Values.get(i)) {
                wynik = h1Values.get(i);
            }
        }
        hmax.setText(wynik.toString());

        wynik = h1Values.get(0);                          //find min value
        for (int i=1;  i<h1Values.size(); i++) {
            if (wynik > h1Values.get(i)) {
                wynik = h1Values.get(i);
            }
        }
        hmin.setText(wynik.toString());

        sum=0.;

        for (int i=0;  i<h1Values.size(); i++) {           //mean
            sum=sum+h1Values.get(i);
        }
        mean = sum/h1Values.size();

        sum=0.;
        for (int i=0;  i<h1Values.size(); i++) {              //standard deviation
            sum=sum +Math.pow((h1Values.get(i)-mean),2);
        }
        sdh.setText(String.valueOf(df.format(Math.sqrt(sum/h1Values.size()))));
        nom.setText(String.valueOf(temp1Values.size()));      // number of measurement

    }

}
