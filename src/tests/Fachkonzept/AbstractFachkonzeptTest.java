package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public abstract class AbstractFachkonzeptTest {
    private IFachkonzept mFachkonzept;
    private IPersistence mPersistence;

    @Before
    public void setUp() {
        mPersistence = mock(IPersistence.class);
        mFachkonzept = createFachkonzept(mPersistence);
    }

    protected abstract IFachkonzept createFachkonzept(IPersistence persistence);

    @After
    public void tearDown() {
        mPersistence = null;
        mFachkonzept = null;
    }

    @Test
    public void testGetProjectName() {
        assertEquals(
                (Object) "Without e and with a capital R!",
                "AbteilungsMitarbeiterVisualizR",
                mFachkonzept.getProjectName()
        );
    }

    @Test
    public void testGetDepartment() {
        Department expectedDepartment = new Department(776, "IT");

        when(mPersistence.getDepartment(anyLong())).thenReturn(expectedDepartment);

        assertEquals(
                "Fachkonzept returned wrong Department",
                expectedDepartment,
                mFachkonzept.getDepartment(expectedDepartment.getId())
        );
    }

    protected abstract List<Department> getDepartmentsSortedById();

    protected abstract List<Department> getDepartmentsSortedByName();

    @Test
    public void testGetDepartments() {
        when(mPersistence.getAllDepartments()).thenReturn(getDepartmentsSortedById());

        List<Department> expectedDepartments = getDepartmentsSortedByName();
        List<Department> actualDepartments = mFachkonzept.getDepartments();

        for (int i = 0; i < actualDepartments.size(); i++) {
            assertEquals(
                    String.format("Department %s != %s.", expectedDepartments.get(i).getName(), actualDepartments.get(i).getName()),
                    expectedDepartments.get(i),
                    actualDepartments.get(i)
            );
        }
    }

    @Test
    public void testSaveDepartment() {
        Department department = mock(Department.class);
        when(department.getName()).thenReturn("Production");
        when(mPersistence.saveDepartment(any(Department.class))).thenReturn(department);

        mFachkonzept.saveDepartment(department.getName());

        verify(mPersistence, times(1))
                .saveDepartment(any());
    }

    @Test
    public void testDeleteDepartment() {
        Department department = mock(Department.class);
        when(department.getId()).thenReturn(1L);

        mFachkonzept.deleteDepartment(department.getId());

        verify(mPersistence, times(1))
                .deleteDepartment(anyLong());
    }

    @Test
    public void testUpdateDepartmentName() {
        Department department = mock(Department.class);
        when(department.getId()).thenReturn(1L);

        mFachkonzept.updateDepartmentName("New Department Name", department.getId());

        verify(mPersistence, times(1))
                .updateDepartment(any(Department.class));
    }

    @Test
    public void testGetEmployee() {
        Employee expectedEmployee = new Employee(94, "Lucas Reich");

        when(mPersistence.getEmployee(anyLong())).thenReturn(expectedEmployee);

        assertEquals(
                "Wrong employee returned",
                expectedEmployee,
                mFachkonzept.getEmployee(expectedEmployee.getId())
        );
    }

    protected abstract List<Employee> getEmployeesSortedById();

    protected abstract List<Employee> getEmployeesSortedByName();

    @Test
    public void testGetEmployees() {
        IPersistence persistence = mock(IPersistence.class);
        when(persistence.getEmployees()).thenReturn(getEmployeesSortedById());

        List<Employee> expectedEmployees = getEmployeesSortedByName();
        List<Employee> actualEmployees = mFachkonzept.getEmployees(1);

        for (int i = 0; i < actualEmployees.size(); i++) {
            assertEquals(
                    String.format("Employee %s != %s.", expectedEmployees.get(i).getName(), actualEmployees.get(i).getName()),
                    expectedEmployees.get(i),
                    actualEmployees.get(i)
            );
        }
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = mock(Employee.class);
        when(employee.getName()).thenReturn("Lucas Reich");

        mFachkonzept.saveNewEmployee(employee.getName(), 1L);

        verify(mPersistence, times(1))
                .saveEmployee(any(Employee.class), anyLong());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = mock(Employee.class);
        when(employee.getId()).thenReturn(1L);

        mFachkonzept.deleteEmployee(employee.getId());

        verify(mPersistence, times(1))
                .deleteEmployee(anyLong());
    }

    @Test
    public void testUpdateEmployeeName() {
        Employee employee = mock(Employee.class);
        when(employee.getName()).thenReturn("Lucas Reich");
        when(employee.getId()).thenReturn(1L);

        mFachkonzept.updateEmployeeName(employee.getName(), employee.getId());

        verify(mPersistence, times(1))
                .updateEmployee(any(Employee.class));
    }

    @Test
    public void testReassingEmployeedDepartment() {
        mFachkonzept.reassingEmployeeDepartment(1L, 1L);

        verify(mPersistence, times(1))
                .deleteEmployee(anyLong());

        verify(mPersistence, times(1))
                .saveEmployee(any(Employee.class), anyLong());
    }
}
