package main.java.ru.urfu.resolver;

import main.java.ru.urfu.model.Player;
import main.java.ru.urfu.model.Position;

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
        return (int) players.stream()
                .filter(p -> p.agency().isEmpty())
                .count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players.stream()
                .filter(p -> p.position().equals(Position.DEFENDER))
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        return players.stream()
                .filter(p -> p.nationality().equals("Germany"))
                .max(Comparator.comparing(Player::transferCost))
                .map(Player::position)
                .map(Position::getTitleRu)
                .orElse("Нет немецких игроков");
    }

    @Override
    public String getTheRudestTeam() {
        return players.stream()
                .collect(Collectors.groupingBy(Player::team, Collectors.averagingDouble(Player::redCards)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Команд не найдено");
    }
}
