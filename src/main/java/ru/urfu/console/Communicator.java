package ru.urfu.console;

import java.util.Scanner;
import java.util.stream.Collectors;

import ru.urfu.graphics.Graphic;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

public class Communicator {
    public static void runChampionship() {
        var scanner = new Scanner(System.in);
        System.out.print("Введите файл для анализа: ");
        String filePath = "/home/pavloxes/Java2025_2/src/main/resources/fakePlayers.csv";
        //String fileName = scanner.nextLine();
        var players = CsvParser.parseCsvToList(filePath);

        var resolver = new Resolver(players);

        System.out.print("Количество игроков, интересы которых не представляет агентство: ");
        System.out.println(resolver.getCountWithoutAgency());

        System.out.print("Автор наибольшего числа голов из числа защитников: ");
        System.out.println(resolver.getDefenderWithMaxGoalsName());

        System.out.print("Наибольшее число голов, забитых защитником: ");
        System.out.println(resolver.getMaxDefenderGoalsCount());

        System.out.print("Русское название позиции самого дорогого немецкого игрока: ");
        System.out.println(resolver.getTheExpensiveGermanPlayerPosition());

        System.out.print("Команда с наибольшим средним числом красных карточек на одного игрока: ");
        System.out.println(resolver.getTheRudestTeam());

        var graphic = new Graphic();

        graphic.drawDiagramPlayersByPosition( players.stream()
                                                .collect(Collectors.groupingBy(Player::position, Collectors.counting())));

    }
}