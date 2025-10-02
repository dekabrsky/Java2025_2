package ru.urfu.mapper;

import ru.urfu.model.Player;
import ru.urfu.model.PlayerBuilder;
import ru.urfu.model.Position;

public class PlayerMapper implements IMapper<String, Player> {
    @Override
    public Player map(String s) {
        String[] data = s.split(";");
        return new PlayerBuilder()
                .name(data[0])
                .team(data[1])
                .city(data[2])
                .position(Position.valueOf(data[3]))
                .nationality(data[4])
                .agency(data[5])
                .transferCost(Integer.parseInt(data[6]))
                .participations(Integer.parseInt(data[7]))
                .goals(Integer.parseInt(data[8]))
                .assists(Integer.parseInt(data[9]))
                .yellowCards(Integer.parseInt(data[10]))
                .redCards(Integer.parseInt(data[11]))
                .build();
    }
}
