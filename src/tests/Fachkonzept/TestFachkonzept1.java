package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
            add(new Department(1, "IT"));
            add(new Department(2, "Marketing"));
            add(new Department(3, "Business"));
        }};
    }

    @Override
    protected List<Department> getDepartmentsSortedByName() {
        return new ArrayList<Department>() {{
            add(new Department(3, "Business"));
            add(new Department(1, "IT"));
            add(new Department(2, "Marketing"));
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
