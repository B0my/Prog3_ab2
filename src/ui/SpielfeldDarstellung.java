package ui;

import util.Interaktionsbrett;

public class SpielfeldDarstellung {
    private Interaktionsbrett ib;
    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;
    private int margin = 10;

    /**
     * @param ib
     */
    public SpielfeldDarstellung(Interaktionsbrett ib) {
        this.ib = ib;
    }

    /**
     * @param spielfeld
     */
    public void spielfeldDarstellen(boolean[][] spielfeld) {
        this.abwischen();
        int seitenlaenge = this.FRAME_WIDTH / spielfeld.length;
        for (int i = 0; i < spielfeld.length; ++i) {
            for (int j = 0; j < spielfeld.length; ++j) {
                Quadrat zelle = new Quadrat(this.margin + j* seitenlaenge, this.margin + i * seitenlaenge, seitenlaenge);
                if(spielfeld[i][j])
                    zelle.darstellenFuellung(this.ib);
                else
                    zelle.darstellenRahmen(this.ib);
            }
        }
    }


    public void abwischen() {
        ib.abwischen();
    }

}
