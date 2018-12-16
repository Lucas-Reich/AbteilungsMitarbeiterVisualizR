package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started");

        IPersistence sqliteRepo = SQLiteRepository.init();

        sqliteRepo.saveDepartment(new Department("Test"));
        Employee test = sqliteRepo.saveEmployee(new Employee("Test Person"), 1);

        Log.console("Program finished");
    }
}
