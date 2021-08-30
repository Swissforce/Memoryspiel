package gui;

import spielcontroller.Spiellogik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI, das die Optionen des Programms beinhaltet
 *
 * @author Martin Düppenbecker
 * @verison 1.1
 * @since 30.08.2021
 */
public class HauptseiteGUI extends JFrame {

    private HauptseiteGUI instance;
    private Spiellogik spiellogik;
    private JButton buttonSpielen;
    private JButton buttonEinstellungen;
    private JButton buttonVerlassen;

    JPanel buttonPanel = new JPanel();
    JLabel titel = new JLabel("", SwingConstants.CENTER);

    /**
     * Konstruktor
     */
    public HauptseiteGUI(Spiellogik spiellogik) {
        this.setTitle("Hauptseite");

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //GUI wird nicht geschlossen bei Exit. (x)

        this.instance = this;
        this.spiellogik = spiellogik;

        Font buttonFont = new Font("Monaco",Font.PLAIN,20);

        buttonSpielen = new JButton("Spielen");
        buttonEinstellungen = new JButton("Einstellungen");
        buttonVerlassen = new JButton("Verlassen");

        buttonSpielen.setFont(buttonFont);
        buttonEinstellungen.setFont(buttonFont);
        buttonVerlassen.setFont(buttonFont);

        titel.setText("Memory");
        titel.setFont(new Font("Monaco", Font.ITALIC, 80));

        buttonSpielen.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEinstellungen.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonVerlassen.setAlignmentX(Component.CENTER_ALIGNMENT);
        titel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(titel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        buttonPanel.add(buttonSpielen);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(buttonEinstellungen);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(buttonVerlassen);


        this.getContentPane().add(buttonPanel);
        this.getContentPane().add(titel, BorderLayout.NORTH);


        addListeners();


        setResizable(false);
        setVisible(true);
        pack();
        setSize(800, 600);

    }


    /**
     * Fügt die Listeners den Buttons hinzu
     */
    private void addListeners(){
        buttonSpielen.addActionListener(new SpielenButton());
        buttonEinstellungen.addActionListener(new EinstellungenButton());
        buttonVerlassen.addActionListener(new VerlassenButton());
    }


    //Button-Listener

    class SpielenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            spiellogik.startGame();
        }
    }

    class EinstellungenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            spiellogik.startEinstellungen(instance);
        }
    }

    class VerlassenButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
