package ru.urfu.domain.model;

public record Player(
        String name,
        Position position,
        String agency,
        int goals
) {
}
