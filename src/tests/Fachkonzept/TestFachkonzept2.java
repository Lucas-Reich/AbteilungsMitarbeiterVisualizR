package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept2;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.util.List;

public class TestFachkonzept2 extends AbstractFachkonzeptTest {
    @Override
    protected IFachkonzept createFachkonzept(IPersistence persistence) {
        return new Fachkonzept2(
                persistence
        );
    }

    @Override
    protected List<Department> getDepartmentsSortedById() {
        // TODO implement me
        return null;
    }

    @Override
    protected List<Department> getDepartmentsSortedByName() {
        // TODO implement me
        return null;
    }

    @Override
    protected List<Employee> getEmployeesSortedById() {
        // TODO implement me
        return null;
    }

    @Override
    protected List<Employee> getEmployeesSortedByName() {
        // TODO implement me
        return null;
    }
}
