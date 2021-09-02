package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import com.google.gson.Gson;
import java.io.*;


public class Controller {

    @FXML
    private TextField txtCity; //The text area with in users write a city
    @FXML
    private TextField interval;  //The text area with in users write a time interval
    @FXML
    private TextArea txtLocation; //The text area in which the location is displayed
    @FXML
    private TextArea txtTemp;  //The text area in which the temperature is displayed
    @FXML
    private TextArea txtPressure; //The text area in which the pressure is displayed
    @FXML
    private TextArea txtTmin; //The text area in which the min temperature is displayed
    @FXML
    private TextArea txtTmax; //The text area in which the max temperature is displayed
    @FXML
    private TextArea txtHumidity; //The text area in which the humidity is displayed
    @FXML
    private TextField txtTime; //The field area in which the time of last update is displayed
    @FXML
    private TextArea txtFahrenheit;  //The text area in which the temperature in fahrenheit is displayed

    @FXML
    private LineChart<String, Number> chart1;  //temperature chart from time
    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart<String, Number> Hchart;  //humidity chart from time
    @FXML
    private NumberAxis hAxis;

    @FXML
    private LineChart<String, Number> pChart;  //pressure chart from time
    @FXML
    private NumberAxis pAxis;

    @FXML
    private ImageView image;    //The image view in which the country flag is displayed

    @FXML
    private TextField Ttmax;  //The text field in which the max value of temperature is displayed
    @FXML
    private TextField Tsdp;  //The text field in which the value of pressure standard deviation is displayed
    @FXML
    private TextField Tsdt;  //The text field in which the value of temperature standard deviation is displayed
    @FXML
    private TextField Tpmin; //The text field in which the min value of pressure is displayed
    @FXML
    private TextField Thmin; //The text field in which the min value of humidity is displayed
    @FXML
    private TextField Ttmin; //The text field in which the min value of temperature is displayed
    @FXML
    private TextField Tpmax; //The text field in which the max value of pressure is displayed
    @FXML
    private TextField Thmax; //The text field in which the max value of humidity is displayed
    @FXML
    private TextField Tsdh;  //The text field in which the value of humidity standard deviation is displayed
    @FXML
    private TextField Tnom;  //The text field in which the number of measurement is displayed

    @FXML
    private Button btnStart;  //Start button
    @FXML
    private Button btnPause;  //Pause button
    @FXML
    private Button btnStop;   //Stop button
    @FXML
    private Button btnLoad;   //Load data button
    @FXML
    private Button Clear;      //Clear all button

    @FXML
    private TextArea txtcurrentTime;  //The field area in which the current time is displayed

    //****************************************
    private WeatherStation weatherStation;
    private SaveJson saveJson;
    private String filePath = "C:\\Users\\Weronika\\Desktop\\Weronika_Warwas_WeatherStationProject\\weatherData.json";   //file path for json file
    private AtomicClock atomicClock;

    /**
     * Button Search on action use button clear all to clear all filed, and finding city write in by users in txtCity TextFiled.
     * By clicked button also observers are add to weather station and atomic clock.
     * Atomic clock start work.
     * @param event event button clicked
     */

    @FXML
    void btnSearchClicked(ActionEvent event) {
        btnClearOn(event);
        btnStop.setDisable(true);
        btnPause.setDisable(true);
        btnLoad.setDisable(true);
        Clear.setDisable(false);

        FlagsImageDisplay flag = new FlagsImageDisplay();
        Weather w = new Weather((String) txtCity.getText());
        StringBuilder message = new StringBuilder();
        try {
            String city = txtCity.getText();
            txtLocation.appendText(city+", "+w.getCountry());
        } catch (NullPointerException e) {
            txtLocation.appendText("Wrong city name");
        }
        image.setImage(flag.getIm(w.getCountry()));
        image.fitHeightProperty();
        image.fitWidthProperty();

        int inte=60000;

        weatherStation = new WeatherStation(txtCity.getText(),inte);
        TxtAreaDisplay display = new TxtAreaDisplay(txtTemp, txtPressure, txtHumidity, txtTmax, txtTmin,txtTime,txtFahrenheit);
        ChartDisplay display1 = new ChartDisplay(chart1, Hchart, pChart);
        CharacterisationDisplay display2= new CharacterisationDisplay(Ttmin,Ttmax,Tsdt,Thmin,Thmax,Tsdh,Tpmin,Tpmax,Tsdp,Tnom);
        saveJson = new SaveJson();
        weatherStation.addObserver(display);
        weatherStation.addObserver(display1);
        weatherStation.addObserver(display2);
        weatherStation.addObserver(saveJson);
        atomicClock = new AtomicClock();

        CurrentTimeDisplay currnet = new CurrentTimeDisplay(txtcurrentTime);
        atomicClock.addObserverTime(currnet);
        atomicClock.start();
        btnStart.setDisable(false);
    }

    /**
     * Button Start on action start update weather conditions
     * @param event event button clicked
     */

    @FXML
    void btnStratClicked(ActionEvent event) {
        int inte;
        try {
            inte = (Integer.parseInt(interval.getText()))*60*1000;
        } catch (NumberFormatException e) {
            inte=60000;
        }
        weatherStation.setInterval(inte);
        weatherStation.start();
        yAxis.setTickUnit(0.1);
        hAxis.setLabel("Humidity");
        yAxis.setLabel("Temperature");
        pAxis.setLabel("Pressure");
        Clear.setDisable(false);
        btnPause.setDisable(false);
        btnStop.setDisable(false);
        Clear.setDisable(true);

    }

    /**
     * Button Stop on action finish update weather conditions and saving all collected weather data to json file
     * @param event event button clicked
     */

    @FXML
    void btnStopClicked(ActionEvent event) {
        Clear.setDisable(false);
        btnStart.setDisable(true);
        weatherStation.stop();
        btnLoad.setDisable(false);
        btnPause.setDisable(true);

        Gson gson=new Gson();

        File file=new File(filePath);

        try(FileWriter fileWriter=new FileWriter(file)) {

            gson.toJson(saveJson.getData(), fileWriter);

        }catch (Exception e){
            System.out.println("IO error");
        }

    }

    /**
     * Button clear on action clear all data, text filed and charts.
     * @param event event button clicked
     */
    @FXML
    void btnClearOn(ActionEvent event) {
        txtLocation.clear();
        txtTemp.clear();
        txtTmax.clear();
        txtTmin.clear();
        txtPressure.clear();
        txtHumidity.clear();
        txtFahrenheit.clear();
        Tnom.clear();
        Tsdh.clear();
        Tsdp.clear();
        Tsdt.clear();
        Tpmax.clear();
        Tpmin.clear();
        Thmax.clear();
        Thmin.clear();
        Ttmax.clear();
        Ttmin.clear();
        pChart.getData().clear();
        chart1.getData().clear();
        Hchart.getData().clear();
    }

    /**
     * Button pause on action interrupt update weather conditions until button start will be not clicked.
     * @param event event button clicked
     */
    @FXML
    void btnPauseClicked(ActionEvent event) {
        weatherStation.interrupt();
    }

    /**
     * Button LoadData takes data from the json file and displays it on charts and text boxes.
     * @param event event button clicked
     */
    @FXML
    void btnLoadData(ActionEvent event) {
        btnClearOn(event);
        btnStart.setDisable(true);
        btnPause.setDisable(true);
        btnStop.setDisable(true);

        ReadJson readJson = new ReadJson();
        try {
            readJson.dataload(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChartDisplay displayJ = new ChartDisplay(chart1, Hchart, pChart);
        displayJ.fromJson(readJson.getTemperatureValues(),readJson.getPreValues(), readJson.getHumValues(),readJson.getTimesValues());

        TxtAreaDisplay displayJTxT = new TxtAreaDisplay(txtTemp, txtPressure, txtHumidity, txtTmax, txtTmin,txtTime,txtFahrenheit);
        displayJTxT.fromJson(readJson.getTemperatureValues(),readJson.getPreValues(), readJson.getHumValues(),readJson.getTmaxValues(),readJson.getTminValues());

        CharacterisationDisplay displayJCh = new CharacterisationDisplay(Ttmin,Ttmax,Tsdt,Thmin,Thmax,Tsdh,Tpmin,Tpmax,Tsdp,Tnom);
        displayJCh.fromJson(readJson.getTemperatureValues(),readJson.getPreValues(), readJson.getHumValues());

        FlagsImageDisplay flag = new FlagsImageDisplay();

        String city = txtCity.getText();
        txtLocation.appendText(readJson.getCity()+", "+readJson.getCountry());

        image.setImage(flag.getIm(readJson.getCountry()));
        image.fitHeightProperty();
        image.fitWidthProperty();

    }

}