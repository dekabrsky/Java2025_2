package ru.urfu;

import ru.urfu.console.Communicator;
import ru.urfu.presentation.controller.Controller;
import ru.urfu.presentation.view.ConsoleView;
import ru.urfu.resolver.Resolver;

public class Main {
    public static void main(String[] args) {
        //Communicator.runChampionship();

        var console = new ConsoleView();
        var resolver = new Resolver();

        // Dependency injection

        var controller = new Controller(console, resolver);
        controller.start();
    }
}