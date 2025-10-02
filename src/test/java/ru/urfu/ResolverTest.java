package test.java.ru.urfu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import main.java.ru.urfu.resolver.Resolver;
import main.java.ru.urfu.model.Player;
import main.java.ru.urfu.model.Position;

import java.util.Collections;
import java.util.List;

public class ResolverTest {

    @Test
    void getCountWithoutAgencyTest() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Nat 1", "Agency 1", 100, 0, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Nat 1", "", 100, 0, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.GOALKEEPER, "Nat 2", "", 100, 0, 0, 0, 0)
        );
        Resolver resolver = new Resolver(players);
        assertEquals(2, resolver.getCountWithoutAgency());

        List<Player> emptyPlayers = Collections.emptyList();
        Resolver resolverEmpty = new Resolver(emptyPlayers);
        assertEquals(0, resolverEmpty.getCountWithoutAgency());
    }

    @Test
    void getMaxDefenderGoalsCountTest() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Nat 1", "Agency 1", 100, 20, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Nat 1", "Agency 1", 100, 1, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.FORWARD, "Nat 2", "Agency 2", 100, 69, 0, 0, 0),
                new Player("Player 4", "Team B", "City B", Position.DEFENDER, "Nat 2", "Agency 2", 100, 23, 0, 0, 0),
                new Player("Player 5", "Team C", "City C", Position.DEFENDER, "Nat 3", "Agency 3", 100, 45, 0, 0, 0)
        );
        Resolver resolver = new Resolver(players);
        assertEquals(45, resolver.getMaxDefenderGoalsCount());

        List<Player> emptyPlayers = Collections.emptyList();
        Resolver resolverEmpty = new Resolver(emptyPlayers);
        assertEquals(0, resolverEmpty.getMaxDefenderGoalsCount());
    }

    @Test
    void getTheExpensiveGermanPlayerPositionTest() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Germany", "Agency 1", 15, 20, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Germany", "Agency 1", 0, 1, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.FORWARD, "France", "Agency 2", 100, 69, 0, 0, 0),
                new Player("Player 4", "Team B", "City B", Position.DEFENDER, "Germany", "Agency 2", 20, 23, 0, 0, 0),
                new Player("Player 5", "Team C", "City C", Position.DEFENDER, "Russian", "Agency 3", 500000, 45, 0, 0, 0)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Защитник", resolver.getTheExpensiveGermanPlayerPosition());

        List<Player> noGermanPlayers = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "France", "Agency 1", 15, 20, 0, 0, 0),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Russian", "Agency 1", 0, 1, 0, 0, 0)
        );
        Resolver resolverNoGerman = new Resolver(noGermanPlayers);
        assertEquals("Нет немецких игроков", resolverNoGerman.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void getTheRudestTeamTest() {
        List<Player> players = List.of(
                new Player("Player 1", "Team A", "City A", Position.GOALKEEPER, "Nat 1", "Agency 1", 100, 20, 0, 0, 2),
                new Player("Player 2", "Team A", "City A", Position.DEFENDER, "Nat 1", "Agency 1", 100, 1, 0, 0, 0),
                new Player("Player 3", "Team B", "City B", Position.FORWARD, "Nat 2", "Agency 2", 100, 69, 0, 0, 1),
                new Player("Player 4", "Team B", "City B", Position.DEFENDER, "Nat 2", "Agency 2", 100, 23, 0, 0, 6),
                new Player("Player 5", "Team C", "City C", Position.DEFENDER, "Nat 3", "Agency 3", 100, 45, 0, 0, 3)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Team B", resolver.getTheRudestTeam());

        List<Player> emptyPlayers = Collections.emptyList();
        Resolver resolverEmpty = new Resolver(emptyPlayers);
        assertEquals("Команд не найдено", resolverEmpty.getTheRudestTeam());
    }
}
