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
        return Fachkonzept1.init(persistence);
    }

    @Override
    protected List<Department> getDepartmentsSortedById() {
        return new ArrayList<Department>() {{
            add(Department.init(1L, "Abteilung D"));
            add(Department.init(2L, "Abteilung A"));
            add(Department.init(3L, "Abteilung E"));
            add(Department.init(4L, "Abteilung B"));
            add(Department.init(5L, "Abteilung C"));
            add(Department.init(6L, "Abteilung C"));
        }};
    }

    @Override
    protected List<Department> getDepartmentsSortedByName() {
        return new ArrayList<Department>() {{
            add(Department.init(2L, "Abteilung A"));
            add(Department.init(4L, "Abteilung B"));
            add(Department.init(5L, "Abteilung C"));
            add(Department.init(6L, "Abteilung C"));
            add(Department.init(1L, "Abteilung D"));
            add(Department.init(3L, "Abteilung E"));
        }};
    }

    @Override
    protected List<Employee> getEmployeesSortedById() {
        return new ArrayList<Employee>() {{
            add(Employee.init(23L, "Lucas Reich"));
            add(Employee.init(234L, "Christian Schulz"));
            add(Employee.init(34578L, "Viktor Yezhov"));
        }};
    }

    @Override
    protected List<Employee> getEmployeesSortedByName() {
        return new ArrayList<Employee>() {{
            add(Employee.init(234L, "Christian Schulz"));
            add(Employee.init(23L, "Lucas Reich"));
            add(Employee.init(34578L, "Viktor Yezhov"));
        }};
    }
}
