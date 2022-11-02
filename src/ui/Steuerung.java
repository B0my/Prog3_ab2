package ui;

import logik.BeiAenderung;
import logik.Simulation;
import logik.Simulator;
import util.EinUndAusgabe;
import util.Interaktionsbrett;

import java.io.IOException;

public class Steuerung implements BeiAenderung {
    private SpielfeldDarstellung spielfeldDarstellung;
    private Simulation simulation = new Simulator();
    private NutzerEingabe io;


    /**
     *
     */
    public void startDesSpiels() {
        initialisierung();
    }

    /**
     *
     */
    private void initialisierung() {
        int userInput1;
        int userInput2;

        this.simulation = new Simulator();
        this.io = new NutzerEingabe(new EinUndAusgabe());
        this.spielfeldDarstellung = new SpielfeldDarstellung(new Interaktionsbrett());

        System.out.print("Anzahl der Zellen: ");
        userInput1 = io.getIo().leseInteger();
        System.out.print("\nWahrscheinlichkeit einer Besiedelung (1-100): ");
        userInput2 = io.getIo().leseInteger();
        System.out.print("\nAnzahl durchzufuehrender Simulationsschritte (Abbruch mit negativer Zahl): ");
        userInput1 = io.getIo().leseInteger();
        this.simulation.berechneAnfangsGeneration(userInput1,userInput2);
        if (userInput1 < 0)
            System.out.println("Programm beenden :)");
        else
            this.simulation.berechneFolgeGeneration(userInput1);
        this.simulation.anmeldenFuerAktualisierungBeiAenderung(this::aktualisiere);
    }

    /**
     * @param neueGeneration
     */
    @Override
    public void aktualisiere(boolean[][] neueGeneration) {

    }
}