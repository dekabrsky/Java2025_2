package ru.urfu.tests

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import ru.urfu.model.Player
import ru.urfu.resolver.Resolver



class ResolverTest {

    private val testPlayers = listOf(
        Player("A", "TeamA", "CityA", "Defender", "Germany", null, 1000000, 10, 2, 1, 1, 1),
        Player("B", "TeamB", "CityB", "Forward", "Russia", "Agency1", 8000000, 20, 10, 3, 0, 0),
        Player("C", "TeamC", "CityC", "Goalkeeper", "Germany", "Agency2", 12000000, 15, 0, 1, 2, 0)
    )

    private val resolver = Resolver(testPlayers)

    @Test
    fun testCountWithoutAgency() {
        assertEquals(1, resolver.getCountWithoutAgency())
    }

    @Test
    fun testMaxDefenderGoals() {
        assertEquals(2, resolver.getMaxDefenderGoalsCount())
    }

    @Test
    fun testMostExpensiveGermanPlayerPosition() {
        assertEquals("Goalkeeper", resolver.getTheExpensiveGermanPlayerPosition())
    }

    @Test
    fun testRudestTeam() {
        assertEquals("TeamA (CityA)", resolver.getTheRudestTeam())
    }
}
