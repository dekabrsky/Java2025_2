package ru.urfu.resolver

import ru.urfu.model.Player

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getMaxDefenderGoalsCount(): Int {
        return players
            .filter { it.position.equals("DEFENDER", ignoreCase = true) }
            .maxOfOrNull { it.goals } ?: 0
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality.equals("Germany", ignoreCase = true) }
            .maxByOrNull { it.transferCost }
            ?.position
            ?: "Not found"
    }

    override fun getTheRudestTeam(): String {
        return players
            .groupBy { "${it.team} (${it.city})" }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.map { it.redCards }.average()
            }
            .maxByOrNull { it.value }
            ?.key ?: "Not found"
    }
}
