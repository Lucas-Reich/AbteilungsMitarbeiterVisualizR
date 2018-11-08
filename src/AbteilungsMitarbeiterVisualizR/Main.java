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

        ///Department department2 = new Department("Abteilung 1337");
        //Department department3 = new Department("Abteilung 13");
        //Department department4 = new Department("Abteilung 25");
        //sqlite.saveDepartment(department2);
        //sqlite.saveDepartment(department3);
        //sqlite.saveDepartment(department4);

        //System.out.println(sqlite.getDepartment(3).getName());

        // Example, does not have to look like this
        GUI gui = new GUI(new SQLiteRepository());
        gui.show();
    }
}
