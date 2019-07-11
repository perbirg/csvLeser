package no.pbe.csvleser.filbehandling;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CsvAliasMapTest {

    @Test
    public void oppslag_kolonnenavn_skal_gi_alias() {
        Map<String, String> aliasMap = new HashMap<>();
        aliasMap.put("key", "alias");

        CsvAliasMap csvAliasMap = new CsvAliasMap(aliasMap);

        assertThat(csvAliasMap.kolonnenavn("key")).isEqualTo("alias");
    }

    @Test
    public void oppslag_kolonnenavn_skal_være_case_insensitiv() {
        Map<String, String> aliasMap = new HashMap<>();
        aliasMap.put("KeY", "alias");

        CsvAliasMap csvAliasMap = new CsvAliasMap(aliasMap);

        assertThat(csvAliasMap.kolonnenavn("key")).isEqualTo("alias");
    }

}