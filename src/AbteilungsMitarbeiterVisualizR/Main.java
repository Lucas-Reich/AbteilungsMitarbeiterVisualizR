package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteConnectionHandler;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        SQLiteHelper.initializeDatabase(new SQLiteConnectionHandler());

        SQLiteRepository sqliteRepo = new SQLiteRepository(new SQLiteConnectionHandler());
        Employee test = sqliteRepo.saveEmployee(new Employee("Test Person"), 1);

        Log.console("Stop for a moment");
    }
}
