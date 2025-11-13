package ru.urfu.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ru.urfu.data.model.PlayerEntity;

import java.sql.SQLException;
import java.util.List;

public class PlayerDatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:players.db";
    private final Dao<PlayerEntity, Integer> playersDao;

    public PlayerDatabaseManager() {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL);
            playersDao = DaoManager.createDao(connectionSource, PlayerEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, PlayerEntity.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePlayer(List<PlayerEntity> player) throws SQLException {
        playersDao.create(player);
    }

    public List<PlayerEntity> getAllPlayers() throws SQLException {
        return playersDao.queryForAll();
    }
}
