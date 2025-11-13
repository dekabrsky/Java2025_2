package ru.urfu.domain.repository;

import ru.urfu.domain.model.Player;

import java.util.List;

public interface IPlayersRepository {
    List<Player> loadPlayers(String link);

    List<Player> getPlayersFromDb();

    List<Player> getCachedPlayers();
}
