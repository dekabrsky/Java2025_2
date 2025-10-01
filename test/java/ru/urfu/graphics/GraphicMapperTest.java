package ru.urfu.graphics;

import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.graphics.Graphic;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphicMapperTest {

    @Test
    void testCreateDatasetFiltersAndMapsCorrectly() {
        List<Player> players = List.of(
                new Player("Messi", Position.FORWARD, "Agent", 30, "Argentina", 100_000_000, "PSG", 0),
                new Player("Neuer", Position.GOALKEEPER, "Agent", 0, "Germany", 50_000_000, "Bayern", 0)
        );

        XYSeriesCollection dataset = Graphic.createDataset(players);

        assertEquals(1, dataset.getSeriesCount());
        assertEquals(1, dataset.getSeries(0).getItemCount());
        assertEquals(100_000_000, dataset.getSeries(0).getX(0).intValue());
        assertEquals(30, dataset.getSeries(0).getY(0).intValue());
    }
    @Test
    void testMultipleForwardsMappedCorrectly() {
        List<Player> players = List.of(
                new Player("Messi", Position.FORWARD, "Agent", 30, "Argentina", 100_000_000, "PSG", 0),
                new Player("Mbappe", Position.FORWARD, "Agent", 40, "France", 180_000_000, "PSG", 1),
                new Player("Neuer", Position.GOALKEEPER, "Agent", 0, "Germany", 50_000_000, "Bayern", 0)
        );

        XYSeriesCollection dataset = Graphic.createDataset(players);

        assertEquals(1, dataset.getSeriesCount());
        assertEquals(2, dataset.getSeries(0).getItemCount());

        assertEquals(100_000_000, dataset.getSeries(0).getX(0).intValue());
        assertEquals(30, dataset.getSeries(0).getY(0).intValue());

        assertEquals(180_000_000, dataset.getSeries(0).getX(1).intValue());
        assertEquals(40, dataset.getSeries(0).getY(1).intValue());
    }

    @Test
    void testEmptyPlayerListReturnsEmptyDataset() {
        List<Player> players = List.of();
        XYSeriesCollection dataset = Graphic.createDataset(players);

        assertEquals(1, dataset.getSeriesCount());
        assertEquals(0, dataset.getSeries(0).getItemCount());
    }

}
