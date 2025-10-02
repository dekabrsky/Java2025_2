package ru.urfu.resolver;

import org.junit.jupiter.api.Test;
import ru.urfu.model.*;
import ru.urfu.parser.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResolverTest {

    @Test
    void testGetCountWithoutAgency() {
        List<Player> players = List.of(
            CSVParser.parsePlayerRow("Rory Koss;Utah nemesis;New York;MIDFIELD;Ghana;Jakubowski and Sons;4483194;20;13;11;9;2"),
            CSVParser.parsePlayerRow("Carson Bradtke;Florida tigers;Washington;FORWARD;Colombia;;61663205;8;24;28;0;1"),
            CSVParser.parsePlayerRow("Micha Koch;New Mexico ogres;Georgia;GOALKEEPER;Croatia;;32216127;15;20;0;7;1")
        );
        Resolver resolver = new Resolver(players);
        assertEquals(2, resolver.getCountWithoutAgency());
    }

    @Test
    void testMaxDefenderGoals() {
        List<Player> players = List.of(
            CSVParser.parsePlayerRow("Rory Koss;Utah nemesis;New York;MIDFIELD;Ghana;Jakubowski and Sons;4483194;20;13;11;9;2"),
            CSVParser.parsePlayerRow("Carson Bradtke;Florida tigers;Washington;FORWARD;Colombia;;61663205;8;24;28;0;1"),
            CSVParser.parsePlayerRow("Eusebio Beatty IV;New Mexico ogres;Georgia;DEFENDER;Ecuador;D'Amore LLC;17429288;0;10;6;1;4"),
            CSVParser.parsePlayerRow("Steven O'Conner DDS;Nevada whales;South Carolina;DEFENDER;Ghana;Bogisich-Rempel;32437027;26;15;16;1;6")
        );
        Resolver resolver = new Resolver(players);
        assertEquals(15, resolver.getMaxDefenderGoalsCount());
    }

    @Test
    void testExpensiveGermanPosition() {
        List<Player> players = List.of(
            CSVParser.parsePlayerRow("Rory Koss;Utah nemesis;New York;MIDFIELD;Ghana;Jakubowski and Sons;4483194;20;13;11;9;2"),
            CSVParser.parsePlayerRow("Carson Bradtke;Florida tigers;Washington;FORWARD;Colombia;;61663205;8;24;28;0;1"),
            CSVParser.parsePlayerRow("Eusebio Beatty IV;New Mexico ogres;Georgia;DEFENDER;Ecuador;D'Amore LLC;17429288;0;10;6;1;4"),
            CSVParser.parsePlayerRow("Steven O'Conner DDS;Nevada whales;South Carolina;DEFENDER;Ghana;Bogisich-Rempel;32437027;26;15;16;1;6"),
            CSVParser.parsePlayerRow("Terry Hegmann;Wisconsin prophets;Vermont;DEFENDER;Germany;Graham-Powlowski;7294362;12;11;28;1;8"),
            CSVParser.parsePlayerRow("Beckie Lindgren;Nevada whales;South Carolina;MIDFIELD;Germany;Walker and Sons;11576194;25;16;15;0;7")
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Полузащитник", resolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    void testRudestTeam() {
        List<Player> players = List.of(
            CSVParser.parsePlayerRow("Iva Streich;Nevada whales;South Carolina;MIDFIELD;Colombia;D'Amore LLC;75012006;22;19;6;3;7"),
            CSVParser.parsePlayerRow("Miss Buck Bradtke;Nevada whales;South Carolina;DEFENDER;Brazil;D'Amore LLC;33850831;20;26;18;6;9"),
            CSVParser.parsePlayerRow("Everette Kovacek MD;Wisconsin prophets;Vermont;DEFENDER;Palau;Walker and Sons;64396675;27;11;25;0;5"),
            CSVParser.parsePlayerRow("Ms. Adolph Hartmann;North Carolina dolphins;Tennessee;FORWARD;Croatia;;52944545;27;10;5;5;8"),
            CSVParser.parsePlayerRow("Kenyetta Emard;Mississippi witches;Virginia;GOALKEEPER;Ecuador;D'Amore LLC;94715977;10;24;18;0;7"),
            CSVParser.parsePlayerRow("Mrs. Celina Abshire;Massachusetts enchanters;Connecticut;GOALKEEPER;Qatar;D'Amore LLC;41436703;27;4;1;0;0"),
            CSVParser.parsePlayerRow("Dr. Royal King;Florida tigers;Washington;GOALKEEPER;Ecuador;Walker and Sons;50056961;21;6;3;5;4"),
            CSVParser.parsePlayerRow("Rory Koss;Utah nemesis;New York;MIDFIELD;Ghana;Jakubowski and Sons;4483194;20;13;11;9;2"),
            CSVParser.parsePlayerRow("Lorinda Labadie;Utah nemesis;New York;MIDFIELD;Germany;Price-Hirthe;33413181;22;13;21;0;4"),
            CSVParser.parsePlayerRow("Ms. Erwin Hoeger;Minnesota giants;Washington;DEFENDER;Qatar;Fadel LLC;71207725;26;28;15;7;1"),
            CSVParser.parsePlayerRow("Terry Hegmann;Wisconsin prophets;Vermont;DEFENDER;Germany;Graham-Powlowski;7294362;12;11;28;1;8")
        );
        Resolver resolver = new Resolver(players);
        assertEquals("Nevada whales", resolver.getTheRudestTeam());
    }
}
