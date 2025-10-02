package ru.urfu.graphic;

import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphicTester{

    @Test
    void getPositionDistribution_ShouldReturnCorrectCounts() {
        // Given
        List<Player> players = List.of(
                new Player("Player1", "TeamA", "City1", Position.DEFENDER, "Germany", "Agency1",
                        1000000, 10, 5, 3, 2, 1),
                new Player("Player2", "TeamA", "City1", Position.DEFENDER, "Germany", "Agency2",
                        2000000, 15, 10, 5, 1, 0),
                new Player("Player3", "TeamB", "City2", Position.FORWARD, "France", "Agency3",
                        1500000, 12, 8, 4, 3, 2),
                new Player("Player4", "TeamB", "City2", Position.MIDFIELD, "Spain", "Agency4",
                        800000, 8, 3, 7, 0, 0),
                new Player("Player5", "TeamC", "City3", Position.GOALKEEPER, "Germany", "Agency5",
                        3000000, 20, 1, 0, 0, 0)
        );

        // When
        Map<Position, Long> distribution = ChartBuilder.getPositionDistribution(players);

        // Then
        assertAll(
                () -> assertEquals(2, distribution.get(Position.DEFENDER)),
                () -> assertEquals(1, distribution.get(Position.FORWARD)),
                () -> assertEquals(1, distribution.get(Position.MIDFIELD)),
                () -> assertEquals(1, distribution.get(Position.GOALKEEPER)),
                () -> assertEquals(4, distribution.size())
        );
    }

    @Test
    void getPositionDistribution_WithEmptyList_ShouldReturnEmptyMap() {
        // Given
        List<Player> emptyList = List.of();

        // When
        Map<Position, Long> distribution = ChartBuilder.getPositionDistribution(emptyList);

        // Then
        assertTrue(distribution.isEmpty());
    }
}