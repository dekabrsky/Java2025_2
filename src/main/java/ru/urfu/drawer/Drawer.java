package main.java.ru.urfu.drawer;

import main.java.ru.urfu.model.Position;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Drawer {
    public void drawDiagramPlayersByPosition(Map<Position, Long> positionsCount) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        positionsCount.forEach((pos, count) ->
                dataset.setValue(pos.getTitleRu(), count)
        );

        JFreeChart chart = ChartFactory.createPieChart("Распределение по позициям", dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                new DecimalFormat("0"),
                new DecimalFormat("0.00%")
        ));

        plot.setSimpleLabels(true);
        plot.setLabelBackgroundPaint(Color.WHITE);

        ChartFrame frame = new ChartFrame("Распределение по позициям", chart);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}
