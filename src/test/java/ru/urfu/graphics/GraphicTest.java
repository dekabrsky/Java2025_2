package ru.urfu.graphics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.urfu.model.Player;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphicTest {

    private List<Player> testPlayers = Arrays.asList(
        new Player("Forward1", "Team1", "City1", "FORWARD", "Germany", "Agency1", 10000000, 20, 15, 5, 2, 1),
        new Player("Forward2", "Team2", "City2", "FORWARD", "Brazil", "Agency2", 20000000, 25, 20, 8, 3, 0),
        new Player("Defender1", "Team3", "City3", "DEFENDER", "Spain", "Agency3", 15000000, 18, 5, 10, 4, 2),
        new Player("Goalkeeper1", "Team4", "City4", "GOALKEEPER", "France", "Agency4", 8000000, 30, 0, 2, 1, 0),
        new Player("Forward3", "Team5", "City5", "FORWARD", "Italy", "Agency5", 30000000, 22, 25, 12, 5, 1)
    );

    @Test
    void testCreateDataset_FiltersOnlyForwards() {
        var dataset = Graphic.createDataset(testPlayers);

        assertNotNull(dataset);
        assertEquals(1, dataset.getSeriesCount());

        var series = dataset.getSeries("Нападающие");
        assertNotNull(series);
        assertEquals(3, series.getItemCount());
    }

    @Test
    void testCreateDataset_DataCorrectness() {
        // Act
        var dataset = Graphic.createDataset(testPlayers);
        var series = dataset.getSeries("Нападающие");

        assertEquals(3, series.getItemCount());

        boolean foundForward1 = false;
        boolean foundForward2 = false;
        boolean foundForward3 = false;

        for (int i = 0; i < series.getItemCount(); i++) {
            double x = series.getX(i).doubleValue();
            double y = series.getY(i).doubleValue();

            if (x == 10000000 && y == 15) {
                foundForward1 = true;
            } else if (x == 20000000 && y == 20) {
                foundForward2 = true;
            } else if (x == 30000000 && y == 25) {
                foundForward3 = true;
            }
        }

        assertTrue(foundForward1, "Должен содержать данные Forward1");
        assertTrue(foundForward2, "Должен содержать данные Forward2");
        assertTrue(foundForward3, "Должен содержать данные Forward3");
    }

    @Test
    void testCreateDataset_NoForwards() {
        List<Player> noForwards = Arrays.asList(
            new Player("Defender1", "Team1", "City1", "DEFENDER", "Germany", "Agency1", 10000000, 20, 2, 5, 2, 1),
            new Player("Goalkeeper1", "Team2", "City2", "GOALKEEPER", "Brazil", "Agency2", 5000_000, 25, 0, 1, 1, 0),
            new Player("Midfielder1", "Team3", "City3", "MIDFIELD", "Spain", "Agency3", 15000000, 18, 8, 12, 3, 0)
        );

        var dataset = Graphic.createDataset(noForwards);

        assertNotNull(dataset);
        assertEquals(1, dataset.getSeriesCount());

        var series = dataset.getSeries("Нападающие");
        assertNotNull(series);
        assertEquals(0, series.getItemCount());
    }
}
