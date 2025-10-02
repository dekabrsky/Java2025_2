package ru.urfu.io;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import ru.urfu.model.Player;
import ru.urfu.model.Position;

public class CSVReader {
	public static ArrayList<Player> readFromFile(String path) {
		var playersList = new ArrayList<Player>();
		Scanner scan;
		try {
			scan = new Scanner(Paths.get(path));
		} catch (IOException e) {
			return playersList;
		}

		scan.nextLine();
		while (scan.hasNextLine()) {
			playersList.add(convertRow2Player(scan.nextLine()));
		}
		return playersList;
	}

	private static Player convertRow2Player(String row) {
		var elems = row.split(";");
		return new Player(
			elems[0],
			elems[1],
			elems[2],
			Position.valueOf(elems[3]),
			elems[4],
			elems[5],
			Integer.parseInt(elems[6]),
			Integer.parseInt(elems[7]),
			Integer.parseInt(elems[8]),
			Integer.parseInt(elems[9]),
			Integer.parseInt(elems[10]),
			Integer.parseInt(elems[11])
		);
	}
}
