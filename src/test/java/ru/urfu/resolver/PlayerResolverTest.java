package ru.urfu.resolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.urfu.io.CsvPlayerParser;
import ru.urfu.io.IParser;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerResolverTest {

    private final List<Player> data;
    private final PlayerResolver playerResolver;

    public PlayerResolverTest() throws IOException {

        IParser<Player> parser = new CsvPlayerParser();

        String path = "src/test/resources/testData.csv";
        this.data = parser.parse(path);

        this.playerResolver = new PlayerResolver(data);
    }

    @Test
    @DisplayName("testCountWithoutAgency")
    public void testCountWithoutAgency() {
        int cnt = 0;

        for (Player p : data) {
            if (p.agency() == null) {
                ++cnt;
            }
        }

        assertEquals(cnt, playerResolver.getCountWithoutAgency());
    }

    @Test
    @DisplayName("testGetMaxDefenderGoalsCount")
    public void testGetMaxDefenderGoalsCount() {

        int maxDefenderGoalCount = 0;

        for (Player p : data) {
            if (p.position() != Position.DEFENDER) {
                continue;
            }
            maxDefenderGoalCount = Math.max(maxDefenderGoalCount, p.goals());
        }

        assertEquals(maxDefenderGoalCount, playerResolver.getMaxDefenderGoalsCount());
    }

    @Test
    @DisplayName("testGetTheExpensiveGermanPlayerPosition")
    public void testGetTheExpensiveGermanPlayerPosition() {

        int maxGermanTransferCost = 0;
        Position costestGermanPlayerPosition = null;

        for (Player p : data) {
            if (!p.nationality().equals("Germany")) {
                continue;
            }
            if (maxGermanTransferCost < p.transferCost()) {
                maxGermanTransferCost = p.transferCost();
                costestGermanPlayerPosition = p.position();
            }
        }

        assertNotNull(costestGermanPlayerPosition);
        assertEquals(costestGermanPlayerPosition.getRussianName(), playerResolver.getTheExpensiveGermanPlayerPosition());
    }

    @Test
    @DisplayName("testGetTheRudestTeam")
    public void testGetTheRudestTeam() {

        Map<String, Integer> teamRedCards = new HashMap<>();
        String rudestTeam = null;
        int maxTeamRedCards = 0;

        for (Player p : data) {
            int teamRedCardsCount;
            teamRedCardsCount = teamRedCards.getOrDefault(p.team(), 0);

            if (maxTeamRedCards < teamRedCardsCount + p.redCards()) {
                rudestTeam = p.team();
                maxTeamRedCards = teamRedCardsCount + p.redCards();
            }

            teamRedCards.put(p.team(), teamRedCardsCount + p.redCards());
        }

        assertNotNull(rudestTeam);
        assertEquals(rudestTeam, playerResolver.getTheRudestTeam());
    }
}
