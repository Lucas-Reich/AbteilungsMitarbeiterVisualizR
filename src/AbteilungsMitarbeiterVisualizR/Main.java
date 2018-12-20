package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept2;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;
import AbteilungsMitarbeiterVisualizR.UI.TUI.TUI;

public class Main {
    public static void main(String[] args) {
        TUI tui = TUI.init(Fachkonzept2.init(XMLHelper.init()));
        tui.show();
    }
}

