package ru.urfu.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.ToString;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.model.Position;

@DatabaseTable(tableName = "players")
@Getter
@ToString
public class PlayerEntity {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private Position position;
    @DatabaseField
    private String agency;
    @DatabaseField
    private int goals;

    public PlayerEntity() {}

    public PlayerEntity(
            String name,
            Position position,
            String agency,
            int goals
    ) {
        this.name = name;
        this.position = position;
        this.agency = agency;
        this.goals = goals;
    }

    public Player toPlayer() {
        return new Player(
                name,
                position,
                agency,
                goals
        );
    }
}
