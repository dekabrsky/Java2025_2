package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class Resolver implements IResolver{
    private final List<Player> players;
    public Resolver(List<Player> players){
        this.players = players;
    }

    private static String getRussianPositionName(Position position) {
        return switch (position) {
            case GOALKEEPER -> "Вратарь";
            case DEFENDER -> "Защитник";
            case MIDFIELD -> "Полузащитник";
            case FORWARD -> "Нападающий";
        };
    }

    public int getCountWithoutAgency() {
        return (int) players.stream()
                .filter(player -> player.agency().isEmpty())
                .count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return  players.stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        int maxCostForGermany = players.stream()
                .filter(player -> player.nationality().equals("Germany"))
                .mapToInt(Player::transferCost)
                .max().orElse(0);
        var res = players.stream()
                .filter(player -> player.nationality().equals("Germany"))
                .filter(player -> player.transferCost() == maxCostForGermany)
                .findAny().get().position();
        return getRussianPositionName(res);
    }

    @Override
    public String getTheRudestTeam() {
        Map<String, List<Player>> grouped = players.stream().collect(Collectors.groupingBy(Player::team));

        Map.Entry<String, List<Player>> mostDeleted = grouped.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().stream()
                        .mapToInt(Player::redCard)
                        .average()
                        .orElse(0)))
                .orElse(null);
        if (mostDeleted != null) return mostDeleted.getKey();
        return "";
    }

}
