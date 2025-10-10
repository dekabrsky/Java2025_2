package ru.urfu

import ru.urfu.loader.PlayerLoader
import ru.urfu.resolver.Resolver
import ru.urfu.visual.ChartDrawer

fun main() {
    val path = "src/main/resources/fakePlayers.csv"
    val players = PlayerLoader.loadPlayersFromCSV(path)
    val resolver = Resolver(players)

    println("Number of players without an agency: ${resolver.getCountWithoutAgency()}")
    println("Maximum number of goals scored by a defender: ${resolver.getMaxDefenderGoalsCount()}")
    println("Position of the most expensive German player: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Team with the highest average number of red cards per player: ${resolver.getTheRudestTeam()}")

    ChartDrawer.drawGoalsVsValue(players)
}
