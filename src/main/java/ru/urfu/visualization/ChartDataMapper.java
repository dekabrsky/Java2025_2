package ru.urfu.visualization;

import ru.urfu.mapper.IMapper;
import ru.urfu.model.Player;
import ru.urfu.resolver.PlayerResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDataMapper implements IMapper<List<Player>, ChartData> {

    @Override
    public ChartData map(List<Player> players) {

        PlayerResolver playerResolver = new PlayerResolver(players);
        Map<String, Long> countriesToCount = playerResolver.getPlayersFromCountriesCount();
        double countPlayers = players.size();

        Map<String, Double> percentageMap = new HashMap<>();

        for (Map.Entry<String, Long> entry : countriesToCount.entrySet()) {
            percentageMap.put(entry.getKey(), 100 * entry.getValue().doubleValue() / countPlayers);
        }
        return new ChartData(percentageMap);
    }

}
