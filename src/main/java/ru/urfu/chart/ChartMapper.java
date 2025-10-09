package ru.urfu.chart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;

import java.util.*;

public class ChartMapper {
    public static XYSeriesCollection mapDataToChart(List<Player> players) {
        Map<Position, List<Integer>> positionToGoals = new HashMap<>();

        for (Player player : players) {
            Position position = player.position();
            int yellowCards = player.goals();

            positionToGoals.computeIfAbsent(position, k -> new ArrayList<>()).add(yellowCards);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        int seriesIndex = 0;
        for (Map.Entry<Position, List<Integer>> entry : positionToGoals.entrySet()) {
            Position position = entry.getKey();
            List<Integer> cardsList = entry.getValue();

            double average = cardsList.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);

            XYSeries series = new XYSeries(position);
            series.add(seriesIndex++, average);
            dataset.addSeries(series);
        }

        return dataset;
    }
}

