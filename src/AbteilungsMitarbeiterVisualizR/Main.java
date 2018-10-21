package AbteilungsMitarbeiterVisualizR;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.GUI;
import AbteilungsMitarbeiterVisualizR.Persistence.XML.XMLHelper;

public class Main {
    // TODO refine me
    public static void main(String args[]) {
        System.out.println("Hello World!");
    }

    public static void test() {
        Fachkonzept fachkonzept = new GUI(new XMLHelper());
    }
}
