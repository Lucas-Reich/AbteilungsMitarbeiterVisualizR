package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.TUI;

public class Main {
    public static void main(String[] args) {
        TUI tui = new TUI(SQLiteRepository.init());
        tui.show();
    }
}
