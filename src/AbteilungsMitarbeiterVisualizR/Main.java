package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;
import AbteilungsMitarbeiterVisualizR.UI.TUI;

public class Main {
    public static void main(String[] args) {
        TUI tui = TUI.init(Fachkonzept1.init(SQLiteRepository.init()));
        tui.show();
    }
}
