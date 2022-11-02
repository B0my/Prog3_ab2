package ui;

import util.Interaktionsbrett;

public class Quadrat {

    private int x;
    private int y;
    private int seitenlaenge;

    public Quadrat(int x, int y, int laenge) {
        setX(x);
        setY(y);
        setSeitenlaenge(laenge);
    }


    /**
     * @param ib
     */
    public void darstellenRahmen(Interaktionsbrett ib) {
        ib.neuesRechteck(this.x, this.y, this.seitenlaenge, this.seitenlaenge);
    }

    /**
     * @param ib
     */
    public void darstellenFuellung(Interaktionsbrett ib) {
        this.darstellenRahmen(ib);
        for (int div = 1; div < seitenlaenge; div++) {
            ib.neueLinie(x, y, x + seitenlaenge / div, y + seitenlaenge / div);
        }
    }

    public void setX(int x) {
        if (x < 0)
            throw new IllegalArgumentException("X-Position muss positiv sein");
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0)
            throw new IllegalArgumentException("Y-Position muss negativ sein");
        this.y = y;
    }

    public void setSeitenlaenge(int seitenlaenge) {
        this.seitenlaenge = seitenlaenge;
    }

    /**
     * Ab hier Builder Klasse für Quadrate
     */
}