package ru.urfu.model;

public record Player(
	String name,
	String team,
	String city,
	Position position,
	String nationality,
	String agency,
	int transfer_cost,
	int matches,
	int goals,
	int assists,
	int yellow_cards,
	int red_cards
) {

}
