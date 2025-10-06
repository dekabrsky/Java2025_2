package main.java.ru.urfu.presentation.view;

public class ConsoleView {
    public void showWelcome() {
        System.out.println("""
                Конанды:
                F - выбрать файл для анализа
                1 - решение задачи 1
                2 - решение задачи 2
                3 - решение задачи 3
                4 - решение задачи 4
                G - показать график
                Любая иная клавиша - завершить работу""");
    }

    public void showEnterCommand() {
        System.out.print("Введите команду: ");
    }

    public void showFilePath() {
        System.out.print("Полный путь до .csv файла: ");
    }

    public void showFileError() {
        System.out.println("Неправильный путь до файла");
    }

    public void showCountWithoutAgency(int count) {
        System.out.printf("Количество игроков без агентсва: %d\n", count);
    }

    public void showMaxDefenderGoalsCount(int count) {
        System.out.printf("Максимальное количество голов у защитника: %d\n", count);
    }

    public void showTheExpensiveGermanPlayerPosition(String position) {
        System.out.printf("Название позиции самого дорогого немецкого игрока: %s\n", position);
    }

    public void showTheRudestTeam(String team) {
        System.out.printf("Команда с наибольшим средним числом удалений на одного игрока: %s\n", team);
    }
}
