package ru.urfu.graphics;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import ru.urfu.model.Player;

import javax.swing.*;
import java.util.List;

public class Graphic {
    static XYSeriesCollection createDataset(List<Player> players) {
        XYSeries series = new XYSeries("Нападающие");
        players
            .stream()
            .filter(player -> player.getPosition().equals("FORWARD"))
            .forEach(p -> series.add(p.getTransferCost(), p.getGoals()));
        return new XYSeriesCollection(series);
    }

    public static void showGraphic(List<Player> players) {
        var dataset = createDataset(players);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Зависимость количества забитых голов от трансферной стоимости для нападающих",
            "Трансферная стоимость",
            "Голы",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        JFrame frame = new JFrame("Диаграмма зависимости количества забитых голов от трансферной стоимости для нападающих");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
