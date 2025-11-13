package ru.urfu.presentation.presenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import ru.urfu.domain.useCase.GetMaxDefenderGoalsUseCase;
import ru.urfu.domain.useCase.GetPlayersFromDbUseCase;
import ru.urfu.domain.useCase.LoadPlayersUseCase;
import ru.urfu.domain.useCase.GetPlayersWithoutAgencyUseCase;
import ru.urfu.presentation.view.ViewInterface;

public class PresenterFactory {
    private final Provider<LoadPlayersUseCase> getPlayersUseCaseProvider;
    private final Provider<GetMaxDefenderGoalsUseCase> getMaxDefenderGoalsUseCaseProvider;
    private final Provider<GetPlayersWithoutAgencyUseCase> getPlayersWithoutAgencyUseCaseProvider;
    private final Provider<GetPlayersFromDbUseCase> getPlayersFromDbUseCaseProvider;

    @Inject
    public PresenterFactory(
            Provider<LoadPlayersUseCase> getPlayersUseCaseProvider,
            Provider<GetMaxDefenderGoalsUseCase> getMaxDefenderGoalsUseCaseProvider,
            Provider<GetPlayersWithoutAgencyUseCase> getPlayersWithoutAgencyUseCaseProvider,
            Provider<GetPlayersFromDbUseCase> getPlayersFromDbUseCaseProvider
    ) {
        this.getPlayersUseCaseProvider = getPlayersUseCaseProvider;
        this.getMaxDefenderGoalsUseCaseProvider = getMaxDefenderGoalsUseCaseProvider;
        this.getPlayersWithoutAgencyUseCaseProvider = getPlayersWithoutAgencyUseCaseProvider;
        this.getPlayersFromDbUseCaseProvider = getPlayersFromDbUseCaseProvider;
    }

    public Presenter create(ViewInterface view) {
        return new Presenter(
                view,
                getPlayersUseCaseProvider.get(),
                getMaxDefenderGoalsUseCaseProvider.get(),
                getPlayersWithoutAgencyUseCaseProvider.get(),
                getPlayersFromDbUseCaseProvider.get()
        );
    }
}
