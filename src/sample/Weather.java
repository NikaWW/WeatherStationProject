package sample;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Map;

/**
 * Class crated to communicate with Open Weather Map server and to gets weather conditions.
 * @author  Weronika Warwas
 */
public class Weather {

    private String city;
    private double temperature;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;
    private String country;
    private StringBuffer response;

    /**
     * Class constructor specifying name of city
     * @param city city name
     */

    public Weather(String city) {
        this.city = city;
    }

    public StringBuffer getResponse() {
        return response;
    }

    public void setResponse(StringBuffer response) {
        this.response = response;
    }

    /**
     * Method to return temperature from method weatherData.
     * @return temperature value
     */
    public double getTemperature() {
        weatherData(city);
        return temperature;
    }
    /**
     * Method to return pressure from method weatherData.
     * @return pressure
     */

    public double getPressure() {
        weatherData(city);
        return pressure;
    }
    /**
     * Method to return humidity from method weatherData.
     * @return humidity
     */

    public double getHumidity() {
        weatherData(city);
        return humidity;
    }
    /**
     * Method to return minimal temperature from method weatherData.
     * @return minimal temperature
     */

    public double getTemp_min() {
        weatherData(city);
        return temp_min;
    }
    /**
     * Method to return maximum temperature from method weatherData.
     * @return maximum temperature
     */

    public double getTemp_max() {
        weatherData(city);
        return temp_max;
    }
    /**
     * Method to return country name from method weatherData.
     * @return country name
     */

    public String getCountry() {
        weatherData(city);
        return country;
    }

    /**
     * Metod setting city
     * @param city city name
     */

    public void setCity(String city) {
        this.city = city;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }
    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    /**
     * Method downloading weather conditions from the server after accepting the city name.
     * @param city city name
     */
    public void weatherData(String city) {
        StringBuffer response = new StringBuffer();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&APPID="; //add api key

        try {
            URL obj = null;
            try {
                obj = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) obj.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException ex) {
            System.out.println("Wrong url");

        } catch (IOException ex) {
            System.out.println("Connection failed!");
        }


        Gson gson = new Gson();
        setResponse(response);
        String tekst = response.toString();    //Saveing response from server to text
        Map m = gson.fromJson(tekst, Map.class);   //Creating a map from response

        //Country name from "sys" key
        String sys = m.get("sys").toString();
        Map n = gson.fromJson(sys, Map.class);
        country = (String) n.get("country");

        //Weather condition from "main" key
        String tekst1 = m.get("main").toString();
        Map p = gson.fromJson(tekst1, Map.class);
        temperature = (double) p.get("temp");
        pressure = (double) p.get("pressure");
        humidity = (double) p.get("humidity");
        temp_min = (double) p.get("temp_min");
        temp_max = (double) p.get("temp_max");
    }

}