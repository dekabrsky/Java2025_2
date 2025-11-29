package ru.urfu.parser;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvParser {
    public static List<Player> parseCsvToList(String pathString) throws IOException {
        return Files.readAllLines(Paths.get(pathString))
                .stream()
                .skip(1)
                .map(CsvParser::parsePlayerRow)
                .toList();
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
