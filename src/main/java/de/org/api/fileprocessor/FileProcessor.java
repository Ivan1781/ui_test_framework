package de.org.api.fileprocessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileProcessor {

    public static String readFile(String path) {
        try {
            ClassLoader classLoader = FileProcessor.class.getClassLoader();
            return new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(classLoader.getResource(path)).toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void replace() {

    }

}
