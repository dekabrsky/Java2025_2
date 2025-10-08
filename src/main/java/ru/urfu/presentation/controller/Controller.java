package ru.urfu.presentation.controller;

import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;
import ru.urfu.presentation.view.ViewInterface;
import ru.urfu.resolver.Resolver;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ViewInterface view;
    private final Scanner scanner;
    private final Resolver resolver;

    private List<Player> players;

    public Controller(ViewInterface view, Resolver resolver) {
        this.view = view;
        this.resolver = resolver;
        scanner = new Scanner(System.in);
    }

    public void start() {
        view.showWelcome();
        mainCycle : while (true) {
            view.showEnterCommand();

            switch (scanner.nextLine()) {
                case "FILE" -> onEnterFile();
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

    private void onEnterFile() {
        view.showSelectFile();
        players = CsvParser.parseCsvToList(scanner.nextLine());
        resolver.setPlayers(players);
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
        view.showCountWithoutAgency(resolver.getCountWithoutAgency());
    }

    private void onTask2Requested() {
        if (hasPlayersError()) return;
        view.showMaxDefenderGoalsCount(resolver.getMaxDefenderGoalsCount());
    }

    private void onChartRequested() {
        if (hasPlayersError()) return;
        var chartData = ChartMapper.mapDataToChart(players);
        ChartDrawer.showChart(chartData);
    }
}
