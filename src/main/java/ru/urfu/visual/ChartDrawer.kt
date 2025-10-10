package ru.urfu.visual

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import ru.urfu.model.Player
import javax.swing.JFrame

object ChartDrawer {
    fun asdasdGoalsVsValue(players: List<Player>) {
        val series = XYSeries("FORWARD")

        players.filter { it.position.equals("FORWARD", ignoreCase = true) }
            .forEach { player ->
                series.add(player.transferCost.toDouble(), player.goals.toDouble())
            }

        val dataset = XYSeriesCollection(series)

        val chart: JFreeChart = ChartFactory.createScatterPlot(
            "Голы vs Трансферная стоимость (FORWARD)",
            "Трансферная стоимость (€)",
            "Голы",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        )

        val frame = JFrame("График")
        frame.contentPane = ChartPanel(chart)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }
}
