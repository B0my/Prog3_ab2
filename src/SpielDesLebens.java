import logik.Simulator;
import ui.Quadrat;
import util.Interaktionsbrett;

public class SpielDesLebens {
    public static void main(String[] args) {
        Interaktionsbrett ib = new Interaktionsbrett();
        ib.neuesRechteck(5, 5, 2, 3);
        ib.starteUhr();
        ib.neuesRechteck(new Quadrat(), "Quadrat 1", 1, 1, 10,10);
    }

}
