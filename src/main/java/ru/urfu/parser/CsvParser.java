package main.java.ru.urfu.parser;

import main.java.ru.urfu.model.Position;
import main.java.ru.urfu.model.Player;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvParser {
    public static ArrayList<Player> parserCsvToList(String pathString) {
        var path = Paths.get(pathString);
        var scan = getScanner(path);
        var playersList = new ArrayList<Player>();

        scan.nextLine();
        while (scan.hasNextLine()) {
            playersList.add(parseRowToPlayer(scan.nextLine()));
        }
        return playersList;
    }

    private static Scanner getScanner(Path path) {
        try {
            return new Scanner(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Player parseRowToPlayer(String row) {
        var cells = row.split(";");
        return new Player(cells[0],
                cells[1],
                cells[2],
                Position.valueOf(cells[3]),
                cells[4],
                cells[5],
                Integer.parseInt(cells[6]),
                Integer.parseInt(cells[7]),
                Integer.parseInt(cells[8]),
                Integer.parseInt(cells[9]),
                Integer.parseInt(cells[10]));
    }
}
