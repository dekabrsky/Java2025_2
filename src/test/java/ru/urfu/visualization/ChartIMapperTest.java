package ru.urfu.visualization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.urfu.io.CsvPlayerParser;
import ru.urfu.io.IParser;
import ru.urfu.model.Player;
import ru.urfu.resolver.PlayerResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChartIMapperTest {

    private final List<Player> data;
    private final ChartDrawer chartDrawer;

    public ChartIMapperTest() throws IOException {
        IParser<Player> parser = new CsvPlayerParser();
        this.data = parser.parse("src/test/resources/testData.csv");
        PlayerResolver resolver = new PlayerResolver(this.data);
        this.chartDrawer = new ChartDrawer(resolver);
    }

    @Test
    @DisplayName("testMap")
    public void testMap() {
        Map<String, Long> countriesPlayersCount = new HashMap<>();
        long playersCount = data.size();

        for (Player p : data) {
            long countryCount = 0;
            if (countriesPlayersCount.containsKey(p.nationality())) {
                countryCount = countriesPlayersCount.get(p.nationality());
            }
            countriesPlayersCount.put(p.nationality(), countryCount + 1);
        }

        Map<String, Double> countriesPlayersPercentage = new HashMap<>();
        for (Map.Entry<String, Long> entry : countriesPlayersCount.entrySet()) {
            countriesPlayersPercentage.put(entry.getKey(), 100 * entry.getValue().doubleValue() / playersCount);
        }

        assertEquals(countriesPlayersPercentage, new ChartDataMapper().map(data).data());
    }
}
