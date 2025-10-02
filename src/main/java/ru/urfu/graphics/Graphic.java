package ru.urfu.graphics;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import ru.urfu.model.Position;

public class Graphic {
    public void drawDiagramPlayersByPosition(Map<Position, Long> positionsCount) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        String title = "Распределение позиций среди игроков";

        positionsCount.forEach((pos, count) ->
                dataset.setValue(pos.getTitleRu(), count)
        );

        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                new DecimalFormat("0"),
                new DecimalFormat("0.00%")
        ));

        plot.setLabelBackgroundPaint(Color.WHITE);

        ChartFrame frame = new ChartFrame(title, chart);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}