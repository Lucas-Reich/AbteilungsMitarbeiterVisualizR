package AbteilungsMitarbeiterVisualizR.Fachkonzept;


import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

class FachkonzeptTest {

    @Test
    void getProjectName(Fachkonzept fk) {

        Assert.assertEquals("AbteilungsMitarbeiterVisualizR", fk.getProjectName());
    }

    @Test
    void getDepartment() {
//        Fachkonzept fachkonzept = new Fachkonzept1(persistence);
    }

    @Test
    void getDepartments() {
    }

    @Test
    void saveDepartment() {
    }

    @Test
    void deleteDepartment() {
    }

    @Test
    void updateDepartmentName() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void getEmployees() {
    }

    @Test
    void saveNewEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployeeName() {
    }

    @Test
    void reassingEmployeeDepartment() {
    }
}