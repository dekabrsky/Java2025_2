package ru.urfu.reolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.resolver.Resolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTester {

    private List<Player> testPlayers;
    private Resolver resolver;

    @BeforeEach
    void setUp() {
        testPlayers = List.of(
                new Player("Player1", "TeamA", "City1", Position.DEFENDER, "Germany", "Agency1",
                        1000000, 10, 5, 3, 2, 1),
                new Player("Player2", "TeamA", "City1", Position.FORWARD, "Germany", "",
                        2000000, 15, 10, 5, 1, 0),
                new Player("Player3", "TeamB", "City2", Position.DEFENDER, "France", "Agency2",
                        1500000, 12, 8, 4, 3, 2),
                new Player("Player4", "TeamB", "City2", Position.MIDFIELD, "Spain", "",
                        800000, 8, 3, 7, 0, 0),
                new Player("Player5", "TeamC", "City3", Position.GOALKEEPER, "Germany", "Agency3",
                        3000000, 20, 1, 0, 0, 0)
        );

        resolver = new Resolver(testPlayers);
    }

    @Test
    void getCountWithoutAgency_ShouldReturnCorrectCount() {
        assertEquals(2, resolver.getCountWithoutAgency());
    }

    @Test
    void getMaxDefenderGoalsCount_ShouldReturnMaxGoalsFromDefenders() {
        assertEquals(8, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void getMaxDefenderGoalsCount_WhenNoDefenders_ShouldReturnZero() {
        List<Player> noDefenders = testPlayers.stream()
                .filter(p -> p.position() != Position.DEFENDER)
                .toList();
        Resolver noDefResolver = new Resolver(noDefenders);

        assertEquals(0, noDefResolver.getMaxDefenderGoalsCount());
    }

    @Test
    void getTheExpensiveGermanPlayerPosition_ShouldReturnMostExpensiveGermanPlayerPosition() {
        assertEquals(Position.GOALKEEPER.toString(), resolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void getTheRudestTeam_ShouldReturnTeamWithHighestAverageRedCards() {
        // TeamA: 1 red card for 2 players = 0.5 average
        // TeamB: 2 red cards for 2 players = 1.0 average
        // TeamC: 0 red cards for 1 player = 0 average
        assertEquals("TeamB", resolver.getTheRudestTeam());
    }
}