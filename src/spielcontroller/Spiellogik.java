package spielcontroller;


import data.DataHandler;
import gui.EinstellungenGUI;
import gui.HauptseiteGUI;
import gui.SpielfeldGUI;
import model.Einstellungen;
import model.Memorykarte;
import model.Spieler;

import javax.swing.*;
import java.util.Collections;
import java.util.Vector;

/**
 * Diese Klasse beinhaltet die gesamte Spiellogik
 *
 * @author Martin Düppenbecker
 * @version 1.1
 * @since 30.06.2021
 */

public class Spiellogik {
    private SpielfeldGUI spielfeldGUI;
    private Spiellogik instance;
    private DataHandler dataHandler;
    private Vector<Memorykarte> memorykarten;
    private Vector<JButton> buttons;
    private Vector<ImageIcon> imageIcons;
    private Einstellungen einstellungen;
    private Spieler spieler1;
    private Spieler spieler2;

    private Spieler spielerAnDerReihe;
    private Memorykarte letzteKarte;
    private int letzerButtonIndex;
    private boolean ersterZug;
    private int streak = 0;
    private int count = 0;


    /**
     * Konstruktor
     */
    public Spiellogik(){
        instance = this;
        einstellungen = new Einstellungen();
        dataHandler = new DataHandler();

        spieler1 = new Spieler("Spieler 1",0);
        spieler2 = new Spieler("Spieler 2", 0);
        spielerAnDerReihe = spieler1;
    }


    //Getter & Setter

    /**
     * Gibt die Spiellogik-Instanz zurück
     * @return instance
     */
    public Spiellogik getInstance(){
        return instance;
    }

    /**
     * Gibt letzerButtonIndex zurück
     * @return letzerButtonIndex
     */
    public int getLetzerButtonIndex(){
        return letzerButtonIndex;
    }

    /**
     * Gibt die Punktzahl für den jeweiligen Spieler zurück
     * @param spielerNr
     * @return punktestand
     */
    public int getSpielstaende(int spielerNr){
        if (spielerNr == 1){
            return spieler1.getPunktestand();
        }
        return spieler2.getPunktestand();

    }


    //Methoden

    /**
     * Wird aufgerufen, wenn ein Memorybutton betätigt wird.
     * Hier ist essenziell die gesamte Spiellogik drin.
     * Gibt einen Code zurück, der für einen Ausgangszustand steht.
     * 0 = 1.Zug  |  1 = 2.Zug  |  2 = 2.Zug, Paar gefunden  |  3 = Spiel fertig
     * @param indexOfButton
     * @return code
     */
    public int buttonGedrueckt(int indexOfButton){
        int code = -1;
        Memorykarte jetzigeKarte = memorykarten.get(imageIcons.indexOf(buttons.get(indexOfButton).getIcon()));
        if (ersterZug){
            if (letzerButtonIndex != indexOfButton){
                letzerButtonIndex = indexOfButton;
                letzteKarte = jetzigeKarte;
                code = 0;
                ersterZug = false;
            }
        }
        else if (letzerButtonIndex != indexOfButton){
            code = 1;
            if (letzteKarte.getId() == jetzigeKarte.getId()){
                if (einstellungen.getBonusstreak()){
                    jetzigeKarte.streak(streak);
                    streak++;
                }
                spielerAnDerReihe.punkteHinzufuegen(jetzigeKarte.getPunkte());
                spielerAnDerReihe = spielerAnDerReihe == spieler1 ? spieler2 : spieler1;    //Damit er weiterspielen kann
                count++;
                code = 2;
            }
            if (einstellungen.getBonusstreak() && code != 2){
                streak = 0;
            }
            spielerAnDerReihe = spielerAnDerReihe == spieler1 ? spieler2 : spieler1;

            if (count >= memorykarten.size() / 2){  //Spiel ist vorbei
                letzerButtonIndex = -1;
                letzteKarte = null;
                spielerAnDerReihe = spieler1;
                count = 0;
                ersterZug = true;
                code = 3;
            }
            ersterZug = true;
        }

        return code;    //0 = 1.Zug  |  1 = 2.Zug  |  2 = 2.Zug, Paar gefunden  |  3 = Spiel fertig
    }

    /**
     * Startet das Spiel
     */
    public void startGame(){
        spieler1.setPunktestand(0);
        spieler2.setPunktestand(0);
        spielfeldGUI = new SpielfeldGUI(this,kartenAufbereiten());
        letzerButtonIndex = -1;
        ersterZug = true;

    }

    /**
     * Startet die Einstellungen
     * @param parentOfEinstellungen
     */
    public void startEinstellungen(JFrame parentOfEinstellungen) {
        if (einstellungen == null) {
            einstellungen = new Einstellungen();
        }
        EinstellungenGUI einstellungenGUI = new EinstellungenGUI(parentOfEinstellungen, einstellungen);
        einstellungen = einstellungenGUI.getEinstellungen();
    }

    /**
     * Dient, im Kern, als Zwischenmethoden zwischen GUI's und der randomBilder()-Methode im DataHandler
     * Jedoch wird hier auch gleich der Memorykarten-Vector, mit der jeweiligen Referenz zum Bild in der Memorykarte, erstellt
     * @return buttons mit integrierten ImageIcon, die direkt im GUI verwendet werden können.
     */
    private Vector<JButton> kartenAufbereiten(){
        Vector<JButton> buttons = new Vector<>((int)Math.pow(einstellungen.getSpielfeldGroesse(), 2));
        Vector<ImageIcon> imageIcons = dataHandler.randomBilder((int)(Math.pow(einstellungen.getSpielfeldGroesse(), 2) / 2));

        memorykarten = new Vector<>(buttons.capacity() / 2);

        //Füllt den memorykarten-Vector mit neuen Memorykarten, die in der ID den Index des Bildes, im bilder-Vector, gespeichert hat
        for (int i = 0; i < imageIcons.size(); i++) {
            memorykarten.add(new Memorykarte(i));
            memorykarten.add(new Memorykarte(i));

        }
        //damit sie auf dem Spielfeld random verteilt sind
        Collections.shuffle(memorykarten);

        for (int i = 0; i < memorykarten.size(); i++) {
            buttons.add(new JButton(imageIcons.get(memorykarten.get(i).getId())));
        }

        this.buttons = buttons;
        this.imageIcons = imageIcons;

        return buttons;
    }


    //Main

    /**
     * main-Methode
     * @param args
     */
    public static void main(String[] args) {
        Spiellogik spiellogik = new Spiellogik();
        new HauptseiteGUI(spiellogik.getInstance());
    }
}
