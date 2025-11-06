package ru.urfu.presentation.view;

import com.google.inject.Inject;
import ru.urfu.presentation.presenter.Presenter;
import ru.urfu.presentation.presenter.PresenterFactory;

import java.util.Scanner;

public class ConsoleView implements ViewInterface {

    private final Presenter presenter;
    private final Scanner scanner = new Scanner(System.in);

    @Inject
    public ConsoleView(PresenterFactory presenterFactory) {
        presenter = presenterFactory.create(this);
        start();
    }

    public void start() {
        while (true) {
            presenter.onNewCommand(scanner.nextLine());
        }
    }

    @Override
    public void showWelcome() {
        System.out.println("Команды: \n" +
                "LOAD - выбрать файл для анализа\n" +
                "1 - решение залачи 1 \n" +
                "2 - решение задачи 2\n" +
                "CHART - показать график\n" +
                "EXIT - завершить работу\n");
    }

    @Override
    public void showEnterCommand() {
        System.out.print("Введите команду: ");
    }

    @Override
    public void showPlayersError() {
        System.out.println("Игроки не загружены");
    }

    @Override
    public void showCommandError() {
        System.out.println("Команда не распознана");
    }

    @Override
    public void showSelectFile() {
        System.out.print("Выберите файл: ");
    }

    @Override
    public void showCountWithoutAgency(int count) {
        System.out.println("Количество игроков без агентства: " + count);
    }

    @Override
    public void showMaxDefenderGoalsCount(int score) {
        System.out.println("Максимум голов защитника: " + score);
    }
}
