package ru.urfu;

import ru.urfu.chart.ChartDrawer;
import ru.urfu.chart.ChartMapper;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;
import ru.urfu.resolver.Resolver;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how GIGA IDE suggests fixing it.
        var parser = new CsvParser("/Users/denis/IdeaProjects/Java2025_2/players.csv");

        Resolver resolver = new Resolver(parser);

        System.out.println(resolver.getTeams());

        var chartData = ChartMapper.mapDataToChart(parser.parseCsvToList());
        ChartDrawer.showChart(chartData);
    }
}