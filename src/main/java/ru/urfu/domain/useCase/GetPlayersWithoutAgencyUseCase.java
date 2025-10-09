package ru.urfu.domain.useCase;

import ru.urfu.domain.model.Player;
import java.util.List;

public class GetPlayersWithoutAgencyUseCase {
    public static int execute(List<Player> players) {
       return players.stream()
                .filter(player -> player.agency() == null || player.agency().isEmpty())
                .mapToInt(player -> 1)
                .sum();
    }
}
