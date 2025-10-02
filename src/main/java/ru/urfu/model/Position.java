package ru.urfu.model;

public enum Position {
    GOALKEEPER("Вратарь"),
    DEFENDER("Защитник"),
    MIDFIELD("Полузащитик"),
    FORWARD("Нападающий");

    private final String titleRu;

    Position(String title){
        this.titleRu = title;
    }

    public String getTitleRu(){
        return this.titleRu;
    }

}