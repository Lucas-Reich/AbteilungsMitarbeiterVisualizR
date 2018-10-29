package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public abstract class AbstractFachkonzeptTest {
    protected IFachkonzept fachkonzept;

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

    @Test
    public void testGetDepartments() {
        List<Department> expectedDepartments = new ArrayList<>();
        expectedDepartments.add(new Department(1, "IT"));
        expectedDepartments.add(new Department(2, "Marketing"));
        expectedDepartments.add(new Department(3, "Business"));

        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getAllDepartments()).thenReturn(expectedDepartments);

        fachkonzept = createFachkonzept(persistence);

        assertEquals(
                "Wrong departments returned",
                expectedDepartments,
                fachkonzept.getDepartments()
        );
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

    @Test
    public void testGetEmployees() {
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(23, "Lucas Reich"));
        expectedEmployees.add(new Employee(34578, "Viktor Yezhov"));
        expectedEmployees.add(new Employee(234, "Christian Schulz"));

        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getAllEmployees()).thenReturn(expectedEmployees);

        fachkonzept = createFachkonzept(persistence);

        assertEquals(
                "Wrong employees returned",
                expectedEmployees,
                fachkonzept.getEmployees(1) // TODO which departmentId
        );
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
