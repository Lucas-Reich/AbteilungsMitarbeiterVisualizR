package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.UI.GUI;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");

        GUI gui = new GUI(new XMLHelper());
        gui.show();
    }
}
