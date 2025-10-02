package ru.urfu.console;

import ru.urfu.parser.*;
import ru.urfu.resolver.*;
import ru.urfu.graphics.*;

import java.util.Scanner;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);
        System.out.println("Введите файл для анализа из директории src/main/resources: ");
        String fileName = scanner.nextLine();
        
        var players = CSVParser.parseCSVToList(fileName);
        var resolver = new Resolver(players);
        
        System.out.println("Количество игроков, интересы которых не представляет агенство:");
        System.out.println(resolver.getCountWithoutAgency());

        System.out.println("Максимальное число голов, забитых защитником:");
        System.out.println(resolver.getMaxDefenderGoalsCount());

        System.out.println("Русское название позиции самого дорогого немецкого игрока:");
        System.out.println(resolver.getTheExpensiveGermanPlayerPosition());

        System.out.println("Команда с наибольшим средним числом красных карточек на одного игрока:");
        System.out.println(resolver.getTheRudestTeam());

        Graphics.showGraphics(Graphics.makeMapping(players));
    }
}
