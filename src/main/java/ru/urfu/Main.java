package main.java.ru.urfu;

// import main.java.ru.urfu.console.Communicator;

import main.java.ru.urfu.presentation.controller.Controller;
import main.java.ru.urfu.presentation.view.ConsoleView;
import main.java.ru.urfu.resolver.Resolver;

public class Main {
    public static void main(String[] args) {
        // Communicator.runChampionship();
        var console = new ConsoleView();
        var resolver = new Resolver();

        var controller = new Controller(console, resolver);
        controller.start();
    }
}