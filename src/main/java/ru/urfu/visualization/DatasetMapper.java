package ru.urfu.visualization;

import org.jfree.data.category.DefaultCategoryDataset;
import ru.urfu.mapper.IMapper;

import java.util.Map;

public class DatasetMapper implements IMapper<ChartData, DefaultCategoryDataset> {
    @Override
    public DefaultCategoryDataset map(ChartData chartData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : chartData.data().entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), entry.getKey());
        }
        return dataset;
    }
}
