package ru.urfu.model;

public record Player(
        String name,
        String team,
        String city,
        Position position,
        String nationality,
        String agency,
        int transferCost,
        int participations,
        int goals,
        int assists,
        int yellowCards,
        int redCards) {}
