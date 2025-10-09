package ru.urfu.data.repository;

import ru.urfu.data.dataSource.LocalDataSource;
import ru.urfu.data.dataSource.RemoteDataSource;
import ru.urfu.domain.model.Player;
import ru.urfu.domain.repository.IPlayersRepository;

import java.util.List;

public class PlayersRepository implements IPlayersRepository {
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private List<Player> cachedPlayers = List.of();

    public PlayersRepository() {
        this.remoteDataSource = new RemoteDataSource();
        this.localDataSource = new LocalDataSource();
    }

    private void initializeData(String link) {
        if (!remoteDataSource.isFileExists()) {
            System.out.println("📥 Локальный файл не найден, загружаем...");
            remoteDataSource.downloadCsvFile(link);
        }

        // Загружаем данные из локального файла
        cachedPlayers = localDataSource.loadPlayersFromCsv();
        System.out.println("✅ Загружено игроков: " + cachedPlayers.size());
    }

    @Override
    public List<Player> getPlayers(String link) {
        if (cachedPlayers.isEmpty()) {
            initializeData(link);
        }
        return cachedPlayers;
    }
}
