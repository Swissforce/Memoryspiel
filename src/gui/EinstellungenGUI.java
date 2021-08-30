package gui;

import model.Einstellungen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modales GUI, das die Änderungsmöglichkeiten der Einstellungen des Spiels beinhaltet
 *
 * @author Martin Düppenbecker
 * @version 1.1
 * @since 30.08.2021
 */

public class EinstellungenGUI extends JDialog {
    private Einstellungen einstellungen;
    private JDialog frame;
    private JButton hauptmenuButton;
    private JPanel einstellungenPanel;
    private JPanel grossesEinstellungenPanel;

    private JSlider spielfeldgroesseSlider;
    private JLabel spielfeldgroesseLabel;

    private JCheckBox bonusstreaksCheckBox;
    private JLabel bonusstreaksLabel;



    /**
     * Konstruktor
     * @param parent
     * @param einstellungen
     */
    public EinstellungenGUI(JFrame parent, Einstellungen einstellungen){
        frame = new JDialog(parent,"Einstellungen", true);
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.einstellungen = einstellungen;

        initComponent();

        addListeners();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Gibt die geähnderten Einstellungen zurück
     * @return Einstellungen-Objekt
     */
    public Einstellungen getEinstellungen(){
        return einstellungen;
    }

    /**
     * initialisiert das GUI
     */
    private void initComponent(){
        einstellungenPanel = new JPanel(new GridLayout(4,2));
        grossesEinstellungenPanel = new JPanel(new BorderLayout());

        spielfeldgroesseLabel = new JLabel("Spielfeldgrösse");
        spielfeldgroesseSlider = new JSlider(JSlider.HORIZONTAL,6,10,6);
        spielfeldgroesseSlider.setMinorTickSpacing(2);
        spielfeldgroesseSlider.setMajorTickSpacing(2);
        spielfeldgroesseSlider.setSnapToTicks(true);
        spielfeldgroesseSlider.setPaintTicks(true);
        spielfeldgroesseSlider.setPaintLabels(true);

        einstellungenPanel.add(spielfeldgroesseLabel);
        einstellungenPanel.add(spielfeldgroesseSlider);

        bonusstreaksLabel = new JLabel("Bonusstreaks");
        bonusstreaksCheckBox = new JCheckBox();


        if (einstellungen != null){
            bonusstreaksCheckBox.setSelected(einstellungen.getBonusstreak());
            spielfeldgroesseSlider.setValue(einstellungen.getSpielfeldGroesse());
        }

        einstellungenPanel.add(bonusstreaksLabel);
        einstellungenPanel.add(bonusstreaksCheckBox);


        hauptmenuButton = new JButton("Hauptmenu");


        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(einstellungenPanel, BorderLayout.CENTER);
        frame.getContentPane().add(hauptmenuButton, BorderLayout.SOUTH);
    }

    /**
     * Fügt die Listeners den Buttons hinzu
     */
    private void addListeners(){
        hauptmenuButton.addActionListener(new HauptmenuButton());
    }


    //Button-Listener

    class HauptmenuButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            einstellungen.setBonusstreak(bonusstreaksCheckBox.isSelected());
            einstellungen.setSpielfeldGroesse(spielfeldgroesseSlider.getValue());

            frame.dispose();
        }
    }
}
