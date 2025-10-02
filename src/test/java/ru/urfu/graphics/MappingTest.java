package ru.urfu.graphics;

import ru.urfu.graphics.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.urfu.model.*;
import ru.urfu.parser.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MappingTest {
    @Test
    void testMakeMapping() {
        List<Player> players = List.of(
            CSVParser.parsePlayerRow("Randall Hilpert;North Carolina dolphins;Tennessee;DEFENDER;Ghana;Bahringer-Larson;54202579;21;2;13;8;0"),
            CSVParser.parsePlayerRow("Connie Hegmann;North Carolina dolphins;Tennessee;DEFENDER;Ecuador;Bogisich-Rempel;97729347;2;8;28;4;5"),
            CSVParser.parsePlayerRow("Leda Orn III;Nevada whales;South Carolina;MIDFIELD;Ghana;;53608367;19;5;18;2;0"),
            CSVParser.parsePlayerRow("Thomas Howell DVM;Mississippi witches;Virginia;FORWARD;Colombia;;32821305;2;27;26;2;7"),
            CSVParser.parsePlayerRow("Sherril Jacobs DDS;Massachusetts enchanters;Connecticut;MIDFIELD;Germany;Hauck Inc;39373848;20;16;21;2;1"),
            CSVParser.parsePlayerRow("Antonina Reinger DVM;Hawaii oxen;Oklahoma;FORWARD;Ghana;Walker and Sons;99974008;4;27;18;8;4"),
            CSVParser.parsePlayerRow("Lourdes Schoen;Minnesota giants;Washington;DEFENDER;Croatia;Graham-Powlowski;87664016;13;29;13;5;2"),
            CSVParser.parsePlayerRow("Lilli Pagac;North Carolina dolphins;Tennessee;FORWARD;Qatar;Jakubowski and Sons;44568969;8;16;29;3;5"),
            CSVParser.parsePlayerRow("Mitsuko Fadel;Wisconsin prophets;Vermont;FORWARD;Qatar;Price-Hirthe;47975979;4;16;0;0;6")
        );

        Map<Position, Long> playersByPosition = Graphics.makeMapping(players);

        assertEquals(3L, playersByPosition.get(Position.DEFENDER));
        assertEquals(2L, playersByPosition.get(Position.MIDFIELD));
        assertEquals(4L, playersByPosition.get(Position.FORWARD));
    }

}
