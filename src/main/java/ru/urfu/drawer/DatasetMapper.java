package ru.urfu.drawer;

import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class DatasetMapper  {
    public DefaultCategoryDataset map(List<ChartData> chartData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ChartData entry : chartData) {
            dataset.addValue(entry.sum(), entry.team(), entry.team());
        }
        return dataset;
    }
}