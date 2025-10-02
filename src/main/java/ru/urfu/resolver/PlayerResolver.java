package ru.urfu.resolver;

import ru.urfu.exception.ValueNotFoundException;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerResolver implements IResolver {

    private final List<Player> playerList;

    public PlayerResolver(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int getPlayersCount() {
        return playerList.size();
    }

    @Override
    public int getCountWithoutAgency() {
        return (int) playerList.stream().filter(player -> player.agency() == null).count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        OptionalInt opt = playerList.stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals)
                .max();
        if (opt.isPresent()) {
            return opt.getAsInt();
        }
        throw new ValueNotFoundException("Не найден защитник с наибольшм числом голов.");
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        String searchNationality = "Germany";
        Optional<Player> opt = playerList.stream()
                .filter(player -> player.nationality().equals(searchNationality))
                .reduce((p1, p2) -> p1.transferCost() < p2.transferCost() ? p2 : p1);

        if (opt.isPresent()) {
            return opt.get().position().getRussianName();
        }

        throw new ValueNotFoundException("Не найден самый дорогой немецкий игрок");
    }

    @Override
    public String getTheRudestTeam() {
        Map<String, Integer> teamRedCards = playerList.stream()
                .collect(
                        Collectors.groupingBy(Player::team, Collectors.summingInt(Player::redCards))
                );
        Optional<Map.Entry<String, Integer>> opt = teamRedCards.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue));

        if (opt.isPresent()) {
            return opt.get().getKey();
        }
        throw new ValueNotFoundException("Не найдена команда с наибольшим числом красных карточек.");
    }

    public Map<String, Long> getPlayersFromCountriesCount() {
        return playerList.stream()
                .collect(Collectors.groupingBy(Player::nationality, Collectors.counting()));
    }
}
