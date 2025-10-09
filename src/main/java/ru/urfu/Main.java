package ru.urfu;

import ru.urfu.data.repository.PlayersRepository;
import ru.urfu.domain.useCase.GetPlayersUseCase;
import ru.urfu.presentation.controller.Controller;
import ru.urfu.presentation.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        var console = new ConsoleView();
        var getPlayersUseCase = new GetPlayersUseCase(new PlayersRepository());

        var controller = new Controller(console, getPlayersUseCase);
        controller.start();
    }
}