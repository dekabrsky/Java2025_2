package ru.urfu.resolver;


import ru.urfu.drawer.ChartData;
import ru.urfu.model.Player;
import ru.urfu.model.Role;

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
                .filter(player -> player.position() == Role.DEFENDER)
                .mapToInt(Player::goals).max().orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        int maxCost = players.stream()
                .filter(player -> player.nation().equals("Germany"))
                .mapToInt(Player::cost).max().orElse(0);
        Role position = players.stream()
                .filter(player -> player.nation().equals("Germany"))
                .filter(player -> player.cost() == maxCost)
                .findAny().get().position();
        switch (position) {
            case FORWARD -> {
                return "Нападающий";
            }
            case DEFENDER -> {
                return "Защитник";
            }
            case GOALKEEPER -> {
                return "Вратарь";
            }
            case MIDFIELD -> {
                return "Полузащитник";
            }
        }
        return "Error";
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

    public List<ChartData> getTopNTeams() {
        Map<String, Integer> team_sum = players.stream().collect(Collectors.groupingBy(Player::team,Collectors.summingInt(Player::cost)));
        List<ChartData> data = team_sum.entrySet().stream()
                .map(entry -> new ChartData(entry.getKey(),entry.getValue()))
                .toList();

        return data.stream().sorted((c1,c2) -> c2.sum().compareTo(c1.sum())).limit(10).collect(Collectors.toList());
    }
}