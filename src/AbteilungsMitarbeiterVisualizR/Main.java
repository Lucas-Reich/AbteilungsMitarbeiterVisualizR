package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteDatabaseHandler;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.GUI;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");

        SQLiteHelper.initializeDatabase(new SQLiteDatabaseHandler());

        SQLiteRepository repo = new SQLiteRepository(new SQLiteDatabaseHandler());
        repo.saveDepartment(new Department("Abteilung 1"));

        Department dep = repo.getDepartment(1);

        // Example, does not have to look like this
        GUI gui = new GUI(repo);
        gui.show();

    }
}
