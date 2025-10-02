package ru.urfu.parser;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static ArrayList<Player> parseCsvToList(String pathString) {
        Path path = Paths.get(pathString);
        Scanner scanner = getScanner(path);
        var list = new ArrayList<Player>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            list.add(parsePlayerRow(scanner.nextLine()));
        }

        return list;
    }

    private static Player parsePlayerRow(String row) {
        var cells = row.split(";");
        var p = new Player(
                cells[0],
                cells[1],
                cells[2],
                Position.valueOf(cells[3]),
                cells[4],
                cells[5],
                Integer.parseInt(cells[6]),
                Integer.parseInt(cells[7]),
                Integer.parseInt(cells[8]),
                Integer.parseInt(cells[9]),
                Integer.parseInt(cells[10]),
                Integer.parseInt(cells[11])
        );
        return p;
    }

    private static Scanner getScanner(Path path) {
        try {
            return new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}