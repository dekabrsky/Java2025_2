package ru.urfu.domain.useCase;

import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

public class GetMaxDefenderGoalsUseCase {
    public static int execute(List<Player> players) {
        return players.stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }
}
