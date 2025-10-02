package ru.urfu.model;

public enum Position {
	MIDFIELD("полузащитник", "ru"),
	DEFENDER("защитник", "ru"),
	FORWARD("нападающий", "ru"),
	GOALKEEPER("вратарь", "ru");

	private final String title;
	private final String lang;

	Position(String title, String lang) {
		this.title = title;
		this.lang = lang;
	}

	public String getTitle() {
		return this.title;
	}
};
