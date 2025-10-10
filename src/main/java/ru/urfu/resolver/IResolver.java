package ru.urfu.resolver;

import ru.urfu.model.Player;

import java.util.List;

public interface IResolver {

    void setPlayers(List<Player> players);

    // Выведите количество игроков, интересы которых не представляет агентство.
    int getCountWithoutAgency();

    // Выведите максимальное число голов, забитых защинтником.
    int getMaxDefenderGoalsCount();

    // Выведите русское название позиции самого дорогого немецкого игрока.
    String getTheExpensiveGermanPlayerPosition();

    // Выберите команду с наибольшим средним числом удалений на одного игрока.
    String getTheRudestTeam();
}
