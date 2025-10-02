package ru.urfu;

import java.util.List;

import ru.urfu.resolver.Task1;
import ru.urfu.model.Player;
import ru.urfu.model.Position;
import ru.urfu.resolver.Task1;
import ru.urfu.io.CSVReader;
import ru.urfu.io.GraphDrawer;

public class Main {
    public static void main(String[] args) {
		List<Player> players = CSVReader.readFromFile(args[1]);
		Task1 task1 = new Task1(players);

        System.out.printf("Task1:\n");
		System.out.printf("Игроков без агенства: %s\n", task1.getCountWithoutAgency());
		System.out.printf("Наибольшее число голов у игрока из числа защитников: %s\n", task1.getMaxDefenderGoalsCount());
		System.out.printf("Позиция самого дорогого немецкого игрока: %s\n", task1.getTheExpensiveGermanPlayerPosition());
		System.out.printf("Команда с наибольшим средним числом удалений на одного игрока: %s\n", task1.getTheRudestTeam());

		GraphDrawer.displayGraph3D(players);
    }
}
