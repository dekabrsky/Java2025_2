package ru.urfu.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Map;

public class Drawer {
    public static void drawPieChart(Map<String, Long> data) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        data.forEach(dataset::setValue);

        var chart = ChartFactory.createPieChart("Распределение игроков по позициям",
                dataset);
        var chartPanel = new ChartPanel(chart);
        var frame = new JFrame();
        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
