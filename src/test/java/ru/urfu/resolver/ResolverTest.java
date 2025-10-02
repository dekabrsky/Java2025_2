package ru.urfu.resolver;

import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.resolver.Resolver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTest {

    @Test
    void testGetCountWithoutAgency() {
        List<Player> players = Arrays.asList(
            new Player("Player1", "Team1", "City1", "FORWARD", "Germany", "", 1000000, 10, 5, 3, 1, 0),
            new Player("Player2", "Team2", "City2", "DEFENDER", "Brazil", "Agency1", 2000000, 15, 2, 4, 2, 0),
            new Player("Player3", "Team3", "City3", "MIDFIELD", "Croatia", "", 1500000, 12, 3, 5, 1, 1)
        );

        Resolver resolver = new Resolver(players);
        assertEquals(2, resolver.getCountWithoutAgency());
    }

    @Test
    void testGetMaxDefenderGoalsCount() {
        List<Player> players = Arrays.asList(
            new Player("Player1", "Team1", "City1", "DEFENDER", "Germany", "Agency1", 1000000, 10, 3, 2, 1, 0),
            new Player("Player2", "Team2", "City2", "DEFENDER", "Brazil", "Agency2", 2000000, 15, 5, 4, 2, 0),
            new Player("Player3", "Team3", "City3", "FORWARD", "Croatia", "Agency3", 3000000, 12, 10, 5, 1, 0)
        );

        Resolver resolver = new Resolver(players);
        assertEquals(5, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testGetTheExpensiveGermanPlayerPosition() {
        List<Player> players = Arrays.asList(
            new Player("Player1", "Team1", "City1", "DEFENDER", "Germany", "Agency1", 1000000, 10, 3, 2, 1, 0),
            new Player("Player2", "Team2", "City2", "FORWARD", "Germany", "Agency2", 5000000, 15, 5, 4, 2, 0),
            new Player("Player3", "Team3", "City3", "MIDFIELD", "Brazil", "Agency3", 3000000, 12, 10, 5, 1, 0)
        );

        Resolver resolver = new Resolver(players);
        assertEquals("Нападающий", resolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void testGetTheRudestTeam() {
        List<Player> players = Arrays.asList(
            new Player("Player1", "Team1", "City1", "DEFENDER", "Germany", "Agency1", 1000000, 10, 3, 2, 1, 2),
            new Player("Player2", "Team1", "City1", "FORWARD", "Brazil", "Agency2", 2000000, 15, 5, 4, 2, 3),
            new Player("Player3", "Team2", "City2", "MIDFIELD", "Croatia", "Agency3", 3000000, 12, 10, 5, 1, 1),
            new Player("Player4", "Team2", "City2", "GOALKEEPER", "Germany", "Agency1", 4000000, 8, 1, 0, 0, 1)
        );

        Resolver resolver = new Resolver(players);
        assertEquals("Team1", resolver.getTheRudestTeam());
    }
}
