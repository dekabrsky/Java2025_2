package ru.urfu.graphics;

import ru.urfu.model.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Graphics {
    public static Map<Position, Long> makeMapping(List<Player> players) {
        return players
            .stream()
            .collect(Collectors.groupingBy(Player::position, Collectors.counting()));
    }

    static String posToStr(Position pos) {
        switch (pos) {
            case (Position.GOALKEEPER):
                return "Вратарь";
            case (Position.DEFENDER):
                return "Защитник";
            case (Position.MIDFIELD):
                return "Полузащитник";
            default:
                return "Нападающий";
        }
    }

    public static void showGraphics(Map<Position, Long> positionsCount) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        positionsCount.forEach((pos, cnt) ->
            dataset.setValue(posToStr(pos), cnt)
        );

        JFreeChart chart = ChartFactory.createPieChart("Распределение по позициям", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                new DecimalFormat("0"),
                new DecimalFormat("0.00%")
        ));


        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
