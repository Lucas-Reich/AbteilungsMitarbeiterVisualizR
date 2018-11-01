package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class TestFachkonzept1 extends AbstractFachkonzeptTest {

    @Override
    protected IFachkonzept createFachkonzept(IPersistence persistence) {
        return new Fachkonzept1(
                persistence
        );
    }

    @Override
    protected List<Department> getDepartmentsSortedById() {
        return new ArrayList<Department>() {{
            add(new Department(1, "Abteilung D"));
            add(new Department(2, "Abteilung A"));
            add(new Department(3, "Abteilung E"));
            add(new Department(4, "Abteilung B"));
            add(new Department(5, "Abteilung C"));
            add(new Department(6, "Abteilung C"));
        }};
    }

    @Override
    protected List<Department> getDepartmentsSortedByName() {
        return new ArrayList<Department>() {{
            add(new Department(2, "Abteilung A"));
            add(new Department(4, "Abteilung B"));
            add(new Department(5, "Abteilung C"));
            add(new Department(6, "Abteilung C"));
            add(new Department(1, "Abteilung D"));
            add(new Department(3, "Abteilung E"));
        }};
    }

    @Override
    protected List<Employee> getEmployeesSortedById() {
        return new ArrayList<Employee>() {{
            add(new Employee(23, "Lucas Reich"));
            add(new Employee(234, "Christian Schulz"));
            add(new Employee(34578, "Viktor Yezhov"));
        }};
    }

    @Override
    protected List<Employee> getEmployeesSortedByName() {
        return new ArrayList<Employee>() {{
            add(new Employee(234, "Christian Schulz"));
            add(new Employee(23, "Lucas Reich"));
            add(new Employee(34578, "Viktor Yezhov"));
        }};
    }
}
