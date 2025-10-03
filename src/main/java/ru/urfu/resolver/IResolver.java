package ru.urfu.resolver;

public interface IResolver {
    // Выведите количество игроков, интересы которых не представляет агентство.
    int getCountWithoutAgency();

    // Выведите защитника с максимальным числом голов.
    String getDefenderWithMaxGoalsName();

    // Выведите максимальное число голов, забитых защинтником.
    int getMaxDefenderGoalsCount();

    // Выведите русское название позиции самого дорогого немецкого игрока.
    String getTheExpensiveGermanPlayerPosition();

    // Выберите команду с наибольшим средним числом удалений на одного игрока.
    String getTheRudestTeam();
}
