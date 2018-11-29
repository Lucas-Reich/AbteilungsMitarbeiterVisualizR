package tests.Fachkonzept.Persistence;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteDatabaseHandler;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractPersistenceTest {
    private IPersistence mPersistence;

    public AbstractPersistenceTest() {
        mPersistence = createPersistence();
    }

    abstract IPersistence createPersistence();

    @Before
    public void setUp() {
        SQLiteHelper.initializeDatabase(new SQLiteDatabaseHandler());
    }

    @After
    public void teardown() {
        File sqliteFile = new File(SQLiteHelper.getDatabaseDir() + "/" + SQLiteHelper.DATABASE_NAME);
        sqliteFile.delete();
    }

    @Test
    public void testSaveDepartment() {
        Department expectedDepartment = createSimpleDepartment(1);
        Department actualDepartment = mPersistence.saveDepartment(expectedDepartment);

        assertSame(expectedDepartment, actualDepartment);
    }

    private Department createSimpleDepartment(long departmentId) {
        return new Department(
                departmentId,
                "Abteilung"
        );
    }

    private Employee createSimpleEmployee(long employeeId) {
        return new Employee(
                employeeId,
                "Hans Peter"
        );
    }
}
