package main.java.ru.urfu.presentation.controller;

import main.java.ru.urfu.drawer.Drawer;
import main.java.ru.urfu.mapper.Mapper;
import main.java.ru.urfu.model.Player;
import main.java.ru.urfu.parser.CsvParser;
import main.java.ru.urfu.presentation.view.ConsoleView;
import main.java.ru.urfu.resolver.Resolver;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ConsoleView view;
    private final Scanner scanner;
    private final Resolver resolver;
    private final Mapper mapper;
    private final Drawer drawer;


    public Controller(ConsoleView view, Resolver resolver) {
        this.view = view;
        this.resolver = resolver;
        scanner = new Scanner(System.in);
        drawer = new Drawer();
        mapper = new Mapper();
    }

    public void start() {
        view.showWelcome();
        mainCycle:
        while (true) {
            view.showEnterCommand();

            switch (scanner.nextLine()) {
                case "F" -> onEnterFile();
                case "1" -> onTask1Request();
                case "2" -> onTask2Request();
                case "3" -> onTask3Request();
                case "4" -> onTask4Request();
                case "G" -> onChartRequest();
                default -> {
                    break mainCycle;
                }
            }
        }
    }

    private void onEnterFile() {
        view.showFilePath();
        try {
            List<Player> players = CsvParser.parserCsvToList(scanner.nextLine());
            resolver.setPlayers(players);
            mapper.setPlayers(players);
        } catch (Exception e) {
            this.onFileError();
        }
    }

    private void onFileError() {
        view.showFileError();
    }

    private void onTask1Request() {
        view.showCountWithoutAgency(resolver.getCountWithoutAgency());
    }

    private void onTask2Request() {
        view.showMaxDefenderGoalsCount(resolver.getMaxDefenderGoalsCount());
    }

    private void onTask3Request() {
        view.showTheExpensiveGermanPlayerPosition(resolver.getTheExpensiveGermanPlayerPosition());
    }

    private void onTask4Request() {
        view.showTheRudestTeam(resolver.getTheRudestTeam());
    }

    private void onChartRequest() {
        drawer.drawDiagramPlayersByPosition(mapper.getPlayersByPosition());
    }
}
