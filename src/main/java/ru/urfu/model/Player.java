package ru.urfu.model;

public record Player(
        String name,
        Position position,
        String agency,
        int goals,
        String nationality,
        int transferCost,
        int redCardsCount,
        String team
) {
}
