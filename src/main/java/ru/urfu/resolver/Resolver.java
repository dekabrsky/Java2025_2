package ru.urfu.resolver;

import ru.urfu.model.*;

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
        return (int)players.stream()
            .filter(p -> p.agency().isEmpty())
            .count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players.stream()
            .filter(p -> p.position().equals(Position.DEFENDER))
            .max(Comparator.comparing(p -> p.goals()))
            .map(p -> p.goals())
            .orElse(-1);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        return players.stream()
            .filter(p -> p.nationality().equals("Germany"))
            .max(Comparator.comparing(p -> p.cost()))
            .map(p -> {
                switch (p.position()) {
                    case (Position.GOALKEEPER):
                        return "Вратарь";
                    case (Position.DEFENDER):
                        return "Защитник";
                    case (Position.MIDFIELD):
                        return "Полузащитник";
                    default:
                        return "Нападающий";
                }
            })
            .orElse("Ошибка: немецких игроков не найдено");
    }

    @Override
    public String getTheRudestTeam() {
        var teams = players
            .stream()
            .collect(Collectors.groupingBy(p -> p.team()));

        var maxEntry = teams
            .entrySet()
            .stream()
            .max(Comparator.comparing(entry ->
                entry
                    .getValue()
                    .stream()
                    .mapToDouble(Player::rCards)
                    .average()
                    .orElse(0)
                ))
            .orElse(null);

        if (maxEntry == null) {
            return "Ошибка: команд не найдено";
        }
        return maxEntry.getKey();
    }
}
