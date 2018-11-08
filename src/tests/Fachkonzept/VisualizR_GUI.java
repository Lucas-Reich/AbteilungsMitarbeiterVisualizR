package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisualizR_GUI {
    private JButton abteilungNeuButton;
    private JButton abteilungBearbeitenButton;
    private JButton abteilungLoeschenButton;
    private JPanel abteilungsmitarbeitervisualizr;
    private JList abteilungsliste;
    private JList mitarbeiterliste;
    private JButton mitarbeiterNeuButton;
    private JButton mitarbeiterBearbeitenButton;
    private JButton mitarbeiterLoeschenButton;
    private JTextArea bearbeitungTextArea;
    private JComboBox abteilungSelect;
    private JPanel listPanel;
    JFrame frame;

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

        frame = new JFrame("App");



    }

    public void setData(List<Department> departments) {
        //String test1 = "test1";
        //String test2 = "test2";
        //Object[] test = new Object[2];
        //test[0] = test1;
        //test[1] = test2;
        //abteilungsliste = new JList(test); //data has type Object[]
        //abteilungsliste.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //abteilungsliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //abteilungsliste.setVisibleRowCount(0);
        //JScrollPane testpane = new JScrollPane(abteilungsliste);
        //testpane.setPreferredSize(new Dimension(250, 50));
        JList deparments = new JList(departments.toArray());
        JPanel panel = new JPanel();
        listPanel.add(deparments);
    }

    public void guiShow() {
        frame.setContentPane(new VisualizR_GUI().abteilungsmitarbeitervisualizr);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
