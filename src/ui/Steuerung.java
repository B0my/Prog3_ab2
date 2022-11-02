package ui;

import logik.BeiAenderung;
import logik.Simulation;
import logik.Simulator;
import util.EinUndAusgabe;
import util.Interaktionsbrett;

import java.io.IOException;
import java.util.Objects;

public class Steuerung implements BeiAenderung {
    private SpielfeldDarstellung spielfeldDarstellung;
    private Simulation simulation;
    private NutzerEingabe io;


    public Steuerung(Simulation simulation) {
        this.simulation = Objects.requireNonNull(simulation);
        this.initialisierung();
    }

    /**
     *
     */
    private void initialisierung() {
        this.spielfeldDarstellung = new SpielfeldDarstellung(new Interaktionsbrett());
        this.io = new NutzerEingabe(new EinUndAusgabe());
        this.simulation.anmeldenFuerAktualisierungBeiAenderung(this);
    }

    public void startDesSpiels() {
        int anzahlZellen = this.io.anzahlZellenDesSpielfeds();
        int wahrscheinlichkeit = this.io.wahrscheinlichkeitDerBesiedelung();
        this.simulation.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);

        int anzahlSchritte;
        do {
            anzahlSchritte = this.io.anzahlDerSimulationsschritte();
            this.simulation.berechneFolgeGeneration(anzahlSchritte);
        } while (anzahlSchritte >= 0);

        System.out.println("-------------------------------------------------------------" + "\n" + "Und Tschuess!!!");
    }


    /**
     * @param neueGeneration
     */
    @Override
    public void aktualisiere(boolean[][] neueGeneration) {
        this.spielfeldDarstellung.spielfeldDarstellen(neueGeneration);
    }
}