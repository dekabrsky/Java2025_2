package ru.urfu.loader

import ru.urfu.model.Player
import java.io.File

object PlayerLoader {

    fun loadPlayersFromCSV(path: String): List<Player> {
        return File(path).readLines()
            .drop(1) // Пропускаем заголовок
            .map { line ->
                val tokens = line.split(";").map { it.trim() }

                Player(
                    name = tokens[0],
                    team = tokens[1],
                    city = tokens[2],
                    position = tokens[3],
                    nationality = tokens[4],
                    agency = if (tokens[5].isBlank()) null else tokens[5],
                    transferCost = tokens[6].replace("_", "").replace("€", "").toInt(),
                    participations = tokens[7].toInt(),
                    goals = tokens[8].toInt(),
                    assists = tokens[9].toInt(),
                    yellowCards = tokens[10].toInt(),
                    redCards = tokens[11].toInt()
                )
            }
    }
}
