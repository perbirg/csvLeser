package no.pbe.csvleser.filbehandling;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

public class CsvAliasMap {
    private final Map<String, String> aliasMap;

    public CsvAliasMap(final Map<String, String> aliasMap) {
        Map<String, String> konvertertMap = aliasMap
                .entrySet()
                .stream()
                .collect(toMap(e -> e.getKey().toUpperCase(), Map.Entry::getValue, (a, b) -> b));

        this.aliasMap = konvertertMap;
    }

    public String kolonnenavn(final String nøkkel) {
        final String oppslag = aliasMap.get(nøkkel.toUpperCase());

        return oppslag != null ? oppslag : nøkkel;
    }
}
