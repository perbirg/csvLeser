package no.pbe.csvleser.filbehandling;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;

import org.jooq.lambda.Seq;

public class CsvRad {
    private final String[] kolonner;
    private final String rad;
    private final Map<String, CsvKolonne> kolonneMap;

    private CsvRad(final String rad, final CsvRad overskriftRad) {
        this.kolonner = rad.split(";");
        this.rad = rad;

        this.kolonneMap = Seq
                .of(kolonner)
                .zipWithIndex()
                .collect(toMap(
                        tuple2 -> overskriftRad.kolonne(tuple2.v2().intValue()).toUpperCase(),
                        tuple2 -> CsvKolonne.csvKolonne(tuple2.v2().intValue(), overskriftRad.kolonne(tuple2.v2().intValue()), tuple2.v1())));
    }

    public static CsvRad fraCsv(final String rad, final CsvRad overskriftRad) {
        return new CsvRad(rad, overskriftRad);
    }

    public static CsvRad overskriftRadFraCsv(final String rad) {
        return new CsvRad(rad);
    }

    public CsvKolonne csvKolonne(String kolonnenavn) {
        var kolonne = kolonneMap.get(kolonnenavn.toUpperCase());
        if (kolonne == null) {
            throw new IllegalStateException("Kolonnen " + kolonnenavn + " mangler");
        }

        return kolonne;
    }

    public Optional<CsvKolonne> kolonneSomBegynnerMed(String prefiks) {
        return kolonneMap
                .keySet()
                .stream()
                .filter(key -> key.toUpperCase().startsWith(prefiks.toUpperCase()))
                .map(kolonneMap::get).findFirst();
    }

    public String tilCsv() {
        return rad;
    }

    private CsvRad(final String rad) {
        this.kolonner = rad.split(";");
        this.rad = rad;

        this.kolonneMap = Seq
                .of(kolonner)
                .zipWithIndex()
                .collect(toMap(tuple2 -> tuple2.v1().toUpperCase(), tuple2 -> CsvKolonne.csvKolonne(tuple2.v2().intValue(), tuple2.v1(), tuple2.v1())));
    }

    private String kolonne(int kolonnenummer) {
        return kolonner[kolonnenummer];
    }

}
