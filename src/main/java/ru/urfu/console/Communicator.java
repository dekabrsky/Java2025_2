package ru.urfu.console;

import ru.urfu.parser.CsvParser;
import ru.urfu.graphics.Graphic;
import ru.urfu.resolver.Resolver;

import java.util.Scanner;

public class Communicator {
    public static void solve() {
        String fileName = "src/main/resources/fakePlayers.csv";
        var players = CsvParser.parseCsvToList(fileName);

        var resolver = new Resolver(players);

        System.out.print("Количество игроков, интересы которых не представляет агентство: ");
        System.out.println(resolver.getCountWithoutAgency());

        System.out.print("Максимальное число голов, забитых защитником: ");
        System.out.println(resolver.getMaxDefenderGoalsCount());

        System.out.print("Русское название позиции самого дорогого немецкого игрока: ");
        System.out.println(resolver.getTheExpensiveGermanPlayerPosition());

        System.out.print("Команда с наибольшим средним числом красных карточек на одного игрока: ");
        System.out.println(resolver.getTheRudestTeam());

        Graphic.showGraphic(players);
    }
}
