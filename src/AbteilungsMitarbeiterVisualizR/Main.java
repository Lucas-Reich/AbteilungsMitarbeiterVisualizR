package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.GUI;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");

        // Example, does not have to look like this
        GUI gui = new GUI(new XMLHelper());
        gui.show();


        SQLiteHelper.initializeDatabase();
        SQLiteRepository sqlite = new SQLiteRepository();
        Employee employee1 = new Employee("Viktor Yezhov");
        Employee employee2 = new Employee("Christian Schulz");
        Employee employee3 = new Employee("Lucas Reich");
        Department department = sqlite.saveDepartment(new Department("Abteilung 1"));
        sqlite.saveEmployee(employee1, department.getId());
        sqlite.saveEmployee(employee2, department.getId());
        sqlite.saveEmployee(employee3, department.getId());
        sqlite.saveDepartment(new Department("Abteilung 2"));
        sqlite.saveDepartment(new Department("Abteilung 3"));
        sqlite.saveDepartment(new Department("Abteilung 4"));
    }
}
