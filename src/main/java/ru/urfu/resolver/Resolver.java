package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.graphics.Graphic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Resolver implements IResolver {
    private final List<Player> players;

    public Resolver(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getCountWithoutAgency() {

        return (int)players.stream().filter(player -> player.agency().isEmpty()).
                count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players.stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals).max().orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        int maxCost = players.stream()
                .filter(player -> player.nation().equals("Germany"))
                .mapToInt(Player::cost).max().orElse(0);
        Position position = players.stream()
                .filter(player -> player.nation().equals("Germany"))
                .filter(player -> player.cost() == maxCost)
                .findAny().get().position();
        if (position ==  Position.DEFENDER) {
            return "Защитник";
        }
        else if (position == Position.GOALKEEPER) {
            return "Вратарь";
        }
        else if (position == Position.MIDFIELD) {
            return "Полузащитник";
        }
        else {
            return "Нападающий";
        }
    }

    @Override
    public String getTheRudestTeam() {
        Map<String, List<Player>> grouped = players.stream()
                .collect(Collectors.groupingBy(Player::team));

        Map.Entry<String, List<Player>> maxEntry = grouped.entrySet().stream()
                .max(Comparator.comparingDouble(entry ->
                        entry.getValue().stream()
                                .mapToInt(Player::redCards)
                                .average()
                                .orElse(0)
                ))
                .orElse(null);

        return maxEntry != null ? maxEntry.getKey() : "";
    }

    public void renderGraphic() {
        Graphic.showGraphic(players);
    }
}
