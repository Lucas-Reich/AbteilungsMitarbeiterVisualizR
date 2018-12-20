package AbteilungsMitarbeiterVisualizR.UI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class visualizR_GUI {
    private JButton abteilungNeuButton;
    private JButton abteilungBearbeitenButton;
    private JButton abteilungLoeschenButton;
    private JPanel abteilungsmitarbeitervisualizr;
    private JList abteilungsliste;
    private JList mitarbeiterliste;
    private JButton mitarbeiterNeuButton;
    private JButton mitarbeiterBearbeitenButton;
    private JButton mitarbeiterLoeschenButton;

    public visualizR_GUI() {

        abteilungNeuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Neue Abteilung angelegt!");
            }
        });
        abteilungBearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Abteilung bearbeitet!");
            }
        });
        abteilungLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Abteilung gelöscht!");
            }
        });
        mitarbeiterNeuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Neuen Mitarbeiter angelegt!");
            }
        });
        mitarbeiterBearbeitenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Mitarbeiter bearbeitet!");
            }
        });
        mitarbeiterLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Mitarbeiter gelöscht!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame ("App");
        frame.setContentPane(new visualizR_GUI().abteilungsmitarbeitervisualizr);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
