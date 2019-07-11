package no.pbe.csvleser.filbehandling;

import java.util.Objects;

public class CsvKolonne {
    private final Integer kolonnenr;
    private final String kolonnenavn;
    private final String kolonneverdi;

    private CsvKolonne(final Integer kolonnenr, final String kolonnenavn, final String kolonneverdi) {
        this.kolonnenr = kolonnenr;
        this.kolonnenavn = kolonnenavn;
        this.kolonneverdi = kolonneverdi;
    }

    static CsvKolonne csvKolonne(final Integer kolonnenr, final String kolonnenavn, final String kolonneverdi) {
        return new CsvKolonne(kolonnenr, kolonnenavn, kolonneverdi);
    }

    public Integer kolonnenr() {
        return kolonnenr;
    }

    public String kolonnenavn() {
        return kolonnenavn;
    }

    public String kolonneverdi() {
        return kolonneverdi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvKolonne that = (CsvKolonne) o;
        return kolonnenr.equals(that.kolonnenr) &&
                kolonnenavn.equals(that.kolonnenavn) &&
                kolonneverdi.equals(that.kolonneverdi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kolonnenr, kolonnenavn, kolonneverdi);
    }
}
