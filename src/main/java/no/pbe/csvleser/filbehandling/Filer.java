package no.pbe.csvleser.filbehandling;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filer {
    
    public static Stream<String> lesLinjerFraFil(final Path innfil) {
        try {
            return Files.newBufferedReader(innfil, StandardCharsets.UTF_8)
                    .lines();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    public static void leggTilLinjerTilFil(final Stream<String> linjer, final Path utFil) {
        final String nyeLinjer = linjer
                .collect(Collectors.joining("\n", "", "\n"));
        try {
            if (utFil.getParent() != null) {
                Files.createDirectories(utFil.getParent());
            }
            Files.write(utFil, nyeLinjer.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void slettFiler(final Path... utFiler) {
        for (Path utFil : utFiler) {
            if (utFil != null && Files.exists(utFil)) {
                try {
                    Files.delete(utFil);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }

    }

    public static Stream<Path> listFiler(final Path katalog) {
        try {
            return Files.walk(katalog);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Greide ikke å liste filer i katalog " + katalog.getFileName());
        }
    }
}

