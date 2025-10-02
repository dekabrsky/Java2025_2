package ru.urfu.model;

public class PlayerBuilder {
    private String name;
    private String team;
    private String city;
    private Position position;
    private String nationality;
    private String agency;
    private int transferCost;
    private int participations;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;

    public PlayerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder team(String team) {
        this.team = team;
        return this;
    }

    public PlayerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public PlayerBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public PlayerBuilder nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public PlayerBuilder agency(String agency) {
        this.agency = agency;
        return this;
    }

    public PlayerBuilder transferCost(int transferCost) {
        this.transferCost = transferCost;
        return this;
    }

    public PlayerBuilder participations(int participations) {
        this.participations = participations;
        return this;
    }

    public PlayerBuilder goals(int goals) {
        this.goals = goals;
        return this;
    }

    public PlayerBuilder assists(int assists) {
        this.assists = assists;
        return this;
    }

    public PlayerBuilder yellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
        return this;
    }

    public PlayerBuilder redCards(int redCards) {
        this.redCards = redCards;
        return this;
    }

    public Player build() {
        return new Player(
                this.name,
                this.team,
                this.city,
                this.position,
                this.nationality,
                this.agency,
                this.transferCost,
                this.participations,
                this.goals,
                this.assists,
                this.yellowCards,
                this.redCards
        );
    }
}
