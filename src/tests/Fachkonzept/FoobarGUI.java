package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Flow;

public class FoobarGUI {

    private JList list;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;

    private JPanel FoobarPanel;
    private JList list1;


    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Foo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(640, 480));
        frame.getContentPane().add(list);
        frame.getContentPane().add(list1);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void setDepartmentsData(List<Department> departments) {
        listModel = new DefaultListModel();
        listModel.addElement(departments);

        System.out.println(listModel.size());

        list = new JList(listModel);

        mitarbeiterData();
    }

    public void mitarbeiterData() {
        listModel2 = new DefaultListModel();
        listModel2.addElement("ben");
        list1 = new JList(listModel2);
    }
}
