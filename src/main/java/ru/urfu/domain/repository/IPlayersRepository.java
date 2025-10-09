package ru.urfu.domain.repository;

import ru.urfu.domain.model.Player;

import java.util.List;

public interface IPlayersRepository {
    List<Player> getPlayers(String link);
}
