package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class FoobarGUI {

    private JList abteilungsliste;
    private JList mitarbeiterliste;
    private DefaultListModel abteilungsListModel;
    private DefaultListModel mitarbeiterListModel;

    private JPanel FoobarPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField textField1;

    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Foo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        frame.setPreferredSize(new Dimension(640, 480));
        frame.getContentPane().add(abteilungsliste);
        frame.getContentPane().add(mitarbeiterliste);
        frame.getContentPane().add(addButton);
        frame.getContentPane().add(deleteButton);
        frame.getContentPane().add(textField1);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //Abteilungsliste erstellen und füllen
    public void setDepartmentsData(List<Department> departments) {
        abteilungsListModel = new DefaultListModel();
        //departments als object adden:
        //abteilungsListModel.addElement(departments);
        //items aus departments einzeln adden:
        abteilungsListModel.addAll(departments);

        abteilungsliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        abteilungsliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        abteilungsliste = new JList(abteilungsListModel);

    }

    //Mitarbeiterliste erstellen und füllen
    public void setEmployeesData() {
        mitarbeiterListModel = new DefaultListModel();

        mitarbeiterliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mitarbeiterliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        mitarbeiterListModel.addElement("Christian Schulz");
        mitarbeiterListModel.addElement("Lucas Reich");
        mitarbeiterListModel.addElement("Viktor Yezhov");
        mitarbeiterliste = new JList(mitarbeiterListModel);
    }

    //Listener setzen
    public void setListener() {
        ListSelectionListener abteilungslisteListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textField1.setText(String.valueOf(abteilungsliste.getSelectedValue()));
            }
        };

        ListSelectionListener mitarbeiterlisteListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textField1.setText(String.valueOf(mitarbeiterliste.getSelectedValue()));
            }
        };

        abteilungsliste.addListSelectionListener(abteilungslisteListener);
        mitarbeiterliste.addListSelectionListener(mitarbeiterlisteListener);
    }
}
