package ru.urfu.presenter.view;

public interface IView {
    void setCallOnCommand(Command command, ICallOnCommand call);

    void start();

    void terminate();

    void showPlayersError();

    String selectFile();

    void showCountWithoutAgency(int count);

    void showMaxDefenderGoalsCount(int count);

    void showTheExpensiveGermanPlayerPosition(String answer);

    void showTheRudestTeam(String answer);
}
