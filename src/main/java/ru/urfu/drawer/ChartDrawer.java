package ru.urfu.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import ru.urfu.model.Player;
import ru.urfu.resolver.Resolver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartDrawer {

    private final List<Player> players;

    public ChartDrawer(List<Player> players) {
        this.players = players;
    }

    private JFreeChart getStatisticsChart() {
        Resolver chartDataResolver = new Resolver(players);
        List<ChartData> chartData = chartDataResolver.getTopNTeams();

        JFreeChart chart = ChartFactory.createBarChart(
                "топ-10 команд с наивысшей суммарной трансферной стоимостью",
                "команда",
                "стоймость, %",
                new DatasetMapper().map(chartData)
        );
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

    public JPanel getChartPanel() {

        JFreeChart chart = getStatisticsChart();

        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));

        return panel;
    }

}