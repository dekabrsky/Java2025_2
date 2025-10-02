package ru.urfu.model;

public record Player(
        String name,
        Role position,
        String agency,
        int goals,
        String nation,
        int cost,
        String team,
        int redCards
){}
