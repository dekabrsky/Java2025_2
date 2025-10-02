package ru.urfu.model;

public class Player {
    private String name;
    private String team;
    private String city;
    private String position;
    private String nationality;
    private String agency;
    private int transferCost;
    private int participations;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;

    public Player(
        String name,
        String team,
        String city,
        String position,
        String nationality,
        String agency,
        int transferCost,
        int participations,
        int goals,
        int assists,
        int yellowCards,
        int redCards
    ) {
        this.name = name;
        this.team = team;
        this.city = city;
        this.position = position;
        this.nationality = nationality;
        this.agency = agency;
        this.transferCost = transferCost;
        this.participations = participations;
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public String getName() { return name; }
    public String getTeam() { return team; }
    public String getCity() { return city; }
    public String getPosition() { return position; }
    public String getNationality() { return nationality; }
    public String getAgency() { return agency; }
    public int getTransferCost() { return transferCost; }
    public int getParticipations() { return participations; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public int getYellowCards() { return yellowCards; }
    public int getRedCards() { return redCards; }
}
