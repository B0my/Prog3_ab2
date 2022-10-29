package ui;

import util.EinUndAusgabe;

public class NutzerEingabe {
    private EinUndAusgabe io;

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
        while (true) {
            input = io.leseInteger();
            if ((input <= 100) && (input >= 1)) {
                return io.leseInteger();
            }
        }
    }

    /**
     * @return
     */
    public int wahrscheinlichkeitDerBesiedelung() {
        int input;
        while (true) {
            input = io.leseInteger();
            if ((input <= 100) && (input >= 1)) {
                return input;

            } else {
                System.out.println("Bitte einen Gültigen Wert zwischen 1 - 100 Eingeben: ");
            }
        }
    }

    /**
     * #
     *
     * @return
     */
    public int anzahlDerSimulationsschritte() {
        int input;
        while (true) {
            input = io.leseInteger();
            if ((input <= Integer.MAX_VALUE) && (input >= 1)) {
                return input;

            } else {
                System.out.println("Bitte einen Gültigen Wert zwischen 1 - " + Integer.MAX_VALUE + " Eingeben: ");
            }
        }
    }

    public EinUndAusgabe getIo() {
        return this.io;
    }
}

