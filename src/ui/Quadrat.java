package ui;

import util.Interaktionsbrett;

public class Quadrat {

    private boolean amLeben;
    private int x;
    private int y;
    private int seitenlaenge;

    public Quadrat(){
        setSeitenlaenge(10);
    }

    public Quadrat(int x, int y, int laenge){
        setX(x);
        setY(y);
        setSeitenlaenge(laenge);
    }



    /**
     *
     * @param ib
     */
    public void darstellenRahmen(Interaktionsbrett ib) {

    }

    /**
     *
     * @param ib
     */
    public void darstellenFuellung(Interaktionsbrett ib) {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSeitenlaenge() {
        return seitenlaenge;
    }

    public void setSeitenlaenge(int seitenlaenge) {
        this.seitenlaenge = seitenlaenge;
    }

    /**
     * Ab hier Builder Klasse für Quadrate
     */

    private Quadrat(QuadratBuilder builder) {
        this.x=builder.x;
        this.y=builder.y;
        this.seitenlaenge= builder.seitenlaenge;
        this.amLeben=builder.amLeben;
    }

    //Builder Class
    public static class QuadratBuilder{

        // required parameters
        private int x;
        private int y;
        private int seitenlaenge;
        private boolean amLeben;

        public QuadratBuilder(int x, int y){
            this.x = x;
            this.y = y;

            this.seitenlaenge = 10;
            this.amLeben = false;
        }

        public QuadratBuilder(int x, int y, int laenge, boolean leben){
            this.x=x;
            this.y=y;
            this.seitenlaenge=laenge;
            this.amLeben = leben;
        }

        public QuadratBuilder setDead() {
            this.amLeben = false;
            return this;
        }

        public Quadrat build(){
            return new Quadrat(this);
        }

    }
}
