package test.java.ru.urfu;

import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.io.GraphDrawer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapperTest {
    @Test
    static void testFirst() {
        List<Player> players = List.of(
                new Player("Player A", "Team A", "City A", Position.MIDFIELD, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.MIDFIELD, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.DEFENDER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.DEFENDER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0)
        );

        Map<Position, Integer> playersByPosition = GraphDrawer.prepareData(players);

        assertEquals(2, playersByPosition.get(Position.MIDFIELD));
        assertEquals(3, playersByPosition.get(Position.GOALKEEPER));
        assertEquals(2, playersByPosition.get(Position.DEFENDER));
        assertEquals(0, playersByPosition.get(Position.FORWARD));
    }

	@Test
    static void testSecond() {
        List<Player> players = List.of(
                new Player("Player A", "Team A", "City A", Position.MIDFIELD, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.MIDFIELD, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.GOALKEEPER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.DEFENDER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0),
                new Player("Player A", "Team A", "City A", Position.DEFENDER, "Nat A", "Agency A", 0, 0, 0, 0, 0, 0)
        );

        Map<Position, Integer> playersByPosition = GraphDrawer.prepareData(players);

        assertEquals(2, playersByPosition.get(Position.MIDFIELD));
        assertEquals(3, playersByPosition.get(Position.GOALKEEPER));
        assertEquals(2, playersByPosition.get(Position.DEFENDER));
        assertEquals(0, playersByPosition.get(Position.FORWARD));
    }

    @Test
    static void testThird() {
        List<Player> players = Collections.emptyList();

        Map<Position, Integer> playersByPosition = GraphDrawer.prepareData(players);

        assertEquals(4, playersByPosition.size());
        assertEquals(0, playersByPosition.get(Position.MIDFIELD));
        assertEquals(0, playersByPosition.get(Position.GOALKEEPER));
        assertEquals(0, playersByPosition.get(Position.DEFENDER));
        assertEquals(0, playersByPosition.get(Position.FORWARD));
    }
}
