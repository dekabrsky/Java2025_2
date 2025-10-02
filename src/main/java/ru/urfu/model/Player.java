package ru.urfu.model;

public record Player(
    String name,
    String team,
    String city,
    Position position,
    String nationality,
    String agency,
    int cost,
    int goals,
    int yCards,
    int rCards
) {}
