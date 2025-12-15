package ru.urfu.data.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.domain.model.Country;
import ru.urfu.domain.repository.CountryRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHandlerTest {

    private Connection connection;
    private DatabaseHandler dbHandler;

    @BeforeEach
    void setUp() throws SQLException {
        // создаем чистую базу данных в оперативной памяти
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // создаем наш DatabaseHandler, передавая ему эту временную базу
        dbHandler = new DatabaseHandler(connection);

        // создаем в ней таблицы
        dbHandler.initStorage();

        // заполняем ее тестовыми данными, которые мы точно знаем
        List<Country> testCountries = Arrays.asList(
                new Country("Belarus", "Eastern Europe", "Europe", 7_000_000, 9_500_000),
                new Country("Poland", "Eastern Europe", "Europe", 34_000_000, 38_000_000),
                new Country("Germany", "Western Europe", "Europe", 77_000_000, 83_000_000),
                new Country("Bermuda", "Northern America", "Americas", 60_000, 62_000)
        );
        dbHandler.saveCountries(testCountries);
    }

    @AfterEach
    void tearDown() throws SQLException {
        // закрываем соединение, чтобы полностью очистить базу в памяти
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void findMinUsersInEasternEurope_ShouldReturnBelarus() throws SQLException {
        String actual = dbHandler.findMinUsersInEasternEurope();
        String expected = String.format(Locale.US, "%s (%,d чел.)", "Belarus", 7_000_000L);
        assertEquals(expected, actual);
    }

    @Test
    void findCountriesByPenetration_ShouldReturnCorrectCountries() throws SQLException {
        List<String> results = dbHandler.findCountriesByPenetration(70, 80); // ищем страны с 70-80%
        assertEquals(1, results.size(), "Должна быть найдена только одна страна (Беларусь)");
        assertTrue(results.get(0).contains("Belarus"));

        List<String> highResults = dbHandler.findCountriesByPenetration(90, 100);
        assertEquals(2, highResults.size(), "Должно быть найдено 2 страны (Германия и Бермуды)");
        assertTrue(highResults.get(0).contains("Bermuda"));
    }

    @Test
    void getSubregionPercentages_ShouldCalculateCorrectly() throws SQLException {
        List<CountryRepository.SubregionData> percentages = dbHandler.getSubregionPercentages();

        // ищем данные по Восточной Европе
        CountryRepository.SubregionData easternEuropeData = percentages.stream()
                .filter(d -> d.subregionName.equals("Eastern Europe"))
                .findFirst()
                .orElse(null);

        assertNotNull(easternEuropeData, "Данные по Восточной Европе должны присутствовать");

        // (7_000_000 + 34_000_000) / (9_500_000 + 38_000_000) * 100 = 86.315
        assertEquals(86.31, easternEuropeData.percentage, 0.01, "Процент для Восточной Европы рассчитан неверно");
    }
}