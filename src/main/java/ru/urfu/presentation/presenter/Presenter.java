package ru.urfu.presentation.presenter;

import com.google.inject.Inject;
import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.useCase.GetMaxDefenderGoalsUseCase;
import ru.urfu.domain.useCase.GetPlayersFromDbUseCase;
import ru.urfu.domain.useCase.LoadPlayersUseCase;
import ru.urfu.domain.useCase.GetPlayersWithoutAgencyUseCase;
import ru.urfu.presentation.view.ViewInterface;

import java.util.List;

public class Presenter {
    private final ViewInterface view;

    private final LoadPlayersUseCase getPlayersUseCase;
    private final GetMaxDefenderGoalsUseCase getMaxDefenderGoalsUseCase;
    private final GetPlayersWithoutAgencyUseCase getPlayersWithoutAgencyUseCase;
    private final GetPlayersFromDbUseCase getPlayersFromDbUseCase;

    private List<Player> players;
    private boolean isNeedSelectFile = false;

    @Inject
    public Presenter(
            ViewInterface view,
            LoadPlayersUseCase getPlayersUseCase,
            GetMaxDefenderGoalsUseCase getMaxDefenderGoalsUseCase,
            GetPlayersWithoutAgencyUseCase getPlayersWithoutAgencyUseCase,
            GetPlayersFromDbUseCase getPlayersFromDbUseCase
    ) {
        this.view = view;
        this.getPlayersUseCase = getPlayersUseCase;
        this.getMaxDefenderGoalsUseCase = getMaxDefenderGoalsUseCase;
        this.getPlayersWithoutAgencyUseCase = getPlayersWithoutAgencyUseCase;
        this.getPlayersFromDbUseCase = getPlayersFromDbUseCase;
        view.showWelcome();
    }

    public void onNewCommand(String command) {
        if (isNeedSelectFile) {
            onFileSelected(command);
            isNeedSelectFile = false;
            return;
        }

        switch (command) {
            case "LOAD" -> onLoadFile();
            case "DB" -> onGetFromDb();
            case "1" -> onTask1Requested();
            case "2" -> onTask2Requested();
            case "CHART" -> onChartRequested();
            default -> view.showCommandError();
        }
    }

    private void onLoadFile() {
        view.showSelectFile();
        isNeedSelectFile = true;
    }

    private void onGetFromDb() {
        players = getPlayersFromDbUseCase.execute();
    }

    private void onFileSelected(String link) {
        players = getPlayersUseCase.execute(link);
    }

    private boolean hasPlayersError() {
        if (players == null || players.isEmpty()) {
            view.showPlayersError();
            return true;
        } else {
            return false;
        }
    }

    private void onTask1Requested() {
        if (hasPlayersError()) return;
        view.showCountWithoutAgency(getPlayersWithoutAgencyUseCase.execute());
    }

    private void onTask2Requested() {
        if (hasPlayersError()) return;
        view.showMaxDefenderGoalsCount(getMaxDefenderGoalsUseCase.execute());
    }

    private void onChartRequested() {
        if (hasPlayersError()) return;
        var chartData = ChartMapper.mapDataToChart(players);
        ChartDrawer.showChart(chartData);
    }
}
