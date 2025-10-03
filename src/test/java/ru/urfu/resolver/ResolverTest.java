package ru.urfu.resolver;

import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTest {

    @Test
    void getCountWithoutAgency() {
        {
            var players = List.of(
                    new Player("A", Position.FORWARD, "", 0, "", 0, 0, ""),
                    new Player("B", Position.FORWARD, "1", 0, "", 0, 0, ""),
                    new Player("C", Position.FORWARD, "", 0, "", 0, 0, ""),
                    new Player("D", Position.FORWARD, "2", 0, "", 0, 0, ""),
                    new Player("E", Position.FORWARD, "3", 0, "", 0, 0, "")
            );
            var resolver = new Resolver(players);
            assertEquals(2, resolver.getCountWithoutAgency());
        }
        {
            List<Player> emptyList = Collections.emptyList();
            var resolver = new Resolver(emptyList);
            assertEquals(0, resolver.getCountWithoutAgency());
        }
    }

    @Test
    void getMaxDefenderGoalsCount() {
        {
            var players = List.of(
                    new Player("A", Position.DEFENDER, "", 10, "", 0, 0, ""),
                    new Player("B", Position.FORWARD, "", 40, "", 0, 0, ""),
                    new Player("C", Position.DEFENDER, "", 30, "", 0, 0, ""),
                    new Player("D", Position.GOALKEEPER, "", 50, "", 0, 0, ""),
                    new Player("E", Position.DEFENDER, "", 20, "", 0, 0, ""),
                    new Player("F", Position.MIDFIELD, "", 60, "", 0, 0, "")
            );
            var resolver = new Resolver(players);
            assertEquals(30, resolver.getMaxDefenderGoalsCount());
        }
        {
            var playersWithoutGoalKeepers = List.of(
                    new Player("A", Position.FORWARD, "", 10, "", 0, 0, ""),
                    new Player("B", Position.GOALKEEPER, "", 20, "", 0, 0, ""),
                    new Player("C", Position.MIDFIELD, "", 30, "", 0, 0, "")
            );
            var resolver = new Resolver(playersWithoutGoalKeepers);
            assertEquals(0, resolver.getMaxDefenderGoalsCount());
        }
        {
            List<Player> emptyList = Collections.emptyList();
            var resolver = new Resolver(emptyList);
            assertEquals(0, resolver.getMaxDefenderGoalsCount());
        }
    }

    @Test
    void getTheExpensiveGermanPlayerPosition() {
        {
            var players = List.of(
                    new Player("A", Position.DEFENDER, "", 0, Resolver.GERMAN, 10, 0, ""),
                    new Player("B", Position.MIDFIELD, "", 0, "Other", 40, 0, ""),
                    new Player("C", Position.GOALKEEPER, "", 0, Resolver.GERMAN, 30, 0, ""),
                    new Player("D", Position.FORWARD, "", 0, Resolver.GERMAN, 20, 0, "")
            );
            var resolver = new Resolver(players);
            assertEquals(Position.GOALKEEPER.inRussian(), resolver.getTheExpensiveGermanPlayerPosition());
        }
        {
            var noGermanPlayers = List.of(
                    new Player("A", Position.DEFENDER, "", 0, "Other", 10, 0, ""),
                    new Player("B", Position.MIDFIELD, "", 0, "Other", 40, 0, ""),
                    new Player("C", Position.GOALKEEPER, "", 0, "Other", 30, 0, ""),
                    new Player("D", Position.FORWARD, "", 0, "Other", 20, 0, "")
            );
            var resolver = new Resolver(noGermanPlayers);
            assertEquals("немецкие игроки отсутствуют", resolver.getTheExpensiveGermanPlayerPosition());
        }
        {
            List<Player> emptyList = Collections.emptyList();
            var resolver = new Resolver(emptyList);
            assertEquals("немецкие игроки отсутствуют", resolver.getTheExpensiveGermanPlayerPosition());
        }
    }

    @Test
    void getTheRudestTeam() {
        {
            var players = List.of(
                    new Player("A", Position.DEFENDER, "", 0, "", 0, 1, "T1"),
                    new Player("B", Position.DEFENDER, "", 0, "", 0, 6, "T2"),
                    new Player("C", Position.DEFENDER, "", 0, "", 0, 9, "T1"),
                    new Player("D", Position.DEFENDER, "", 0, "", 0, 5, "T2")
            );
            var resolver = new Resolver(players);
            assertEquals("T2", resolver.getTheRudestTeam());
        }
        {
            List<Player> emptyList = Collections.emptyList();
            var resolver = new Resolver(emptyList);
            assertEquals("команды отсутствуют", resolver.getTheRudestTeam());
        }
    }
}