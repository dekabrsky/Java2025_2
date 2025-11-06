package ru.urfu.presentation.presenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import ru.urfu.domain.useCase.GetMaxDefenderGoalsUseCase;
import ru.urfu.domain.useCase.GetPlayersUseCase;
import ru.urfu.domain.useCase.GetPlayersWithoutAgencyUseCase;
import ru.urfu.presentation.view.ViewInterface;

public class PresenterFactory {
    private final Provider<GetPlayersUseCase> getPlayersUseCaseProvider;
    private final Provider<GetMaxDefenderGoalsUseCase> getMaxDefenderGoalsUseCaseProvider;
    private final Provider<GetPlayersWithoutAgencyUseCase> getPlayersWithoutAgencyUseCaseProvider;

    @Inject
    public PresenterFactory(
            Provider<GetPlayersUseCase> getPlayersUseCaseProvider,
            Provider<GetMaxDefenderGoalsUseCase> getMaxDefenderGoalsUseCaseProvider,
            Provider<GetPlayersWithoutAgencyUseCase> getPlayersWithoutAgencyUseCaseProvider
    ) {
        this.getPlayersUseCaseProvider = getPlayersUseCaseProvider;
        this.getMaxDefenderGoalsUseCaseProvider = getMaxDefenderGoalsUseCaseProvider;
        this.getPlayersWithoutAgencyUseCaseProvider = getPlayersWithoutAgencyUseCaseProvider;
    }

    public Presenter create(ViewInterface view) {
        return new Presenter(
                view,
                getPlayersUseCaseProvider.get(),
                getMaxDefenderGoalsUseCaseProvider.get(),
                getPlayersWithoutAgencyUseCaseProvider.get()
        );
    }
}
