package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteHelper;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.GUI;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");

        // Example, does not have to look like this
        GUI gui = new GUI(new XMLHelper());
        gui.show();


        SQLiteHelper.initializeDatabase();
        SQLiteRepository sqlite = new SQLiteRepository();

        System.out.println(sqlite.getDepartment(3).getName());
    }
}
