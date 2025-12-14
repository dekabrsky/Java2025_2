package ru.urfu.data.parser;

import org.junit.jupiter.api.Test;
import ru.urfu.domain.model.Country;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvLoaderTest {

    @Test
    void load_ShouldCorrectlyParseFile() throws Exception {
        File tempFile = File.createTempFile("test-country-data", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Country or area,Subregion,Region,Internet users,Population\n");
            writer.write("\"Testland\",\"Test Subregion\",\"Test Region\",\"1,234,567\",\"2,000,000\"\n");
        }

        List<Country> countries = CsvLoader.load(tempFile.getAbsolutePath());
        tempFile.delete();

        assertEquals(1, countries.size());
        Country firstCountry = countries.get(0);
        assertEquals("Testland", firstCountry.getName());
        assertEquals(1234567L, firstCountry.getInternetUsers());
        assertEquals(2000000L, firstCountry.getPopulation());
    }

    @Test
    void parseNumber_ShouldHandleVariousFormats() {
        assertEquals(1000L, CsvLoader.parseNumber("1,000"));
        assertEquals(500L, CsvLoader.parseNumber("500"));
        assertEquals(0L, CsvLoader.parseNumber(""));
        assertEquals(0L, CsvLoader.parseNumber(null));
    }
}