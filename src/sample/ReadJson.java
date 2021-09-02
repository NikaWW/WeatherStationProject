package sample;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Class with read gson file and save weather conditions from gson flie to array lists.
 * @author  Weronika Warwas
 */
public class ReadJson {

    private ArrayList<Double> temperatureValues = new ArrayList<>();
    private ArrayList<Double> humValues = new ArrayList<>();
    private ArrayList<Double> preValues = new ArrayList<>();
    private ArrayList<String> timesValues = new ArrayList<>();
    private ArrayList<Double> tmaxValues = new ArrayList<>();
    private ArrayList<Double> tminValues = new ArrayList<>();
    private String city;
    private String country;

    public ArrayList<Double> getTemperatureValues() {
        return temperatureValues;
    }

    public ArrayList<Double> getHumValues() {
        return humValues;
    }

    public ArrayList<Double> getPreValues() {
        return preValues;
    }

    public ArrayList<String> getTimesValues() {
        return timesValues;
    }

    public ArrayList<Double> getTmaxValues() {
        return tmaxValues;
    }

    public ArrayList<Double> getTminValues() {
        return tminValues;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setTemperatureValues(ArrayList<Double> temperatureValues) {
        this.temperatureValues = temperatureValues;
    }

    public void setHumValues(ArrayList<Double> humValues) {
        this.humValues = humValues;
    }

    public void setPreValues(ArrayList<Double> preValues) {
        this.preValues = preValues;
    }

    public void setTimesValues(ArrayList<String> timesValues) {
        this.timesValues = timesValues;
    }

    public void setTmaxValues(ArrayList<Double> tmaxValues) {
        this.tmaxValues = tmaxValues;
    }

    public void setTminValues(ArrayList<Double> tminValues) {
        this.tminValues = tminValues;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Method reading a gson file with all weather conditions
     * @param path path of gson file
     * @throws IOException ioexception
     */

    public void dataload(String path)  throws IOException {

        String[] weather = null;

        File file=new File(path);
        Gson gson=new Gson();

        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))) {
            weather=gson.fromJson(bufferedReader, String[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList Weaterdata = new ArrayList(Arrays.asList(weather));  //Array list with all weather responses

        for(int i=0; i<weather.length ;i++) {
            String tekst = weather[i];
            Map m = gson.fromJson(tekst, Map.class);

            //Country name from "sys" key
            String sys = m.get("sys").toString();
            Map n = gson.fromJson(sys, Map.class);
            country = (String) n.get("country");
            setCountry(country);

            // Weather conditions from "main" key
            String tekst1 = m.get("main").toString();
            Map p = gson.fromJson(tekst1, Map.class);
            city = (String) m.get("name");
            setCity(city);
            double temperature = (double) p.get("temp");
            double pressure = (double) p.get("pressure");
            double humidity = (double) p.get("humidity");
            double temp_min = (double) p.get("temp_min");
            double temp_max = (double) p.get("temp_max");
            temperatureValues.add(temperature);
            setTemperatureValues(temperatureValues);
            preValues.add(pressure);
            setPreValues(preValues);
            humValues.add(humidity);
            setHumValues(humValues);
            tmaxValues.add(temp_max);
            setTmaxValues(tmaxValues);
            tminValues.add(temp_min);
            setTminValues(tminValues);
            timesValues.add(String.valueOf(i+1));
            setTimesValues(timesValues);
        }
    }

}//end class