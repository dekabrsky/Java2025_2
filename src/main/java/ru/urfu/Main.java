package ru.urfu;

import ru.urfu.presenter.controller.Controller;
import ru.urfu.presenter.view.ConsoleView;
import ru.urfu.resolver.Resolver;

public class Main {
    public static void main(String[] args) {
        final var resolver = new Resolver();
        final var view = new ConsoleView();
        final var controller = new Controller(view, resolver);
        controller.start();
    }
}