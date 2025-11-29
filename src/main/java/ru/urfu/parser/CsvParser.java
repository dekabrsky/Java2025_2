package ru.urfu.parser;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvParser {
    private final String path;

    public CsvParser(String path) {
        this.path = path;
    }

    public List<Player> parseCsvToList() {
        try {
            return Files.readAllLines(Paths.get(path))
                    .stream()
                    .skip(1)
                    .map(CsvParser::parsePlayerRow)
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private static Player parsePlayerRow(String row) {
        var cells = row.split(";");
        return new Player(
                cells[0],
                Position.valueOf(cells[3]),
                cells[5],
                Integer.parseInt(cells[8])
        );
    }
}
