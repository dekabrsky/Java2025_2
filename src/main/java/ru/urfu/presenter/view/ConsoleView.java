package ru.urfu.presenter.view;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

class CommandsListViewItem {
    Command command;
    String name;
    String description;

    CommandsListViewItem(Command command, String name, String description) {
        this.command = command;
        this.name = name;
        this.description = description;
    }
}

class CommandsListViewGenerator {
    private final CommandsListViewItem[] list;
    private int added;

    public CommandsListViewGenerator() {
        list = new CommandsListViewItem[Command.values().length];
        added = 0;

        add(Command.OPEN_FILE, "FILE", "выбрать файл для анализа");
        final Command[] tasks = {
                Command.SHOW_COUNT_WITHOUT_AGENCY,
                Command.SHOW_MAX_DEFENDER_GOALS_COUNT,
                Command.SHOW_THE_EXPENSIVE_GERMAN_PLAYER_POSITION,
                Command.SHOW_THE_RUDEST_TEAM
        };
        for (int i = 0; i < tasks.length; ++i) {
            final var taskNumber = String.valueOf(i + 1);
            add(tasks[i], taskNumber, "решение задачи " + taskNumber);
        }
        add(
                Command.SHOW_PLAYERS_BY_POSITIONS_CHART,
                "CHART",
                "показать график распределения игроков по позициям"
        );
        add(Command.EXIT, "EXIT", "завершить работу");
        assert added == list.length;
    }

    private void add(Command command, String name, String description)
    {
        list[added++] = new CommandsListViewItem(command, name, description);
    }

    public CommandsListViewItem[] get() {
        return list;
    }
}

public class ConsoleView implements IView {
    private static final CommandsListViewItem[] commandsListView =
            new CommandsListViewGenerator().get();
    private final ICallOnCommand[] callOnCommand;
    Scanner scanner;

    public ConsoleView() {
        callOnCommand = new ICallOnCommand[Command.values().length];
        scanner = new Scanner(System.in);
    }

    @Override
    public void setCallOnCommand(Command command, ICallOnCommand call) {
        callOnCommand[command.index()] = call;
    }

    @Override
    public void start() {
        showWelcome();
        while (true) {
            final var command = getCommand();
            if (command.isEmpty()) {
                showCommandError();
            } else {
                call(command.get());
            }
        }
    }

    @Override
    public void terminate() {
        System.exit(0);
    }

    @Override
    public void showPlayersError() {
        System.out.println("Игроки не загружены");
    }

    @Override
    public String selectFile() {
        System.out.print("Выберите файл: ");
        return scanner.nextLine();
    }

    @Override
    public void showCountWithoutAgency(int count) {
        System.out.print("Количество игроков без агента: ");
        System.out.println(count);
    }

    @Override
    public void showMaxDefenderGoalsCount(int count) {
        System.out.print("Максимальное число голов, забитых защитником: ");
        System.out.println(count);
    }

    @Override
    public void showTheExpensiveGermanPlayerPosition(String answer) {
        System.out.print("Название позиции самого дорогого немецкого игрока: ");
        System.out.println(answer);
    }

    @Override
    public void showTheRudestTeam(String answer) {
        System.out.print("Команда с наибольшим средним числом удалений на одного игрока: ");
        System.out.println(answer);
    }

    private void showWelcome() {
        System.out.println("Команды");
        for (var item : commandsListView) {
            System.out.println(item.name + " - " + item.description);
        }
    }

    private String readCommandName() {
        System.out.print("Введите команду: ");
        return scanner.nextLine();
    }

    private Optional<Command> getCommand() {
        final var inputtedName = readCommandName();
        return Arrays.stream(commandsListView)
                .filter(cmd -> cmd.name.equals(inputtedName))
                .findAny()
                .map(cmd -> cmd.command);
    }

    private void call(Command command) {
        var toCall = callOnCommand[command.index()];
        assert toCall != null : "callOnCommand for " + command.name() + " is null";
        toCall.call();
    }

    private void showCommandError() {
        System.out.println("Команда не распознана");
    }
}
