package ru.urfu;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.urfu.di.AppModule;
import ru.urfu.presentation.view.TgBot;

public class TgApp {
    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new AppModule());

            var bot = injector.getInstance(TgBot.class);

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            System.out.println("Telegram бот запущен!");
        } catch (TelegramApiException e) {
            System.err.println("Ошибка запуска бота: " + e.getMessage());
        }
    }
}
