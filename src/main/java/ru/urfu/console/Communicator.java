package ru.urfu.console;

import ru.urfu.drawer.Drawer;
import ru.urfu.mapper.Mapper;
import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

import javax.swing.*;
import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите файл для анализа: ");
        var filename = scanner.nextLine();
        //var filename = "C:\\projects\\java_projects\\Java2025_2\\src\\main\\resources\\fakePlayers.csv";
        var players = CsvParser.parseCsvToList(filename);

        var resolver = new Resolver(players);
        System.out.print("Количество игроков без агента: ");
        System.out.println(resolver.getCountWithoutAgency());
        System.out.print("Максимальное число голов, забитых защитником: ");
        System.out.println(resolver.getMaxDefenderGoalsCount());
        System.out.print("Название позиции самого дорогого немецкого игрока: ");
        System.out.println(resolver.getTheExpensiveGermanPlayerPosition());
        System.out.print("Команда с наибольшим средним числом удалений на одного игрока: ");
        System.out.println(resolver.getTheRudestTeam());

        Drawer.drawPieChart(Mapper.playersByPositions(players));
    }
}
