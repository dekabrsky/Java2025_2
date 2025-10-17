package ru.urfu.domain.useCase;

import com.google.inject.Inject;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

public class GetMaxDefenderGoalsUseCase {
    private final IPlayersRepository repository;

    @Inject
    public GetMaxDefenderGoalsUseCase(IPlayersRepository repository) {
        this.repository = repository;
    }

    public int execute() {
        return repository.getPlayers().stream()
                .filter(player -> player.position() == Position.DEFENDER)
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }
}
