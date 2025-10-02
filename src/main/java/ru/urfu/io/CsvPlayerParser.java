package ru.urfu.io;

import ru.urfu.mapper.PlayerMapper;
import ru.urfu.model.Player;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvPlayerParser implements IParser<Player> {

    private Scanner getScanner(Path path) throws IOException {
        try {
            return new Scanner(path);
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении CSV-файла: " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public List<String> readFile(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        Scanner scanner = getScanner(path);

        List<String> rows = new ArrayList<>();

        boolean isHeaderSkipped = false;

        while (scanner.hasNext()) {
            if (!isHeaderSkipped) {
                isHeaderSkipped = true;
                scanner.nextLine();
                continue;
            }
            rows.add(scanner.nextLine());
        }

        return rows;
    }

    public List<Player> parse(String pathString) throws IOException {
        List<String> rows = readFile(pathString);
        List<Player> players = new ArrayList<>();

        PlayerMapper mapper = new PlayerMapper();

        for (String row : rows) {
            players.add(mapper.map(row));
        }

        return players;
    }
}
