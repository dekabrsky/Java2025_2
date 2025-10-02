package ru.urfu;

import ru.urfu.io.IParser;
import ru.urfu.io.CsvPlayerParser;
import ru.urfu.model.Player;
import ru.urfu.resolver.PlayerResolver;
import ru.urfu.visualization.ChartDrawer;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        IParser<Player> parser = new CsvPlayerParser();
        List<Player> players = parser.parse("src/main/resources/fakePlayers.csv");
        PlayerResolver playerResolver = new PlayerResolver(players);

        ChartDrawer chartDrawer = new ChartDrawer(playerResolver);
        frame.add(chartDrawer.getChartPanel());

        frame.setVisible(true);
    }
}