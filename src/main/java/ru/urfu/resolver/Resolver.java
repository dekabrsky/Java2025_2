package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Resolver implements IResolver {
    private final List<Player> players;

    public Resolver(List<Player> players) {
        this.players = players;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public int getCountWithoutAgency() {
        return (int) players.stream()
                .filter(player -> player.agency() == null || player.agency().isEmpty())
                .count();
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
        return "";
    }

    @Override
    public Map<String, String> getPlayersByPosition() {
        return Map.of();
    }

    @Override
    public Set<String> getTeams() {
        return players.stream()
                .map(Player::agency)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> getTop5TeamsByGoalsCount() {
        return Map.of();
    }

    @Override
    public String getAgencyWithMinPlayersCount() {
        return "";
    }

    @Override
    public String getTheRudestTeam() {
        return "";
    }
}
