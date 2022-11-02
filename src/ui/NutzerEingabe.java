package ui;

import util.EinUndAusgabe;

public class NutzerEingabe {
    private EinUndAusgabe io;
    private final int MINDESTZELLENANZAHL = 3;

    /**
     * @param io
     */
    public NutzerEingabe(EinUndAusgabe io) {
        this.io = new EinUndAusgabe();
    }

    /**
     * @return
     */
    public int anzahlZellenDesSpielfeds() {
        int input;
        do {
            io.ausgeben("Anzahl der Zellen (mindestens " + MINDESTZELLENANZAHL + "): ");
            input = io.leseInteger();
        } while (input < MINDESTZELLENANZAHL);
        return input;
    }

    /**
     * @return
     */
    public int wahrscheinlichkeitDerBesiedelung() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - 100 Eingeben: ");
            input = io.leseInteger();
        } while (input < 1 || input > 100);
        return input;
    }

    /**
     * #
     *
     * @return
     */
    public int anzahlDerSimulationsschritte() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - " + Integer.MAX_VALUE + " Eingeben: ");
            input = io.leseInteger();
        } while (input < 1);
        return input;
    }
}

