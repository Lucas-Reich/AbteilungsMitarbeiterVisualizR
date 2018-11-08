package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.GUI;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");


        SQLiteHelper.initializeDatabase();
        SQLiteRepository sqlite = new SQLiteRepository();

        Department department = new Department("Abteilung 1337");
        //sqlite.saveDepartment(department);

        //System.out.println(sqlite.getDepartment(3).getName());

        // Example, does not have to look like this
        GUI gui = new GUI(new SQLiteRepository());
        gui.show();
    }
}
