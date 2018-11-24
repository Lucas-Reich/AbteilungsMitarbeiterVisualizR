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
    private JButton speichernButton;
    private JTextArea bearbeitungTextArea;
    private JComboBox abteilungSelect;

    String returnedList;

    JFrame frame = new JFrame("visualizR");

    JList listenstuff;



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
    /*
    public void setData(List<Department> departments) {
        //String test1 = "test1";
        //String test2 = "test2";
        //Object[] test = new Object[2];
        //test[0] = test1;
        //test[1] = test2;
        //JFrame frame = new JFrame("Abteilungsliste");
        abteilungsliste = new JList(departments.toArray()); //data has type Object[]
        //abteilungsliste.setVisibleRowCount();
        //abteilungsliste.getSelectedIndex();
        abteilungsliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        abteilungsliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        //JScrollPane abteilungScrollPane = new JScrollPane(abteilungsliste);
        //frame.add(abteilungScrollPane);

        //frame.setSize(250, 200);
        //frame.setVisible(true);

    }
    */


    public StringBuilder setDataNew(List<Department> departments) {

        abteilungsliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        abteilungsliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        abteilungsliste.setVisibleRowCount(5);
        abteilungsliste.setListData(departments.toArray());

        int size = abteilungsliste.getModel().getSize();
        StringBuilder allDepartments = new StringBuilder();
        for(int i = 0; i < size; i++) {
            allDepartments.append("\n").append(abteilungsliste.getModel().getElementAt(i));
        }

        System.out.println(allDepartments);

        return allDepartments;

    }



    public void guiShow() {
        frame.setContentPane(new VisualizR_GUI().abteilungsmitarbeitervisualizr);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
