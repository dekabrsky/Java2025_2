package ru.urfu.console;

import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

import java.util.ArrayList;
import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);

        ArrayList<Player> players = null;
        Resolver resolver = null;

        System.out.println("Команды: \n" +
                "FILE - выбрать файл для анализа\n" +
                "1 - решение залачи 1 \n" +
                "2 - решение задачи 2\n" +
                "CHART - показать график\n" +
                "EXIT - завершить работу\n");

        mainCycle: while (true) {
            System.out.print("Введите команду: ");
            var command = scanner.nextLine();
            switch (command) {
                case "FILE" -> {
                    System.out.print("Укажите файл:");
                    players = CsvParser.parseCsvToList(scanner.nextLine());
                    resolver = new Resolver(players);
                }
                case "1" -> {
                    if (isFileError(resolver)) break;
                    System.out.print("Количество игроков без агентства: ");
                    System.out.println(resolver.getCountWithoutAgency());
                }
                case "2" -> {
                    if (isFileError(resolver)) break;
                    System.out.print("Максимум голов защитника: ");
                    System.out.println(resolver.getMaxDefenderGoalsCount());
                }
                case "CHART" -> {
                    if (isFileError(resolver)) break;
                    var chartData = ChartMapper.mapDataToChart(players);
                    ChartDrawer.showChart(chartData);
                }
                case "EXIT" -> {
                    break mainCycle;
                }
                default -> {
                    System.out.println("Неверная команда");
                }
            }
        }
    }

    private static boolean isFileError(Resolver resolver) {
        if (resolver == null) {
            System.out.println("Не выбран файл");
            return true;
        }
        return false;
    }
}
