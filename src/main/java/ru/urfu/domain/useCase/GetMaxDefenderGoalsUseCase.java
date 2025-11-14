package ru.urfu.domain.useCase;

import com.google.inject.Inject;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;
import ru.urfu.domain.repository.IPlayersRepository;

public class GetMaxDefenderGoalsUseCase {
    private final IPlayersRepository repository;

    @Inject
    public GetMaxDefenderGoalsUseCase(IPlayersRepository repository) {
        this.repository = repository;
    }

    public int execute() {
        return repository.getCachedPlayers().stream()
                .filter(player -> player.position() == Position.FORWARD)
                .mapToInt(Player::goals)
                .max()
                .orElse(0);
    }
}
