package sample;

import javafx.scene.image.Image;
import java.io.File;
import java.net.MalformedURLException;

/**
 * A class that allows to display the flag of the city in which the weather is checked.
 * A country name and name of flag picture is write by ISO 3166-1 system( the International Standard for country codes and codes for their subdivisions).
 * Folder with flags picture is located in project file.
 * @author  Weronika Warwas
 */

public class FlagsImageDisplay {

    /**
     * The method that takes the image of the country flag.
     * @param country name of country where the weather is checked.
     * @return image with flag
     */
    public Image getIm(String country) {

        File file = new File("C:\\Users\\Weronika\\Desktop\\Weronika_Warwas_WeatherStationProject\\flags-mini\\"+country.toLowerCase()+".png");
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image im = new Image(localUrl);
        return im;
    }

}