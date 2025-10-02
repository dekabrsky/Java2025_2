package ru.urfu.visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import ru.urfu.resolver.PlayerResolver;

import javax.swing.*;
import java.awt.*;

public class ChartDrawer {

    private PlayerResolver playerResolver;

    public void setPlayerResolver(PlayerResolver playerResolver) {
        this.playerResolver = playerResolver;
    }

    public ChartDrawer(PlayerResolver playerResolver) {
        this.playerResolver = playerResolver;
    }

    private JFreeChart getStatisticsChart() {
        ChartDataMapper chartDataMapper = new ChartDataMapper();
        ChartData chartData = chartDataMapper.map(playerResolver.getPlayerList());

        JFreeChart chart = ChartFactory.createBarChart(
                "Статистика",
                "Страна",
                "Доля игроков, %",
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
