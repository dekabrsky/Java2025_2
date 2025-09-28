package main.java.ru.urfu.console;

import main.java.ru.urfu.parser.CsvParser;
import main.java.ru.urfu.resolver.Resolver;

import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scan = new Scanner(System.in);
        System.out.print("Full Path to .csv file required: ");
        var filename = scan.nextLine();

        var players = CsvParser.parserCsvToList(filename);
        var resolver = new Resolver(players);
        System.out.printf("Количество игроков без агентсва: %d\n", resolver.getCountWithoutAgency());
        System.out.printf("Максимальное количество голов у защитника: %d\n", resolver.getMaxDefenderGoalsCount());
        System.out.printf("Название позиции самого дорогого немецкого игрока: %s\n", resolver.getTheExpensiveGermanPlayerPosition());
        System.out.printf("Команда с наибольшим средним числом удалений на одного игрока: %s\n", resolver.getTheRudestTeam());
    }
}
