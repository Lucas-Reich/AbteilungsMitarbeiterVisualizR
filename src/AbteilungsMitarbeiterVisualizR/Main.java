package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        XMLHelper repo = new XMLHelper();

        Department dep = repo.saveDepartment(new Department("Schule"));

        Employee chris = repo.saveEmployee(new Employee("Christian"), dep.getId());
        Employee luc = repo.saveEmployee(new Employee("Lucas"), dep.getId());
        Employee vik = repo.saveEmployee(new Employee("Viktor"), dep.getId());

        vik.setName("Nicht Viktor");
        repo.updateEmployee(vik);

        Log.console(repo.getEmployee(vik.getId()).getName());
    }
}
