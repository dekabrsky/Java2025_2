package ru.urfu.resolver;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.urfu.model.Player;
import ru.urfu.model.Position;


public class Resolver implements IResolver {

    private final List<Player> players;

    public Resolver(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getCountWithoutAgency() {
    //    int count = 0;
    //    for (Player player : players) {
    //     if (player.agency().isEmpty()) {
    //         count++;
    //     }
    //    }
    //    return count;
        return (int) players.stream()
                .filter(player -> player.agency().isEmpty())
                .count();
    }     

    @Override
    public String getDefenderWithMaxGoalsName() {
    return players.stream()
        .filter(player -> player.position() == Position.DEFENDER)
        .max(Comparator.comparingInt(Player::goals))
        .map(Player::name)
        .orElse("Нет защитников");
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players.stream()
            .filter(player -> player.position() == Position.DEFENDER)
            .mapToInt(Player::goals)
            .max()
            .orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        return players.stream()
                .filter(player -> player.nation().equals("Germany"))
                .max(Comparator.comparing(Player::cost))
                .map(Player::position)
                .map(Position::getTitleRu)
                .orElse("Немецких игроков не найдено");
    }

    @Override
    public String getTheRudestTeam() {
        return players.stream()
                .collect(Collectors.groupingBy(Player::team, Collectors.averagingDouble(Player::redCards)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Нет команд");
    }

}
