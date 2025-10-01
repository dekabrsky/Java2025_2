package ru.urfu.resolver;

import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResolverTest {

    @Test
    void testGetCountWithoutAgency() {
        List<Player> players = List.of(
                new Player("A", Position.FORWARD, "", 10, "Germany", 100_000_000, "TeamX", 1),
                new Player("B", Position.DEFENDER, "AgentX", 5, "France", 50_000_000, "TeamY", 0)
        );
        Resolver resolver = new Resolver(players);
        assertEquals(1, resolver.getCountWithoutAgency());
    }

    @Test
    void testMaxDefenderGoals() {
        List<Player> players = List.of(
                new Player("A", Position.DEFENDER, "Agent", 3, "Spain", 10_000_000, "TeamA", 0),
                new Player("B", Position.DEFENDER, "Agent", 7, "Spain", 20_000_000, "TeamA", 1)
        );
        Resolver resolver = new Resolver(players);
        assertEquals(7, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testExpensiveGermanPosition() {
        List<Player> players = List.of(
                new Player("A", Position.MIDFIELD, "Agent", 10, "Germany", 90_000_000, "TeamA", 0),
                new Player("B", Position.FORWARD, "Agent", 15, "Germany", 120_000_000, "TeamB", 1)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Нападающий", resolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void testRudestTeam() {
        List<Player> players = List.of(
                new Player("A", Position.FORWARD, "Agent", 10, "Germany", 90_000_000, "TeamA", 2),
                new Player("B", Position.DEFENDER, "Agent", 5, "France", 50_000_000, "TeamB", 0),
                new Player("C", Position.DEFENDER, "Agent", 5, "France", 50_000_000, "TeamB", 0)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("TeamA", resolver.getTheRudestTeam());
    }
}
