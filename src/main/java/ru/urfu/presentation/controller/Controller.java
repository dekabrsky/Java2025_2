package ru.urfu.presentation.controller;

import com.google.inject.Inject;
import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.useCase.GetMaxDefenderGoalsUseCase;
import ru.urfu.domain.useCase.LoadPlayersUseCase;
import ru.urfu.domain.useCase.GetPlayersWithoutAgencyUseCase;
import ru.urfu.presentation.view.ViewInterface;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ViewInterface view;
    private final Scanner scanner;

    private final LoadPlayersUseCase getPlayersUseCase;
    private final GetMaxDefenderGoalsUseCase getMaxDefenderGoalsUseCase;
    private final GetPlayersWithoutAgencyUseCase getPlayersWithoutAgencyUseCase;

    private List<Player> players;

    @Inject
    public Controller(
            ViewInterface view,
            LoadPlayersUseCase getPlayersUseCase,
            GetMaxDefenderGoalsUseCase getMaxDefenderGoalsUseCase,
            GetPlayersWithoutAgencyUseCase getPlayersWithoutAgencyUseCase
    ) {
        this.view = view;
        this.getPlayersUseCase = getPlayersUseCase;
        this.getMaxDefenderGoalsUseCase = getMaxDefenderGoalsUseCase;
        this.getPlayersWithoutAgencyUseCase = getPlayersWithoutAgencyUseCase;
        scanner = new Scanner(System.in);
    }

    public void start() {
        view.showWelcome();
        mainCycle : while (true) {
            view.showEnterCommand();

            switch (scanner.nextLine()) {
                case "LOAD" -> onLoadFile();
                case "1" -> onTask1Requested();
                case "2" -> onTask2Requested();
                case "CHART" -> onChartRequested();
                case "EXIT" -> {
                    break mainCycle;
                }
                default -> view.showCommandError();
            }
        }
    }

    private void onLoadFile() {
        view.showSelectFile();
        players = getPlayersUseCase.execute(scanner.nextLine());
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
