package ru.urfu.resolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.drawer.ChartData;
import ru.urfu.model.Player;
import ru.urfu.model.Role;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


class ResolverTest {

    private Resolver resolver;

    @BeforeEach
    void setUp() {
        List<Player> players = List.of(
                new Player("Player1", Role.DEFENDER, "Agency1", 5, "Germany", 10000, "TeamA", 1),
                new Player("Player2", Role.MIDFIELD, null, 3, "Spain", 8000, "TeamB", 0),
                new Player("Player3", Role.FORWARD, "Agency2", 10, "Germany", 20000, "TeamC", 2),
                new Player("Player4", Role.GOALKEEPER, "Agency3", 0, "France", 5000, "TeamA", 4),
                new Player("Player5", Role.DEFENDER, "Agency4", 7, "Italy", 12000, "TeamB", 1)
        );

        resolver = new Resolver(players);
    }

    @Test
    void testGetCountWithoutAgency() {
        assertEquals(1, resolver.getCountWithoutAgency());
    }

    @Test
    void testGetMaxDefenderGoalsCount() {
        assertEquals(7, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testGetTheExpensiveGermanPlayerPosition() {
        assertEquals("Нападающий", resolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void testGetTheRudestTeam() {
        assertEquals("TeamA", resolver.getTheRudestTeam());
    }

    @Test
    void testGetTopNTeams() {
        List<ChartData> topTeams = resolver.getTopNTeams();
        assertEquals(3, topTeams.size());

        ChartData teamC = new ChartData("TeamC", 20000);
        ChartData teamB = new ChartData("TeamB", 20000);
        ChartData teamA = new ChartData("TeamA", 15000);
        assertTrue(topTeams.contains(teamA));
        assertTrue(topTeams.contains(teamB));
        assertTrue(topTeams.contains(teamC));

    }
}