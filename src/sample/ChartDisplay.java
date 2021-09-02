package sample;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class created to display updated weather conditions on charts.
 * Class implements Interface Observer to get updated weather conditions.
 * @author  Weronika Warwas
 */

public class ChartDisplay implements Observer {

    private ArrayList<Double> tempValues = new ArrayList<>();
    private ArrayList<Double> hValues = new ArrayList<>();
    private ArrayList<Double> pValues = new ArrayList<>();
    private ArrayList<String> tsValues = new ArrayList<>();
    private LineChart<String,Number> chart;
    private LineChart<String,Number> chartH;
    private LineChart<String,Number> chartP;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Class constructor specifying weather conditions charts to display.
     * @param chart temperature chart from time.
     * @param chartH humidity chart from time.
     * @param chartP pressure chart from time.
     */
    public ChartDisplay(LineChart<String, Number> chart, LineChart<String, Number> chartH, LineChart<String,Number> chartP) {
        this.chart = chart;
        this.chartH = chartH;
        this.chartP=chartP;
    }

    /**
     * Methods filling charts with updated weather conditions series.
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
            chart.getData().removeAll(chart.getData());
            chartH.getData().removeAll(chartH.getData());
            chartP.getData().removeAll(chartP.getData());
            tempValues.add(temp);
            hValues.add(hum);
            pValues.add(pre);
            String t = String.valueOf(time.format(dtf));
            tsValues.add(t);
            XYChart.Series<String,Number> series_temp = new XYChart.Series<>();
            XYChart.Series<String,Number> series_h = new XYChart.Series<>();
            XYChart.Series<String,Number> series_p= new XYChart.Series<>();
            for (int i=0; i<tsValues.size(); i++) {
                series_temp.getData().add(new XYChart.Data<String, Number>((tsValues.get(i)),(tempValues.get(i))));
                series_h.getData().add(new XYChart.Data<String,Number>((tsValues.get(i)),(hValues.get(i))));
                series_p.getData().add(new XYChart.Data<String, Number>((tsValues.get(i)),(pValues.get(i))));
            }
            chart.getData().addAll(series_temp);
            chartH.getData().addAll(series_h);
            chartP.getData().addAll(series_p);
        });
    }

    /**
     * Methods filling charts with weather conditions series from Json file.
     * @param temp1Values array list with temperatures values
     * @param p1Values array list with pressure values
     * @param h1Values array list with humidity values
     * @param ts1Values array list with time values
     */

    public void fromJson(ArrayList<Double> temp1Values, ArrayList<Double> p1Values, ArrayList<Double> h1Values,ArrayList<String> ts1Values ){
        chart.getData().removeAll(chart.getData());
        chartH.getData().removeAll(chartH.getData());
        chartP.getData().removeAll(chartP.getData());

        XYChart.Series<String,Number> series_temp = new XYChart.Series<>();
        XYChart.Series<String,Number> series_h = new XYChart.Series<>();
        XYChart.Series<String,Number> series_p= new XYChart.Series<>();

        for (int i=0; i<temp1Values.size(); i++) {
            series_temp.getData().add(new XYChart.Data<String, Number>((ts1Values.get(i)),(temp1Values.get(i))));
            series_h.getData().add(new XYChart.Data<String,Number>((ts1Values.get(i)),(h1Values.get(i))));
            series_p.getData().add(new XYChart.Data<String, Number>((ts1Values.get(i)),(p1Values.get(i))));
        }
        chart.getData().addAll(series_temp);
        chartH.getData().addAll(series_h);
        chartP.getData().addAll(series_p);
    }

}//end









