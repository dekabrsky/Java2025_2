package ru.urfu.domain.useCase;

import com.google.inject.Inject;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

public class GetPlayersWithoutAgencyUseCase {
    private final IPlayersRepository repository;

    @Inject
    public GetPlayersWithoutAgencyUseCase(IPlayersRepository repository) {
        this.repository = repository;
    }

    public int execute() {
       return repository.getPlayers().stream()
                .filter(player -> player.agency() == null || player.agency().isEmpty())
                .mapToInt(player -> 1)
                .sum();
    }
}
