package model;

/**
 * Modelklasse des Spielers
 *
 * @author Martin Düppenbecker
 * @version 1.1
 * @since 30.08.2021
 */

public class Spieler {
    private String name;
    private int punktestand;
    private int streak;


    /**
     * Konstruktor
     * @param name
     * @param punktestand
     */
    public Spieler(String name, int punktestand){
        this.name = name;
        this.punktestand = punktestand;
    }


    //Getter & Setter

    /**
     * Gibt den Namen des Spielers zurück
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen dse Spielers
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Punktestand des Spielers zurück
     * @return punktestand
     */
    public int getPunktestand() {
        return punktestand;
    }

    /**
     * Setzt den Punktestand des Spielers
     * @param punktestand
     */
    public void setPunktestand(int punktestand) {
        this.punktestand = punktestand;
    }

    /**
     * Gibt die Höhe der Streak zurück
     * @return streak
     */
    public int getStreak() {
        return streak;
    }

    /**
     * Setzt die Höhe der Streak
     * @param streak
     */
    public void setStreak(int streak) {
        this.streak = streak;
    }


    //Methoden

    /**
     * Fügt Punkte zum Punktestand hinzu
     * @param punkte
     */
    public void punkteHinzufuegen(int punkte){
        this.punktestand += punkte;
    }


}