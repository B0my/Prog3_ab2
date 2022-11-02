package logik;

public interface Simulation {
    void calculateFirstGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung);

    void calculateNextGeneration(int berechnungsschritte);

    void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung);
}