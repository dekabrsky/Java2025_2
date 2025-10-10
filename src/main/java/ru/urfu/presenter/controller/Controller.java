package ru.urfu.presenter.controller;

import ru.urfu.drawer.Drawer;
import ru.urfu.mapper.Mapper;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;
import ru.urfu.presenter.view.Command;
import ru.urfu.presenter.view.IView;
import ru.urfu.resolver.IResolver;

import java.util.ArrayList;

public class Controller {
    IView view;
    IResolver resolver;
    ArrayList<Player> players;

    public Controller(IView view, IResolver resolver) {
        this.view = view;
        this.resolver = resolver;
        players = null;
        view.setCallOnCommand(Command.OPEN_FILE, this::onOpenFile);
        view.setCallOnCommand(Command.SHOW_COUNT_WITHOUT_AGENCY,
                this::onShowCountWithoutAgency);
        view.setCallOnCommand(Command.SHOW_MAX_DEFENDER_GOALS_COUNT,
                this::onShowMaxDefenderGoalsCount);
        view.setCallOnCommand(Command.SHOW_THE_EXPENSIVE_GERMAN_PLAYER_POSITION,
                this::onShowTheExpensiveGermanPlayerPosition);
        view.setCallOnCommand(Command.SHOW_THE_RUDEST_TEAM, this::onShowTheRudestTeam);
        view.setCallOnCommand(Command.SHOW_PLAYERS_BY_POSITIONS_CHART,
                this::onShowPlayersByPositionsChart);
        view.setCallOnCommand(Command.EXIT, this::onExit);
        var filename = "C:\\projects\\java_projects\\Java2025_2\\src\\main\\resources\\fakePlayers.csv";
        openFile(filename);
    }

    public void start() {
        view.start();
    }

    private void openFile(String path)
    {
        players = CsvParser.parseCsvToList(path);
        resolver.setPlayers(players);
    }

    private void onOpenFile() {
        openFile(view.selectFile());
    }

    private void onShowCountWithoutAgency() {
        if (emptyPlayersError()) return;
        view.showCountWithoutAgency(resolver.getCountWithoutAgency());
    }

    private void onShowMaxDefenderGoalsCount() {
        if (emptyPlayersError()) return;
        view.showMaxDefenderGoalsCount(resolver.getMaxDefenderGoalsCount());
    }

    private void onShowTheExpensiveGermanPlayerPosition() {
        if (emptyPlayersError()) return;
        view.showTheExpensiveGermanPlayerPosition(
                resolver.getTheExpensiveGermanPlayerPosition()
        );
    }

    private void onShowTheRudestTeam() {
        if (emptyPlayersError()) return;
        view.showTheRudestTeam(resolver.getTheRudestTeam());
    }

    private void onShowPlayersByPositionsChart() {
        if (emptyPlayersError()) return;
        Drawer.drawPieChart(Mapper.playersByPositions(players));
    }

    private boolean emptyPlayersError() {
        if (players == null || players.isEmpty()) {
            view.showPlayersError();
            return true;
        }
        return false;
    }

    private void onExit() {
        view.terminate();
    }
}
