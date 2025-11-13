package ru.urfu.domain.useCase;

import com.google.inject.Inject;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

public class LoadPlayersUseCase {
    private final IPlayersRepository repository;

    @Inject
    public LoadPlayersUseCase(IPlayersRepository repository) {
        this.repository = repository;
    }

    public List<Player> execute(String link) {
        return repository.loadPlayers(link);
    }
}
