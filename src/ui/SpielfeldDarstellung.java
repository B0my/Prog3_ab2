package ui;

import util.Interaktionsbrett;

public class SpielfeldDarstellung {
    private Interaktionsbrett ib;
    private final int FRAME_WIDTH = 380;
    private final int FRAME_HEIGHT = 500;
    private int margin = 10;

    /**
     * @param ib
     */
    public SpielfeldDarstellung(Interaktionsbrett ib) {
        this.ib = ib;
        erstelleSpielRahmen();
    }

    /**
     * @param spielfeld
     */
    public void spielfeldDarstellen(boolean[][] spielfeld) {
        for (int i = 1; i <= spielfeld.length; i++) {
            for (int j = 1; j <= spielfeld.length; j++) {
                if (spielfeld[i][j] == true) {
                    ib.neuesRechteck(i * i + margin, j * j + margin, 10, 10);

                }else {
                    ib.neuesRechteck(i * i + margin, j * j + margin, 10, 10);
                }
            }
        }
    }


    public void abwischen() {
        ib.abwischen();
    }

    public void erstelleSpielRahmen() {
        ib.neuesRechteck(margin, margin, FRAME_WIDTH - margin * 2, FRAME_HEIGHT - margin * 2);
    }
}
