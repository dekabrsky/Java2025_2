package ru.urfu.console;

import ru.urfu.graphic.Graphic;
import ru.urfu.parser.Parser;
import ru.urfu.resolver.Resolver;

import java.util.Scanner;

public class Communicator {
    public static void runChampionship(){
        var scanner = new Scanner(System.in);
        System.out.print("Введите файл для анализа: ");
        var fileName = scanner.nextLine();
        var players = Parser.parseCsvToList(fileName);

        var res = new Resolver(players);

        System.out.print("Кол-во игроков без агенства: ");
        System.out.println(res.getCountWithoutAgency());

        System.out.print("Максимальное число голов, забитых защитником: ");
        System.out.println(res.getMaxDefenderGoalsCount());

        System.out.print("Позиция самого дорогого немецкого игрока: ");
        System.out.println(res.getTheExpensiveGermanPlayerPosition());

        System.out.print("Самая грубая команда: ");
        System.out.println(res.getTheRudestTeam());

        Graphic.showPositionDistributionChart(players);

    }
}