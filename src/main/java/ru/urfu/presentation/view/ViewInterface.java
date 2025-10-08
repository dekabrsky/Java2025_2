package ru.urfu.presentation.view;

public interface ViewInterface {
    void showWelcome();

    void showEnterCommand();

    void showPlayersError();

    void showCommandError();

    void showSelectFile();

    void showCountWithoutAgency(int count);

    void showMaxDefenderGoalsCount(int score);
}
