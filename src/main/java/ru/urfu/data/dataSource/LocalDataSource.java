package ru.urfu.data.dataSource;

import ru.urfu.domain.model.Player;
import ru.urfu.parser.CsvParser;

import java.util.List;

public class LocalDataSource {
    private static final String LOCAL_FILE = "players.csv";

    public List<Player> loadPlayersFromCsv() {
        return CsvParser.parseCsvToList(LOCAL_FILE);
    }
}
