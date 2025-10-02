package ru.urfu.io;

import ru.urfu.model.Position;
import ru.urfu.model.Player;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Color;

public class GraphDrawer {
	static public Map<Position, Integer> prepareData(List<Player> players) {
		Map<Position, Integer> out = new HashMap();
		Integer count;
		for (Player p: players) {
			count = out.get(p.position());
			if(count == null) {
				count = 0;
			}
			count ++;
			out.put(p.position(), count);
		}
		for (Position pos: Position.values()) {
			if(out.get(pos) == null) {
				out.put(pos, 0);
			}
		}
		return out;
	}

	static public void displayGraph(List<Player> players) {
		Map<Position, Integer> data = prepareData(players);
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

		data.forEach((pos, count) -> dataset.setValue(pos.getTitle(), count));

		JFreeChart chart = ChartFactory.createPieChart(
			"Распределение по позициям", dataset, true, true, false
		);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelGenerator(
			new StandardPieSectionLabelGenerator(
				"{0}: {1} ({2})",
				new DecimalFormat("0"),
				new DecimalFormat("0.00%")
			)
		);
		plot.setSimpleLabels(true);
		plot.setLabelBackgroundPaint(Color.WHITE);

		ChartFrame frame = new ChartFrame("Распределение по позициям", chart);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}

	static public void displayGraph3D(List<Player> players) {
		Map<Position, Integer> data = prepareData(players);
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

		data.forEach((pos, count) -> dataset.setValue(pos.getTitle(), count));

		JFreeChart chart = ChartFactory.createPieChart3D(
			"Распределение по позициям", dataset, true, true, false
		);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelGenerator(
			new StandardPieSectionLabelGenerator(
				"{0}: {1} ({2})",
				new DecimalFormat("0"),
				new DecimalFormat("0.00%")
			)
		);
		plot.setSimpleLabels(true);
		plot.setLabelBackgroundPaint(Color.WHITE);
		plot.setStartAngle( 270 );
		plot.setForegroundAlpha( 0.60f );
		plot.setInteriorGap( 0.02 );

		ChartFrame frame = new ChartFrame("Распределение по позициям", chart);
		frame.pack();
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}
