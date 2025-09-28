package main.java.ru.urfu.mapper;

import main.java.ru.urfu.model.Player;
import main.java.ru.urfu.model.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapper {

    private final List<Player> players;

    public Mapper(List<Player> players) {
        this.players = players;
    }

    public Map<Position, Long> getPlayersByPosition() {
        return players.stream()
                .collect(Collectors.groupingBy(Player::position, Collectors.counting()));
    }
}
