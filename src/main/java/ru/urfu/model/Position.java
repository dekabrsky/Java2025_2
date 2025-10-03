package ru.urfu.model;

public enum Position {
    GOALKEEPER,
    DEFENDER,
    MIDFIELD,
    FORWARD;

    public String inRussian() {
        return switch (this) {
            case GOALKEEPER -> "вратарь";
            case DEFENDER -> "защитник";
            case MIDFIELD -> "полузащитник";
            case FORWARD -> "нападающий";
        };
    }
}
