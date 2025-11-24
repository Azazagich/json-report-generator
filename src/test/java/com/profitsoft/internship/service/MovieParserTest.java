package com.profitsoft.internship.service;

import com.profitsoft.internship.domain.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MovieParserTest {

    @TempDir
    Path tempDir;

    @Test
    void testParseJsonFile_EmptyArray() throws IOException {
        File jsonFile = tempDir.resolve("empty_test.json").toFile();
        try (FileWriter writer = new FileWriter(jsonFile)) {
            writer.write("[]");
        }

        MovieParser parser = new MovieParser();
        Iterator<Movie> iterator = parser.parseJsonFile(jsonFile);

        assertFalse(iterator.hasNext());
    }

    @Test
    void testParseJsonFile_InvalidFile() {
        File nonExistentFile = tempDir.resolve("phantom.json").toFile();

        MovieParser parser = new MovieParser();
        Iterator<Movie> iterator = parser.parseJsonFile(nonExistentFile);

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
    }

}
