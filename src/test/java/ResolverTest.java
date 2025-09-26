import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.resolver.Resolver;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResolverTest {

    @Test
    void testGetMaxDefenderGoalsCount_WithDefenders() {
        List<Player> players = List.of(
                new Player("Иван", Position.DEFENDER, "Агентство1", 3),
                new Player("Петр", Position.FORWARD, "Агентство2", 9),
                new Player("Сергей", Position.DEFENDER, "Агентство3", 7),
                new Player("Дмитрий", Position.DEFENDER, "Агентство4", 2)
        );

        Resolver resolver = new Resolver(players);
        assertEquals(7, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testGetMaxDefenderGoalsCount_NoDefenders() {
        List<Player> players = List.of(
                new Player("Иван", Position.FORWARD, "Агентство1", 3),
                new Player("Петр", Position.MIDFIELD, "Агентство2", 5)
        );

        Resolver resolver = new Resolver(players);

        //assertThrows(NoSuchElementException.class, resolver::getMaxDefenderGoalsCount);
        assertEquals(0, resolver.getMaxDefenderGoalsCount());
    }
}