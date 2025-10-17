package ru.urfu.di;

import com.google.inject.AbstractModule;
import ru.urfu.data.repository.PlayersRepository;
import ru.urfu.domain.interactor.PlayersInteractor;
import ru.urfu.domain.repository.IPlayersRepository;
import ru.urfu.domain.useCase.GetPlayersUseCase;
import ru.urfu.presentation.view.ConsoleView;
import ru.urfu.presentation.view.ViewInterface;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IPlayersRepository.class).to(PlayersRepository.class);
        bind(ViewInterface.class).to(ConsoleView.class);
    }
}
