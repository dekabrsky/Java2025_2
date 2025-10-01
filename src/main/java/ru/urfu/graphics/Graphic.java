package ru.urfu.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import javax.swing.*;
import java.util.List;

public class Graphic {
    static XYSeriesCollection createDataset(List<Player> players) {
        XYSeries series = new XYSeries("Нападающие");
        players.stream()
                .filter(player -> player.position() == Position.FORWARD)
                .forEach(p -> series.add(p.cost(), p.goals()));
        return new XYSeriesCollection(series);
    }

    public static void showGraphic(List<Player> players) {
        var dataset = createDataset(players);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Голы vs Стоимость (FORWARD)",
                "Трансферная стоимость",
                "Голы",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        JFrame frame = new JFrame("Диаграмма");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
