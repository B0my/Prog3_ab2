package logik;

import util.EinUndAusgabe;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Simulator implements Simulation {

    private boolean[][] spielfeld;
    private BeiAenderung beiAenderung;
    private int anzahlFelder;

    /**
     * @param anzahlDerZellen
     * @param wahrscheinlichkeitDerBesiedlung
     */
    @Override
    public void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung) {
        this.anzahlFelder = anzahlDerZellen;
        this.spielfeld = new boolean[anzahlDerZellen][anzahlDerZellen];
        Random random = new Random();

        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld.length; j++) {
                this.spielfeld[i][j] = random.nextInt(100) < wahrscheinlichkeitDerBesiedlung;
            }
        }
        this.aktualisiere(this.spielfeld);
    }

    /**
     * @param berechnungsschritte
     */
    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) {
        if (berechnungsschritte < 1)
            return;
        if (this.spielfeld == null)
            throw new IllegalStateException("Initialisierung fehlgeschlagen");

        boolean[][] neueGeneration = new boolean[this.anzahlFelder][this.anzahlFelder];

        for (int i = 0; i < this.anzahlFelder; ++i) {
            for (int j = 0; j < this.anzahlFelder; ++j) {
                neueGeneration[i][j] = this.zellenBerechnung(i, j);
            }
        }
        if (Arrays.deepEquals(neueGeneration, this.spielfeld)) {
            System.out.println("Keine Änderung!");
            return;
        }

        this.spielfeld = neueGeneration;
        this.aktualisiere(this.spielfeld);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.berechneFolgeGeneration(berechnungsschritte -1);
    }

    private boolean zellenBerechnung(int cell, int column) {
        int neighbourCounter = 0;

        for (int i = -1; i <= 1; ++i) {
            if (cell + i < 0 || cell + i >= this.anzahlFelder) // Zeile außerhalb des Spielfeldes
                continue;

            for (int j = -1; j <= 1; ++j) {
                if (column + j < 0 || column + j >= this.anzahlFelder) // Spalte außerhalb des Spielfeldes
                    continue;

                if (i == 0 && j == 0) // aktuelle Zelle
                    continue;

                if (this.spielfeld[cell + i][column + j])
                    neighbourCounter++;
            }
        }
        if (this.spielfeld[cell][column])
            return neighbourCounter == 2 || neighbourCounter == 3; // Bleibt lebendig

        return neighbourCounter == 3; // Wird lebendig
    }

   /* private int nachbarZaehlung(int x, int y, int indicator) {
        int counter = 0;

        switch (indicator) {
            case 1: // Oben Links
                if (spielfeld[x + 1][y])
                    counter++;
                if (spielfeld[x + 1][y + 1])
                    counter++;
                if (spielfeld[x][y + 1])
                    counter++;
                break;
            case 2: // Oben
                if (spielfeld[x + 1][y])
                    counter++;
                if (spielfeld[x + 1][y + 1])
                    counter++;
                if (spielfeld[x][y + 1])
                    counter++;
                if (spielfeld[x - 1][y + 1])
                    counter++;
                if (spielfeld[x - 1][y])
                    counter++;
                break;
            case 3: // Oben Rechts
                if (spielfeld[x][y + 1])
                    counter++;
                if (spielfeld[x - 1][y + 1])
                    counter++;
                if (spielfeld[x - 1][y])
                    counter++;
                break;
            case 4: // Links
                if (spielfeld[x][y - 1])
                    counter++;
                if (spielfeld[x + 1][y - 1])
                    counter++;
                if (spielfeld[x + 1][y])
                    counter++;
                if (spielfeld[x + 1][y + 1])
                    counter++;
                if (spielfeld[x][y + 1])
                    counter++;
                break;
            case 5: // Unten links
                if (spielfeld[x][y - 1])
                    counter++;
                if (spielfeld[x + 1][y - 1])
                    counter++;
                if (spielfeld[x + 1][y])
                    counter++;
                break;
            case 6: // Rechts
                if (spielfeld[x][y + 1])
                    counter++;
                if (spielfeld[x][y - 1])
                    counter++;
                if (spielfeld[x - 1][y])
                    counter++;
                if (spielfeld[x - 1][y - 1])
                    counter++;
                if (spielfeld[x - 1][y + 1])
                    counter++;
                break;
            case 7: // Rechts Unten
                if (spielfeld[x][y - 1])
                    counter++;
                if (spielfeld[x - 1][y])
                    counter++;
                if (spielfeld[x - 1][y - 1])
                    counter++;
                break;
            case 8: // Unten
                if (spielfeld[x][y - 1])
                    counter++;
                if (spielfeld[x + 1][y - 1])
                    counter++;
                if (spielfeld[x + 1][y])
                    counter++;
                if (spielfeld[x - 1][y])
                    counter++;
                if (spielfeld[x - 1][y - 1])
                    counter++;
                break;
        }
        return counter;
    }

    private int ccZellenposition(int x, int y) {
        if ((x < 0 || y < 0) && (x > spielfeld.length || y > spielfeld.length)) return -1; // Außerhalb des Spielfeldes

        if (y == 1) { // Erste Reihe
            if (x == 1)
                return 1; // Ecke oben Links 3 Nachbarn
            if (x > 1 && x < spielfeld.length - 1)
                return 2; // Die erste obere Reihe zwischen den Ecken 5 Nachbarn
            if (x == spielfeld.length)
                return 3; // Ecke oben Rechts 3 Nachbarn
        }

        if (x == 1) { // Reihe Links
            // obere linke ecke wurde schon besucht
            if (y > 1 && y < spielfeld.length - 1)
                return 4; // Reihe links zwischen den Ecken 5 Nachbarn
            if (y == spielfeld.length)
                return 5; // Ecke unten links 3 Nachbarn
        }

        if (x == spielfeld.length) { // Reihe Rechts
            // Ecke oben rechts wurde schon besucht
            if (y > 1 && y < spielfeld.length - 1)
                return 6; // zwischen den Ecken 5 Nachbarn
            if (y == spielfeld.length)
                return 7; // Ecke unten Rechts 3 Nachbarn
        }

        if (y == spielfeld.length) // unterste Ecke, Ecke unten links & Ecke unten rechts bereits besucht
            if (y > 1 && y < spielfeld.length)
                return 8; // Untere Reihe zwischen den Ecken 5 Nachbarn

        return -1; // Eingabe Fehlerhaft
    }*/

    /**
     * @param beiAenderung
     */
    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {
        this.beiAenderung = beiAenderung;
    }

    private void aktualisiere(boolean[][] neu) {
        if (this.beiAenderung != null)
            beiAenderung.aktualisiere(neu);
    }
}
