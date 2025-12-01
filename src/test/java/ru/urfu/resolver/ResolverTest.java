package ru.urfu.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.parser.CsvParser;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResolverTest {
    private static final CsvParser mockParser = mock(CsvParser.class);

    @Test
    public void testOneDefender() {
        when(mockParser.parseCsvToList())
                .thenReturn(List.of(new Player("Иванов Иван", Position.DEFENDER, "", 10)));

        var resolver = new Resolver(mockParser);

        assertEquals(10, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    public void testNoPlayers() {
        when(mockParser.parseCsvToList())
                .thenReturn(List.of());

        var resolver = new Resolver(mockParser);

        assertEquals(0, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    public void testNoDefender() {
        when(mockParser.parseCsvToList())
                .thenReturn(List.of(new Player("Иванов Иван", Position.FORWARD, "", 10)));

        var resolver = new Resolver(mockParser);

        assertEquals(0, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testGetCountWithoutAgency_ZeroPlayers() {
        when(mockParser.parseCsvToList()).thenReturn(List.of());

        var resolver = new Resolver(mockParser);
        int count = resolver.getCountWithoutAgency();

        assertEquals(0, count);
    }


    @Test
    void testGetCountWithoutAgency_AllHaveAgency() {
        List<Player> players = List.of(
                new Player("Иван", Position.FORWARD, "Агентство1", 1),
                new Player("Петр", Position.DEFENDER, "Агентство2", 2)
        );
        when(mockParser.parseCsvToList()).thenReturn(players);

        var resolver = new Resolver(mockParser);
        int count = resolver.getCountWithoutAgency();

        assertEquals(0, count);
    }

    @Test
    void testGetCountWithoutAgency_SomeWithoutAgency() {
        List<Player> players = List.of(
                new Player("Иван", Position.FORWARD, null, 1),
                new Player("Петр", Position.DEFENDER, "", 2),
                new Player("Сергей", Position.MIDFIELD, "Агентство", 3)
        );
        when(mockParser.parseCsvToList()).thenReturn(players);

        var resolver = new Resolver(mockParser);
        int count = resolver.getCountWithoutAgency();

        assertEquals(2, count); // Иван (null) + Петр (пустая строка)
    }

    @Test
    void testGetCountWithoutAgency_AllWithoutAgency() {
        List<Player> players = List.of(
                new Player("Иван", Position.FORWARD, null, 1),
                new Player("Петр", Position.DEFENDER, "", 2)
        );
        when(mockParser.parseCsvToList()).thenReturn(players);

        var resolver = new Resolver(mockParser);
        int count = resolver.getCountWithoutAgency();

        assertEquals(2, count);
    }

    @Test
    void testGetCountWithoutAgency_MixedNullAndEmpty() {
        List<Player> players = List.of(
                new Player("Иван", Position.FORWARD, null, 1),
                new Player("Петр", Position.DEFENDER, "", 2),
                new Player("Сергей", Position.MIDFIELD, "   ", 3), // пробелы — не пусто!
                new Player("Анна", Position.FORWARD, "Агентство", 4)
        );
        when(mockParser.parseCsvToList()).thenReturn(players);

        var resolver = new Resolver(mockParser);
        int count = resolver.getCountWithoutAgency();

        assertEquals(2, count);
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testGetTeams(List<Player> players, Set<String> expected) {
        when(mockParser.parseCsvToList()).thenReturn(players);

        var resolver = new Resolver(mockParser);
        Set<String> result = resolver.getTeams();

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                // Сценарий 1: пустой список игроков
                Arguments.of(
                        List.of(),
                        Set.of()
                ),

                // Сценарий 2: один игрок — одна команда
                Arguments.of(
                        List.of(new Player("Иван", Position.FORWARD, "Barcelona", 1)),
                        Set.of("Barcelona")
                ),

                // Сценарий 3: несколько игроков одной команды
                Arguments.of(
                        List.of(
                                new Player("Иван", Position.FORWARD, "Barcelona", 1),
                                new Player("Петр", Position.DEFENDER, "Barcelona", 2)
                        ),
                        Set.of("Barcelona")
                ),

                // Сценарий 4: игроки из разных команд
                Arguments.of(
                        List.of(
                                new Player("Иван", Position.FORWARD, "Barcelona", 1),
                                new Player("Петр", Position.DEFENDER, "Real Madrid", 2),
                                new Player("Сергей", Position.MIDFIELD, "Barcelona", 3)
                        ),
                        Set.of("Barcelona", "Real Madrid")
                ),

                // Сценарий 5: один игрок без агентства (null)
                Arguments.of(
                        List.of(
                                new Player("Иван", Position.FORWARD, null, 1)
                        ),
                        Set.of()
                ),

                // Сценарий 6: с null и пустой строкой
                Arguments.of(
                        List.of(
                                new Player("Иван", Position.FORWARD, null, 1),
                                new Player("Петр", Position.DEFENDER, "", 2),
                                new Player("Сергей", Position.MIDFIELD, "Barcelona", 3)
                        ),
                        Set.of("Barcelona")
                )
        );
    }
}