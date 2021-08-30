package data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Datahandler für die Bilder
 *
 * @author Martin Düppenbecker
 * @version 1.1
 * @since 30.08.2021
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();


    //Getter

    /**
     * Gibt die Instanz des Datahandlers zurück
     * @return Instanz
     */
    public static DataHandler getInstance(){
        return instance;
    }


    //Methoden

    /**
     * Gibt einen Vector mit zufällig von resources/bilder ausgewählten Bildern zurück
     * Die Anzahl der Bilder kann über den Parameter "anzahl" bestimmt werden
     * @param anzahl
     * @return bilderIcons
     */
    public Vector<ImageIcon> randomBilder(int anzahl){
        Vector<ImageIcon> imageIcons = new Vector<>(anzahl);

        //Listet die Pfäde aller Bilder auf
        String[] bilderPath = new File("resources/bilder/").list();

        //Konvertiert diese in eine Liste, damit sie random sortiert werden kann
        List randomPaths = Arrays.asList(bilderPath);
        Collections.shuffle(randomPaths);

        //Schneidet die Liste in die gewünschte Länge und konvertiert sie zum Array zurück
        //Leider ist es ein bisschen kompliziert Object[] zu String[] umzuwandeln
        Object[] tmpArr = randomPaths.subList(0, anzahl).toArray();
        bilderPath = Arrays.copyOf(tmpArr, tmpArr.length, String[].class);


        try {
            for (int i = 0; i < anzahl; i++) {
                //Füllt den Vector mit den Bildern ab
                ImageIcon icon = new ImageIcon(ImageIO.read(new File("resources/bilder/" + bilderPath[i])));
                icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_FAST));
                imageIcons.add(icon);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return imageIcons;

    }

}
