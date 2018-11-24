package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JList;

public class VisualizR_GUI {

    JFrame frame = new JFrame("visualizR");

    private JButton abteilungNeuButton;
    private JButton abteilungBearbeitenButton;
    private JButton abteilungLoeschenButton;
    private JPanel abteilungsmitarbeitervisualizr;
    private DefaultListModel abteilungslisteModel;
    private JList abteilungsliste;
    private JList mitarbeiterliste;
    private JButton mitarbeiterNeuButton;
    private JButton mitarbeiterBearbeitenButton;
    private JButton mitarbeiterLoeschenButton;
    private JButton speichernButton;
    private JTextArea bearbeitungTextArea;
    private JComboBox abteilungSelect;

    public VisualizR_GUI() {

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

    public void setData(List<Department> departments) {
        abteilungslisteModel = new DefaultListModel();
        abteilungslisteModel.addElement(departments);

        abteilungsliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        abteilungsliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        abteilungsliste.setVisibleRowCount(5);
        abteilungsliste.setListData(departments.toArray());
        abteilungsliste.setModel(abteilungslisteModel);

        System.out.println(abteilungslisteModel);
        System.out.println(abteilungsliste.getModel());

    }

    public void guiShow() {
        frame.setContentPane(new VisualizR_GUI().abteilungsmitarbeitervisualizr);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
