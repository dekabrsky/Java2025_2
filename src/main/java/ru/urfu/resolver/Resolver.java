package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Resolver implements IResolver {

    private List<Player> players = new ArrayList<>();

    public Resolver() { }

    public Resolver(List<Player> players) {
        this.players = players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getCountWithoutAgency() {
        int count = 0;
//        for (Player player : players) {
//            if (player.agency().isEmpty()) {
//                count++;
//            }
//        }
//        return count;

        return players.stream()
                .filter(player -> player.agency() == null || player.agency().isEmpty())
                .mapToInt(player -> 1)
                .sum();
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
    public String getTheRudestTeam() {
        return "";
    }
}
