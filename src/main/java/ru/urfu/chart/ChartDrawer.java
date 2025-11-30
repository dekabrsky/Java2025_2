package ru.urfu.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartDrawer {
    public static void showChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                "Среднее количество голов по позициям",
                "Позиция",
                false,
                "Среднее кол-во голов",
                dataset
        );

        ChartFrame frame = new ChartFrame("Среднее число голов по позиции", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
