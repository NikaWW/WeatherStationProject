package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that creat and starts displaying the application.
 * Main class extends {@code Application} class.
 * @author  Weronika Warwas
 */

public class Main extends Application {
    /**
     * The method that creates application.
     * @param primaryStage primaryStage
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Weather App"); //name of window application
        primaryStage.setScene(new Scene(root, 1000, 700)); //size of application window
        primaryStage.show();

    }

    public static void main(String[] args) { launch(args);
    }
}
