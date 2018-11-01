package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public abstract class AbstractFachkonzeptTest {
    private IFachkonzept fachkonzept;

    @After
    public void tearDown() {
        fachkonzept = null;
    }

    protected abstract IFachkonzept createFachkonzept(IPersistence persistence);

    @Test
    public void testGetProjectName() {
        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        assertEquals(
                "Without e and with a capital R!",
                "AbteilungsMitarbeiterVisualizR",
                fachkonzept.getProjectName()
        );
    }

    @Test
    public void testGetDepartment() {
        Department expectedDepartment = new Department(776, "IT");

        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getDepartment(anyLong())).thenReturn(expectedDepartment);

        fachkonzept = createFachkonzept(persistence);

        assertEquals(
                "Fachkonzept returned wrong Department",
                expectedDepartment,
                fachkonzept.getDepartment(expectedDepartment.getId())
        );
    }

    protected abstract List<Department> getDepartmentsSortedById();

    protected abstract List<Department> getDepartmentsSortedByName();

    @Test
    public void testGetDepartments() {
        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getAllDepartments()).thenReturn(getDepartmentsSortedById());

        fachkonzept = createFachkonzept(persistence);

        List<Department> expectedDepartments = getDepartmentsSortedByName();
        List<Department> actualDepartments = fachkonzept.getDepartments();

        for (int i = 0; i < actualDepartments.size(); i++) {
            assertEquals(expectedDepartments.get(i), actualDepartments.get(i));
        }
    }

    @Test
    public void testSaveDepartment() {
        Department department = mock(Department.class);
        when(department.getName()).thenReturn("Production");

        IPersistence persistence = mock(IPersistence.class);
        when(persistence.saveDepartment(any(Department.class))).thenReturn(department);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.saveDepartment(department.getName());

        verify(persistence, times(1))
                .saveDepartment(any());
    }

    @Test
    public void testDeleteDepartment() {
        Department department = mock(Department.class);
        when(department.getId()).thenReturn(1L);

        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.deleteDepartment(department.getId());

        verify(persistence, times(1))
                .deleteDepartment(anyLong());
    }

    @Test
    public void testUpdateDepartmentName() {
        Department department = mock(Department.class);
        when(department.getId()).thenReturn(1L);

        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.updateDepartmentName("New Department Name", department.getId());

        verify(persistence, times(1))
                .updateDepartment(any(Department.class));
    }

    @Test
    public void testGetEmployee() {
        Employee expectedEmployee = new Employee(94, "Lucas Reich");

        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getEmployee(anyLong())).thenReturn(expectedEmployee);

        fachkonzept = createFachkonzept(persistence);

        assertEquals(
                "Wrong employee returned",
                expectedEmployee,
                fachkonzept.getEmployee(expectedEmployee.getId())
        );
    }

    protected abstract List<Employee> getEmployeesSortedById();

    protected abstract List<Employee> getEmployeesSortedByName();

    @Test
    public void testGetEmployees() {
        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getAllEmployees()).thenReturn(getEmployeesSortedById());

        fachkonzept = createFachkonzept(persistence);

        List<Employee> expectedEmployees = getEmployeesSortedByName();
        List<Employee> actualEmployees = fachkonzept.getEmployees(1);

        for (int i = 0; i < actualEmployees.size(); i++) {
            assertEquals(expectedEmployees.get(i), actualEmployees.get(i));
        }
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = mock(Employee.class);
        when(employee.getName()).thenReturn("Lucas Reich");

        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.saveNewEmployee(employee.getName(), 1L);

        verify(persistence, times(1))
                .saveEmployee(any(Employee.class), anyLong());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(1L);

        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.deleteEmployee(employee.getId());

        verify(persistence, times(1))
                .deleteEmployee(anyLong());
    }

    @Test
    public void testUpdateEmployeeName() {
        Employee employee = mock(Employee.class);
        when(employee.getName()).thenReturn("Lucas Reich");
        when(employee.getId()).thenReturn(1L);

        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.updateEmployeeName(employee.getName(), employee.getId());

        verify(persistence, times(1))
                .updateEmployee(any(Employee.class));
    }

    @Test
    public void testReassingEmployeedDepartment() {
        IPersistence persistence = mock(IPersistence.class);

        fachkonzept = createFachkonzept(persistence);

        fachkonzept.reassingEmployeeDepartment(1L, 1L);

        verify(persistence, times(1))
                .deleteEmployee(anyLong());

        verify(persistence, times(1))
                .saveEmployee(any(Employee.class), anyLong());
    }
}
