import logik.Simulator;
import ui.Quadrat;
import ui.Steuerung;
import util.Interaktionsbrett;


public class SpielDesLebens {
    public static void main(String[] args) {
        Steuerung mainApp = new Steuerung(new Simulator());
        mainApp.startDesSpiels();
        System.exit(0);
    }

}
