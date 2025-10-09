package ru.urfu.presentation.controller;

import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.useCase.GetMaxDefenderGoalsUseCase;
import ru.urfu.domain.useCase.GetPlayersUseCase;
import ru.urfu.domain.useCase.GetPlayersWithoutAgencyUseCase;
import ru.urfu.presentation.view.ViewInterface;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ViewInterface view;
    private final Scanner scanner;

    private final GetPlayersUseCase getPlayersUseCase;

    private List<Player> players;

    public Controller(ViewInterface view, GetPlayersUseCase getPlayersUseCase) {
        this.view = view;
        this.getPlayersUseCase = getPlayersUseCase;
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
        view.showCountWithoutAgency(GetPlayersWithoutAgencyUseCase.execute(players));
    }

    private void onTask2Requested() {
        if (hasPlayersError()) return;
        view.showMaxDefenderGoalsCount(GetMaxDefenderGoalsUseCase.execute(players));
    }

    private void onChartRequested() {
        if (hasPlayersError()) return;
        var chartData = ChartMapper.mapDataToChart(players);
        ChartDrawer.showChart(chartData);
    }
}
