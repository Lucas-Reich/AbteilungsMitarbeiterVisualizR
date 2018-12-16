package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteConnectionHandler;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started");

        SQLiteRepository sqliteRepo = new SQLiteRepository(new SQLiteConnectionHandler());
        sqliteRepo.saveDepartment(new Department("Test"));
        Employee test = sqliteRepo.saveEmployee(new Employee("Test Person"), 1);

        Log.console("Program finished");
    }
}
