package ru.urfu.parser;

import ru.urfu.model.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class CSVParser {
    public static ArrayList<Player> parseCSVToList(String filename) {
        var list = new ArrayList<Player>();
        var scanner = getScanner(Paths.get(System.getProperty("user.dir") + "/src/main/resources/" + filename));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            list.add(parsePlayerRow(scanner.nextLine()));
        }

        return list;
    }

    public static Player parsePlayerRow(String row) {
        var cells = row.split(";");
        return new Player(
                cells[0],
                cells[1],
                cells[2],
                Position.valueOf(cells[3]),
                cells[4],
                cells[5],
                Integer.parseInt(cells[6]),
                Integer.parseInt(cells[8]),
                Integer.parseInt(cells[10]),
                Integer.parseInt(cells[11])
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
