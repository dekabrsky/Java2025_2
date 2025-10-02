package ru.urfu.model;

public enum Position {
    GOALKEEPER("Вратарь"), DEFENDER("Защитник"), FORWARD("Нападающий"), MIDFIELD("Полузащитник");
    private final String russianName;

    public String getRussianName() {
        return russianName;
    }

    Position(String russianName) {
        this.russianName = russianName;
    }
}
