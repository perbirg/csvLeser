package no.pbe.csvleser.filbehandling;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;

public class CsvRadTest {

    @Test
    public void fraCsv() {
        final CsvRad csvRad = CsvRad.fraCsv("2;3", CsvRad.overskriftRadFraCsv("kol1;kol2"));

        assertThat(csvRad.csvKolonne("kol1")).isEqualTo(CsvKolonne.csvKolonne(0, "kol1", "2"));
        assertThat(csvRad.csvKolonne("kol2")).isEqualTo(CsvKolonne.csvKolonne(1, "kol2", "3"));
    }

    @Test
    public void overskriftRadFraCsv() {
        final CsvRad csvRad = CsvRad.overskriftRadFraCsv("kol1;kol2");

        assertThat(csvRad.csvKolonne("kol1")).isEqualTo(CsvKolonne.csvKolonne(0, "kol1", "kol1"));
        assertThat(csvRad.csvKolonne("kol2")).isEqualTo(CsvKolonne.csvKolonne(1, "kol2", "kol2"));
    }

    @Test
    public void oppslag_kolonnenavn_skal_være_case_insensitivt() {

        final CsvRad csvRad = CsvRad.fraCsv("2;3", CsvRad.overskriftRadFraCsv("kol1;kol2"));

        assertThat(csvRad.csvKolonne("koL2")).isEqualTo(CsvKolonne.csvKolonne(1, "kol2", "3"));
    }

    @Test
    public void kolonneSomBegynnerMed() {
        final CsvRad csvRad = CsvRad.fraCsv("2;3", CsvRad.overskriftRadFraCsv("kolonne1;xyxkol"));

        assertThat(csvRad.kolonneSomBegynnerMed("xyx").isPresent()).isTrue();
        assertThat(csvRad.kolonneSomBegynnerMed("xYx").isPresent()).isTrue();

        assertThat(csvRad.kolonneSomBegynnerMed("xxx").isPresent()).isFalse();
    }

    @Test
    public void tilCsv() {
        final CsvRad csvRad = CsvRad.fraCsv("2;3", CsvRad.overskriftRadFraCsv("kol1;kol2"));

        assertThat(csvRad.tilCsv()).isEqualTo("2;3");
    }


}