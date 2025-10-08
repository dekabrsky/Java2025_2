package ru.urfu.presentation.view;

public class ConsoleView implements ViewInterface {
    @Override
    public void showWelcome() {
        System.out.println("Команды: \n" +
                "FILE - выбрать файл для анализа\n" +
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
