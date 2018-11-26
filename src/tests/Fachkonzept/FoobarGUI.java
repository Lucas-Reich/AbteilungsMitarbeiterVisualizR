package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FoobarGUI {

    private JLabel abteilungLabel;
    private JList abteilungsliste;
    private JLabel mitarbeiterLabel;
    private JList mitarbeiterliste;
    private DefaultListModel abteilungsListModel;
    private DefaultListModel mitarbeiterListModel;

    private JPanel FoobarPanel;
    private JButton abteilungAddButton;
    private JButton abteilungDeleteButton;
    private JButton abteilungChangeButton;
    private JButton mitarbeiterAddButton;
    private JButton mitarbeiterDeleteButton;
    private JButton mitarbeiterChangeButton;
    private JTextField textField1;
    private JTextField abteilungIdTextfield;


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
    public void setEmployeesData(List<Employee> employees) {
        mitarbeiterListModel = new DefaultListModel();

        mitarbeiterListModel.addAll(employees);

        mitarbeiterliste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mitarbeiterliste.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        mitarbeiterliste = new JList(mitarbeiterListModel);
    }

    //Listener setzen
    public void setListener() {
        abteilungsliste.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textField1.setText(String.valueOf(abteilungsliste.getSelectedValue()));
            }
        });
        mitarbeiterliste.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textField1.setText(String.valueOf(mitarbeiterliste.getSelectedValue()));
            }
        });
        abteilungDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abteilungsListModel.remove(abteilungsliste.getSelectedIndex());
            }
        });

        mitarbeiterDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mitarbeiterListModel.remove(mitarbeiterliste.getSelectedIndex());
            }
        });

        abteilungAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abteilungsListModel.addElement(String.valueOf(textField1.getText()));
            }
        });

        mitarbeiterAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mitarbeiterListModel.addElement(String.valueOf(textField1.getText()));
            }
        });

        mitarbeiterliste.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                abteilungIdTextfield.setText(String.valueOf(mitarbeiterliste.getSelectedIndices()));
            }
        });
    }

    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("WSDB AbteilungsmitarbeitervisualizR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(640, 480));


        JPanel panel = new JPanel();
        panel.setLayout(null);

        abteilungLabel = new JLabel("Abteilung");
        mitarbeiterLabel = new JLabel("Mitarbeiter");
        abteilungChangeButton = new JButton("Bearbeiten");
        abteilungIdTextfield = new JTextField();

        panel.add(abteilungLabel);
        panel.add(abteilungsliste);
        panel.add(mitarbeiterLabel);
        panel.add(mitarbeiterliste);
        panel.add(abteilungAddButton);
        panel.add(abteilungDeleteButton);
        panel.add(abteilungChangeButton);
        panel.add(mitarbeiterAddButton);
        panel.add(mitarbeiterDeleteButton);
        panel.add(mitarbeiterChangeButton);
        panel.add(textField1);
        panel.add(abteilungIdTextfield);
        abteilungLabel.setBounds(5, 15, 300, 30);
        abteilungsliste.setBounds(5, 45, 300, 180);
        mitarbeiterLabel.setBounds(320, 15, 300, 30);
        mitarbeiterliste.setBounds(320, 45, 300, 180);
        abteilungAddButton.setBounds(5, 235, 60, 25);
        abteilungDeleteButton.setBounds(80, 235, 90, 25);
        abteilungChangeButton.setBounds(185, 235, 120, 25);
        mitarbeiterAddButton.setBounds(320, 235, 60, 25);
        mitarbeiterDeleteButton.setBounds(395, 235, 90, 25);
        mitarbeiterChangeButton.setBounds(500, 235, 120, 25);
        textField1.setBounds(5, 280, 300, 100);
        abteilungIdTextfield.setBounds(320, 280, 85, 25);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.add(panel);
    }
}
