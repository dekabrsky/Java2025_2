package ru.urfu;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.urfu.di.AppModule;
import ru.urfu.presentation.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        injector.getInstance(ConsoleView.class);
    }
}