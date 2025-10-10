package ru.urfu.mapper;

import ru.urfu.model.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapper {
    public static Map<String, Long> playersByPositions(List<Player> players) {
        return players.stream()
                .collect(Collectors.groupingBy(player ->
                        player.position().inRussian(), Collectors.counting()));
    }
}
