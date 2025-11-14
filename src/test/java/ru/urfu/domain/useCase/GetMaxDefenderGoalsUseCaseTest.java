package ru.urfu.domain.useCase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GetMaxDefenderGoalsUseCaseTest {
    private static final IPlayersRepository mockRepository = mock(IPlayersRepository.class);

    @Test
    public void test() {
        Mockito
                .when(mockRepository.getCachedPlayers())
                .thenReturn(
                        List.of(
                                new Player("Иванов Иван", Position.DEFENDER, "", 10)
                        )
                );

        var useCase = new GetMaxDefenderGoalsUseCase(mockRepository);

        assertEquals(10, useCase.execute());
    }
}