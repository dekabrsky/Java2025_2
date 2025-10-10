package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Resolver implements IResolver {

    private List<Player> players;

    public Resolver(List<Player> players) {
        this.players = players;
    }

    public Resolver() {
    }

    @Override
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getCountWithoutAgency() {
        return Math.toIntExact(players.stream()
                .filter(player -> player.agency().isEmpty())
                .count());
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players.stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }

    final static String GERMAN = "Germany";

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        return players.stream()
                .filter(player -> player.nationality().equals(GERMAN))
                .max(Comparator.comparing(Player::transferCost))
                .map(Player::position)
                .map(Position::inRussian)
                .orElse("немецкие игроки отсутствуют");
    }

    @Override
    public String getTheRudestTeam() {
        var teams = players.stream()
                .collect(Collectors.groupingBy(Player::team));
        return teams.entrySet().stream()
                .max(Comparator.comparing(team ->
                        team.getValue().stream()
                                .mapToInt(Player::redCardsCount)
                                .average()
                                .orElse(0)
                ))
                .map(Map.Entry::getKey)
                .orElse("команды отсутствуют");
    }
}
