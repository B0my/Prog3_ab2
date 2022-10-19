package logik;

public interface Simulation {
    /**
     *
     * @param anzahlDerZellen
     * @param wahrscheinlichkeitDerBesiedlung
     */
    void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung);

    /**
     *
     * @param berechnungsschritte
     */
    void berechneFolgeGeneration(int berechnungsschritte);

    /**
     *
     * @param beiAenderung
     */
    void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung);
}
