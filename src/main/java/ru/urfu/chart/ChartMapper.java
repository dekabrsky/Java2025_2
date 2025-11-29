package ru.urfu.chart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.urfu.model.Player;
import ru.urfu.model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartMapper {
    public static XYSeriesCollection mapDataToChart(List<Player> players) {
        Map<Position, List<Integer>> positionToGoals = new HashMap<>();

        for (Player player : players) {
            Position position = player.position();
            int goals = player.goals();

            positionToGoals.computeIfAbsent(position, k -> new ArrayList<>()).add(goals);
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
