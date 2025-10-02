package ru.urfu.resolver;

import ru.urfu.model.Player;
import ru.urfu.graphics.Graphic;

import java.util.Map;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Resolver implements IResolver {
    private final List<Player> players;

    public Resolver(List<Player> players) {
        this.players = players;
    }

    private String translatePositionToRussian(String position) {
        switch (position.toUpperCase()) {
            case "GOALKEEPER": return "Вратарь";
            case "DEFENDER": return "Защитник";
            case "MIDFIELD": return "Полузащитник";
            case "FORWARD": return "Нападающий";
            default: return position;
        }
    }

    @Override
    public int getCountWithoutAgency() {
        return (int) players
            .stream()
            .filter(player ->
                player.getAgency() == null ||
                player.getAgency().isEmpty() ||
                player.getAgency().equals("-")
            )
            .count();
    }

    @Override
    public int getMaxDefenderGoalsCount() {
        return players
            .stream()
            .filter(player -> player.getPosition().equals("DEFENDER"))
            .mapToInt(Player::getGoals)
            .max()
            .orElse(0);
    }

    @Override
    public String getTheExpensiveGermanPlayerPosition() {
        Player expensiveGerman = players
            .stream()
            .filter(player -> player.getNationality().equals("Germany"))
            .max((p1, p2) -> Long.compare(p1.getTransferCost(), p2.getTransferCost()))
            .orElse(null);

        if (expensiveGerman != null) {
            return translatePositionToRussian(expensiveGerman.getPosition());
        }
        return "Не найден";
    }

    @Override
    public String getTheRudestTeam() {
        Map<String, Double> teamRedCardsAvg = players
            .stream()
            .collect(Collectors.groupingBy(
                Player::getTeam,
                Collectors.averagingDouble(Player::getRedCards)
            ));

        return teamRedCardsAvg
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Не найдена");
    }
}
