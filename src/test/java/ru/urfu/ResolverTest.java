package ru.urfu;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.resolver.Resolver;

public class ResolverTest {

    @Test
    void getCountWithoutAgencyTest() {
        List<Player> players = List.of(
            new Player("Iva Streich", Position.MIDFIELD, "D'Amore LLC", 19, "Colombia", 75012006, "Nevada whales", 7),
            new Player("Miss Buck Bradtke", Position.DEFENDER, "D'Amore LLC", 26, "Brazil", 33850831, "Nevada whales", 9),
            new Player("Everette Kovacek MD", Position.DEFENDER, "Walker and Sons", 11, "Palau", 64396675, "Wisconsin prophets", 5),
            new Player("Ms. Adolph Hartmann", Position.FORWARD, "", 10, "Croatia", 52944545, "North Carolina dolphins", 8),
            new Player("Kenyetta Emard", Position.GOALKEEPER, "D'Amore LLC", 24, "Ecuador", 94715977, "Mississippi witches", 7),
            new Player("Mrs. Celina Abshire", Position.GOALKEEPER, "D'Amore LLC", 4, "Qatar", 41436703, "Massachusetts enchanters", 0),
            new Player("Dr. Royal King", Position.GOALKEEPER, "Walker and Sons", 6, "Ecuador", 50056961, "Florida tigers", 4),
            new Player("Terry Hegmann", Position.DEFENDER, "", 11, "Germany", 7294362, "Wisconsin prophets", 8)
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
            new Player("Iva Streich", Position.MIDFIELD, "D'Amore LLC", 19, "Colombia", 75012006, "Nevada whales", 7),
            new Player("Miss Buck Bradtke", Position.DEFENDER, "D'Amore LLC", 26, "Brazil", 33850831, "Nevada whales", 9),
            new Player("Everette Kovacek MD", Position.DEFENDER, "Walker and Sons", 11, "Palau", 64396675, "Wisconsin prophets", 5),
            new Player("Ms. Adolph Hartmann", Position.FORWARD, "", 10, "Croatia", 52944545, "North Carolina dolphins", 8),
            new Player("Kenyetta Emard", Position.GOALKEEPER, "D'Amore LLC", 24, "Ecuador", 94715977, "Mississippi witches", 7),
            new Player("Mrs. Celina Abshire", Position.GOALKEEPER, "D'Amore LLC", 4, "Qatar", 41436703, "Massachusetts enchanters", 0),
            new Player("Dr. Royal King", Position.GOALKEEPER, "Walker and Sons", 6, "Ecuador", 50056961, "Florida tigers", 4)
        );
        Resolver resolver = new Resolver(players);
        assertEquals(26, resolver.getMaxDefenderGoalsCount());

        List<Player> emptyPlayers = Collections.emptyList();
        Resolver resolverEmpty = new Resolver(emptyPlayers);
        assertEquals(0, resolverEmpty.getMaxDefenderGoalsCount());
    }

    @Test
    void getTheExpensiveGermanPlayerPositionTest() {
        List<Player> players = List.of(
            new Player("Iva Streich", Position.MIDFIELD, "D'Amore LLC", 19, "Colombia", 75012006, "Nevada whales", 7),
            new Player("Miss Buck Bradtke", Position.DEFENDER, "D'Amore LLC", 26, "Brazil", 33850831, "Nevada whales", 9),
            new Player("Everette Kovacek MD", Position.DEFENDER, "Walker and Sons", 11, "Palau", 64396675, "Wisconsin prophets", 5),
            new Player("Ms. Adolph Hartmann", Position.FORWARD, "", 10, "Croatia", 52944545, "North Carolina dolphins", 8),
            new Player("Kenyetta Emard", Position.GOALKEEPER, "D'Amore LLC", 24, "Ecuador", 94715977, "Mississippi witches", 7),
            new Player("Mrs. Celina Abshire", Position.GOALKEEPER, "D'Amore LLC", 4, "Qatar", 41436703, "Massachusetts enchanters", 0),
            new Player("Dr. Royal King", Position.GOALKEEPER, "Walker and Sons", 6, "Ecuador", 50056961, "Florida tigers", 4),
            new Player("Lorinda Labadie", Position.MIDFIELD, "Price-Hirthe", 13, "Germany", 33413181, "Utah nemesis", 4),
            new Player("Ms. Erwin Hoeger", Position.DEFENDER, "Fadel LLC", 28, "Qatar", 71207725, "Minnesota giants", 1),
            new Player("Terry Hegmann", Position.DEFENDER, "Graham-Powlowski", 11, "Germany", 7294362, "Wisconsin prophets", 8)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Полузащитник", resolver.getTheExpensiveGermanPlayerPosition());

        List<Player> noGermanPlayers = List.of(
                new Player("Iva Streich", Position.MIDFIELD, "D'Amore LLC", 19, "Colombia", 75012006, "Nevada whales", 7),
                new Player("Miss Buck Bradtke", Position.DEFENDER, "D'Amore LLC", 26, "Brazil", 33850831, "Nevada whales", 9),
                new Player("Everette Kovacek MD", Position.DEFENDER, "Walker and Sons", 11, "Palau", 64396675, "Wisconsin prophets", 5),
                new Player("Ms. Adolph Hartmann", Position.FORWARD, "", 10, "Croatia", 52944545, "North Carolina dolphins", 8),
                new Player("Kenyetta Emard", Position.GOALKEEPER, "D'Amore LLC", 24, "Ecuador", 94715977, "Mississippi witches", 7),
                new Player("Mrs. Celina Abshire", Position.GOALKEEPER, "D'Amore LLC", 4, "Qatar", 41436703, "Massachusetts enchanters", 0)
        );
        Resolver resolverNoGerman = new Resolver(noGermanPlayers);
        assertEquals("Немецких игроков не найдено", resolverNoGerman.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void getTheRudestTeamTest() {
        List<Player> players = List.of(
            new Player("Iva Streich", Position.MIDFIELD, "D'Amore LLC", 19, "Colombia", 75012006, "Nevada whales", 7),
            new Player("Miss Buck Bradtke", Position.DEFENDER, "D'Amore LLC", 26, "Brazil", 33850831, "Nevada whales", 9),
            new Player("Everette Kovacek MD", Position.DEFENDER, "Walker and Sons", 11, "Palau", 64396675, "Wisconsin prophets", 5),
            new Player("Ms. Adolph Hartmann", Position.FORWARD, "", 10, "Croatia", 52944545, "North Carolina dolphins", 8),
            new Player("Kenyetta Emard", Position.GOALKEEPER, "D'Amore LLC", 24, "Ecuador", 94715977, "Mississippi witches", 7),
            new Player("Mrs. Celina Abshire", Position.GOALKEEPER, "D'Amore LLC", 4, "Qatar", 41436703, "Massachusetts enchanters", 0),
            new Player("Dr. Royal King", Position.GOALKEEPER, "Walker and Sons", 6, "Ecuador", 50056961, "Florida tigers", 4),
            new Player("Lorinda Labadie", Position.MIDFIELD, "Price-Hirthe", 13, "Germany", 33413181, "Utah nemesis", 4),
            new Player("Ms. Erwin Hoeger", Position.DEFENDER, "Fadel LLC", 28, "Qatar", 71207725, "Minnesota giants", 1),
            new Player("Terry Hegmann", Position.DEFENDER, "Graham-Powlowski", 11, "Germany", 7294362, "Wisconsin prophets", 8)
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Nevada whales", resolver.getTheRudestTeam());

        List<Player> emptyPlayers = Collections.emptyList();
        Resolver resolverEmpty = new Resolver(emptyPlayers);
        assertEquals("Нет команд", resolverEmpty.getTheRudestTeam());
    }
}