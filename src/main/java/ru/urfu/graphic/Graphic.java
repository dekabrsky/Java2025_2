package ru.urfu.graphic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graphic {

    private static String getRussianPositionName(Position position) {
        return switch (position) {
            case GOALKEEPER -> "Вратарь";
            case DEFENDER -> "Защитник";
            case MIDFIELD -> "Полузащитник";
            case FORWARD -> "Нападающий";
        };
    }

    public static void showPositionDistributionChart(List<Player> players) {
        // данные для графика
        Map<Position, Long> positionCounts = players.stream()
                .collect(Collectors.groupingBy(Player::position, Collectors.counting()));

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        int totalPlayers = players.size();
        positionCounts.forEach((position, count) -> {
            String rusPos = getRussianPositionName(position);
            dataset.setValue(rusPos, count);
        });

        JFreeChart chart = ChartFactory.createPieChart(
                "Распределение игроков по позициям (Всего: " + totalPlayers + ")",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})",
                new DecimalFormat("0"),
                new DecimalFormat("0.0%")
        );
        plot.setLabelGenerator(labelGenerator);

        JFrame frame = new JFrame("Статистика позиций игроков");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static Map<Position, Long> getPositionDistribution(List<Player> players) {
        return players.stream()
                .collect(Collectors.groupingBy(Player::position, Collectors.counting()));
    }
}