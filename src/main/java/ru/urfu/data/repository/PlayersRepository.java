package ru.urfu.data.repository;

import com.google.inject.Inject;
import ru.urfu.data.dataSource.LocalDataSource;
import ru.urfu.data.dataSource.RemoteDataSource;
import ru.urfu.data.db.PlayerDatabaseManager;
import ru.urfu.data.model.PlayerEntity;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.repository.IPlayersRepository;

import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class PlayersRepository implements IPlayersRepository {
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final PlayerDatabaseManager playerDatabaseManager;

    private List<Player> cachedPlayers = List.of();

    @Inject
    public PlayersRepository(
            RemoteDataSource remoteDataSource,
            LocalDataSource localDataSource,
            PlayerDatabaseManager playerDatabaseManager
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.playerDatabaseManager = playerDatabaseManager;
    }

    private void initializeData(String link) {
        if (!remoteDataSource.isFileExists()) {
            System.out.println("📥 Локальный файл не найден, загружаем...");
            remoteDataSource.downloadCsvFile(link);
        }

        // Загружаем данные из локального файла
        cachedPlayers = localDataSource.loadPlayersFromCsv();
        System.out.println("✅ Загружено игроков: " + cachedPlayers.size());

        try {
            playerDatabaseManager.savePlayer(
                    cachedPlayers.stream()
                            .map(player -> new PlayerEntity(player.name(), player.position(), player.agency(), player.goals())).toList()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Player> loadPlayers(String link) {
        if (cachedPlayers.isEmpty()) {
            initializeData(link);
        }
        return cachedPlayers;
    }

    @Override
    public List<Player> getPlayersFromDb() {
        if (cachedPlayers.isEmpty()) {
            try {
                cachedPlayers = playerDatabaseManager.getAllPlayers().stream().map(PlayerEntity::toPlayer).toList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cachedPlayers;
    }

    @Override
    public List<Player> getCachedPlayers() {
        return cachedPlayers;
    }
}
