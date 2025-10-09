package ru.urfu;

public class Player {
    public String name;
    public String team;
    public String city;
    public String position;
    public String nationality;
    public String agency;
    public int transfer_cost;
    public int participations;
    public int goals;
    public int assists;
    public int yellow_cards;
    public int red_cards;

    Player (String[] args) {
        name = args[0];
        team = args[1];
        city = args[2];
        position = args[3];
        nationality = args[4];
        agency = args[5];
        transfer_cost = Integer.parseInt(args[6]);
        participations = Integer.parseInt(args[7]);
        goals = Integer.parseInt(args[8]);
        assists = Integer.parseInt(args[9]);
        yellow_cards = Integer.parseInt(args[10]);
        red_cards = Integer.parseInt(args[11]);
    }
}
