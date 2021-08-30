package model;

/**
 * Modelklasse der Einstellungen
 *
 * @author Martin Düppenbecker
 * @version 1.1
 * @since 30.08.2021
 */

public class Einstellungen {
    private int schwierigkeitsstufe;
    private Boolean bonusstreak;
    private int spielfeldGroesse;
    private Boolean jokerkarten;


    /**
     * Default Konstruktor
     */
    public Einstellungen(){
        schwierigkeitsstufe = 0;
        bonusstreak = false;
        spielfeldGroesse = 6;
        jokerkarten = false;
    }

    /**
     * Konstruktor mit den einzelnen Attributen als Parameter
     * @param bonusstreak
     * @param spielfeldGroesse
     */
    public Einstellungen(Boolean bonusstreak, int spielfeldGroesse) {
        this.bonusstreak = bonusstreak;
        this.spielfeldGroesse = spielfeldGroesse;
    }

    /**
     * Konsturkor mit einem Einstellungsobjekt als Parameter.
     * @param einstellungen
     */
    public Einstellungen(Einstellungen einstellungen){
        bonusstreak = einstellungen.getBonusstreak();
        spielfeldGroesse = einstellungen.getSpielfeldGroesse();
    }


    //Getter & Setter

    /**
     * Gibt zurück, ob mit Bonusstreak gespielt werden soll
     * @return Boolean
     */
    public Boolean getBonusstreak() {
        return bonusstreak;
    }

    /**
     * Setzt, ob mit Bonusstreak gespielt werden soll
     * @param bonusstreak
     */
    public void setBonusstreak(Boolean bonusstreak) {
        this.bonusstreak = bonusstreak;
    }

    /**
     * Gibt die Spielfeldgrösse zurück
     * @return int
     */
    public int getSpielfeldGroesse() {
        return spielfeldGroesse;
    }

    /**
     * Setzt die Spielfeldgroesse
     * Kann nur 6x6, 8x8 oder 10x10 sein
     * @param spielfeldGroesse
     */
    public void setSpielfeldGroesse(int spielfeldGroesse) {
        if (spielfeldGroesse == 6 || spielfeldGroesse == 8 || spielfeldGroesse == 10){
            this.spielfeldGroesse = spielfeldGroesse;
        }
    }
}
