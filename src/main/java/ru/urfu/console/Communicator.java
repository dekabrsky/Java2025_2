package ru.urfu.console;

import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите файл для анализа: ");
        var fileName = scanner.nextLine();

        var players = CsvParser.parseCsvToList(fileName);

        var resolver = new Resolver(players);

        System.out.print("Количество игроков без агентства: ");
        System.out.println(resolver.getCountWithoutAgency());

        System.out.print("Максимум голов защитника: ");
        System.out.println(resolver.getMaxDefenderGoalsCount());

        var chartData = ChartMapper.mapDataToChart(players);
        ChartDrawer.showChart(chartData);
    }
}
