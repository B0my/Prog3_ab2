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
        int i = 2;
        while (i < this.seitenlaenge -1){
            ib.neueLinie(this.x + i, this.y + 2, this.x + i, this.y + this.seitenlaenge - 2);
            i++;
        }
    }
}