package test.java.ru.urfu;

import main.java.ru.urfu.mapper.Mapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import main.java.ru.urfu.model.Player;
import main.java.ru.urfu.model.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapperTest {
    @Test
    void testGetPlayersByPositionAll() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Nat 1", "Agency 1", 100, 0, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Nat 1", "Agency 1", 100, 0, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.GOALKEEPER, "Nat 2", "Agency 2", 100, 0, 0, 0, 0),
                new Player("Player 4", "Team B", "City B", Position.MIDFIELD, "Nat 2", "Agency 2", 100, 0, 0, 0, 0),
                new Player("Player 5", "Team C", "City C", Position.FORWARD, "Nat 3", "Agency 3", 100, 0, 0, 0, 0)
        );

        Mapper mapper = new Mapper(players);
        Map<Position, Long> playersByPosition = mapper.getPlayersByPosition();

        assertEquals(2L, playersByPosition.get(Position.GOALKEEPER));
        assertEquals(1L, playersByPosition.get(Position.DEFENDER));
        assertEquals(1L, playersByPosition.get(Position.MIDFIELD));
        assertEquals(1L, playersByPosition.get(Position.FORWARD));
    }

    @Test
    void testGetPlayersByPositionPart() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Nat 1", "Agency 1", 100, 0, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Nat 1", "Agency 1", 100, 0, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.GOALKEEPER, "Nat 2", "Agency 2", 100, 0, 0, 0, 0),
                new Player("Player 4", "Team B", "City B", Position.MIDFIELD, "Nat 2", "Agency 2", 100, 0, 0, 0, 0)
        );

        Mapper mapper = new Mapper(players);
        Map<Position, Long> playersByPosition = mapper.getPlayersByPosition();

        assertEquals(2L, playersByPosition.get(Position.GOALKEEPER));
        assertEquals(1L, playersByPosition.get(Position.DEFENDER));
        assertEquals(1L, playersByPosition.get(Position.MIDFIELD));
    }

    @Test
    void testGetPlayersByPositionZero() {
        List<Player> players = Collections.emptyList();

        Mapper mapper = new Mapper(players);
        Map<Position, Long> playersByPosition = mapper.getPlayersByPosition();

        assertEquals(0, playersByPosition.size());
    }
}
