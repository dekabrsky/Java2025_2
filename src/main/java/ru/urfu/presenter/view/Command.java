package ru.urfu.presenter.view;

public enum Command {
    OPEN_FILE,
    SHOW_COUNT_WITHOUT_AGENCY,
    SHOW_MAX_DEFENDER_GOALS_COUNT,
    SHOW_THE_EXPENSIVE_GERMAN_PLAYER_POSITION,
    SHOW_THE_RUDEST_TEAM,
    SHOW_PLAYERS_BY_POSITIONS_CHART,
    EXIT;

    int index() {
        for (int i = 0; i < values().length; ++i) {
            if (this == Command.values()[i]) {
                return i;
            }
        }
        assert false : "Should be unreachable";
        return -1;
    }
}
