package ru.urfu;

import ru.urfu.drawer.ChartDrawer;
import ru.urfu.model.Player;
import ru.urfu.parser.CsvParser;

import javax.swing.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        List<Player> players = CsvParser.parseCsvToList("src/main/resources/fakePlayers.csv");

        // Resolver playerResolver = new Resolver(players);

        ChartDrawer chartDrawer = new ChartDrawer(players);
        frame.add(chartDrawer.getChartPanel());

        frame.setVisible(true);
    }
}