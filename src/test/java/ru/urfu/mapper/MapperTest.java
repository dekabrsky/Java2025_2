package ru.urfu.mapper;

import org.junit.jupiter.api.Test;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void playersByPositions() {
        var players = List.of(
                new Player("A", Position.DEFENDER, "", 0, "", 0, 0, ""),
                new Player("B", Position.FORWARD, "", 0, "", 0, 0, ""),
                new Player("C", Position.MIDFIELD, "", 0, "", 0, 0, ""),
                new Player("D", Position.GOALKEEPER, "", 0, "", 0, 0, ""),
                new Player("E", Position.FORWARD, "", 0, "", 0, 0, ""),
                new Player("F", Position.MIDFIELD, "", 0, "", 0, 0, ""),
                new Player("G", Position.GOALKEEPER, "", 0, "", 0, 0, ""),
                new Player("G", Position.MIDFIELD, "", 0, "", 0, 0, ""),
                new Player("H", Position.GOALKEEPER, "", 0, "", 0, 0, ""),
                new Player("I", Position.GOALKEEPER, "", 0, "", 0, 0, "")
        );
        var playersMap = Mapper.playersByPositions(players);
        assertEquals(4, playersMap.size());
        assertEquals(1, playersMap.get(Position.DEFENDER.inRussian()));
        assertEquals(2, playersMap.get(Position.FORWARD.inRussian()));
        assertEquals(3, playersMap.get(Position.MIDFIELD.inRussian()));
        assertEquals(4, playersMap.get(Position.GOALKEEPER.inRussian()));
    }
}