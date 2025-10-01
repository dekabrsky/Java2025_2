package ru.urfu.console;

import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите файл для анализа: ");
        String fileName = "D:\\java_git\\2\\src\\main\\resources\\fakePlayers.csv";
        //String fileName = scanner.nextLine();
        var players = CsvParser.parseCsvToList(fileName);
        //System.out.println(players);

        var resolver = new Resolver(players);

        System.out.print("Количество игроков, интересы которых не представляет агентство: ");
        System.out.println(resolver.getCountWithoutAgency());

        System.out.print("Максимальное число голов, забитых защитником: ");
        System.out.println(resolver.getMaxDefenderGoalsCount());

        System.out.print("Русское название позиции самого дорогого немецкого игрока: ");
        System.out.println(resolver.getTheExpensiveGermanPlayerPosition());

        System.out.print("Команда с наибольшим средним числом красных карточек на одного игрока: ");
        System.out.println(resolver.getTheRudestTeam());

        resolver.renderGraphic();
    }
}
