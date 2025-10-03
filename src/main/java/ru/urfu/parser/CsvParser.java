package ru.urfu.parser;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvParser {
    public static ArrayList<Player> parseCsvToList(String pathString) {
        var path = Paths.get(pathString);
        var scanner = getScanner(path);
        var list = new ArrayList<Player>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            list.add(parsePlayerRow(scanner.nextLine()));
        }
        return list;
    }

    private static Player parsePlayerRow(String row) {
        var cells = row.split(";");
        return new Player(
                cells[0],
                Position.valueOf(cells[3]),
                cells[5],
                Integer.parseInt(cells[8]),
                cells[4],
                Integer.parseInt(cells[6]),
                Integer.parseInt(cells[11]),
                cells[1]
        );
    }

    private static Scanner getScanner(Path path) {
        try {
            return new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
