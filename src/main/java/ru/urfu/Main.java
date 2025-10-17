package ru.urfu;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.urfu.data.repository.PlayersRepository;
import ru.urfu.di.AppModule;
import ru.urfu.domain.useCase.GetPlayersUseCase;
import ru.urfu.presentation.controller.Controller;
import ru.urfu.presentation.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        var controller = injector.getInstance(Controller.class);
        controller.start();
    }
}