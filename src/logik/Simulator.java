package logik;

import java.util.Arrays;
import java.util.Random;

public class Simulator implements Simulation {

    private boolean[][] gameField;
    private BeiAenderung onSwitch;
    private int amountFields;

    /**
     * Erstellt mithilfe der anzahl der Zellen ein neues boolean[][] und gibt den einzelnen Zellen des 2-Dimensionalen
     * Arrays mithilfe der wahrscheinlichkeit der Besiedelung einen Wert. Nachdem alles erstellt wurde wird das Feld 
     * mithilfe der aktualisieren() methode aktualisiert.
     *
     * @param amountCells                 beschreibt die Anzahl der Zellen für eine Seitenlaenge des Spielfeldes
     * @param probability beschreibt die wahrscheinlichkeit der Besiedlung für das Spielfeld
     */
    @Override
    public void calculateFirstGeneration(int amountCells, int probability) {
        this.amountFields = amountCells;
        this.gameField = new boolean[amountCells][amountCells];
        Random random = new Random();

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                this.gameField[i][j] = random.nextInt(100) < probability;
            }
        }
        this.update(this.gameField);
    }

    /**
     * 
     * 
     * @param amountSteps Der nutzer gibt an wie viele Folgegenerationen berechnet werden sollen
     */
    @Override
    public void calculateNextGeneration(int amountSteps) {
        if (amountSteps < 1) //
            return;
        if (this.gameField == null) // auf einem nicht existierenden spielfeld kann nicht gearbeitet werden
            throw new IllegalStateException("Initialisierung fehlgeschlagen");

        boolean[][] newGeneration = new boolean[this.amountFields][this.amountFields];

        for (int i = 0; i < this.amountFields; ++i) {
            for (int j = 0; j < this.amountFields; ++j) {
                newGeneration[i][j] = this.updateCells(i, j);
            }
        }
        if (Arrays.deepEquals(newGeneration, this.gameField)) {
            System.out.println("Keine Änderung!");
            return;
        }

        this.gameField = newGeneration;
        this.update(this.gameField);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.calculateNextGeneration(amountSteps - 1);
    }

    private boolean updateCells(int lines, int column) {
        int neighbourCounter = 0;

        for (int i = -1; i <= 1; ++i) {
            if (lines + i < 0 || lines + i >= this.amountFields) // Zeile außerhalb des Spielfeldes
                continue;

            for (int j = -1; j <= 1; ++j) {
                if (column + j < 0 || column + j >= this.amountFields) // Spalte außerhalb des Spielfeldes
                    continue;

                if (i == 0 && j == 0) // aktuelle Zelle
                    continue;

                if (this.gameField[lines + i][column + j])
                    neighbourCounter++;
            }
        }
        if (this.gameField[lines][column])
            return neighbourCounter == 2 || neighbourCounter == 3; // Bleibt lebendig

        return neighbourCounter == 3; // Wird lebendig
    }

    /**
     * @param beiAenderung
     */
    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {
        this.onSwitch = beiAenderung;
    }

    private void update(boolean[][] neu) {
        if (this.onSwitch != null)
            onSwitch.aktualisiere(neu);
    }
}
