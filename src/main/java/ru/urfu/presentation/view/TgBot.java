package ru.urfu.presentation.view;

import com.google.inject.Inject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.urfu.presentation.presenter.Presenter;
import ru.urfu.presentation.presenter.PresenterFactory;

import java.util.Scanner;

public class TgBot extends TelegramLongPollingBot implements ViewInterface {

    private Long chatId = 0L;

    private final Presenter presenter;

    @Inject
    public TgBot(PresenterFactory presenterFactory) {
        presenter = presenterFactory.create(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update.getMessage());
        }
    }

    private void handleMessage(Message message) {
        String userMessage = message.getText();
        chatId = message.getChatId();

        presenter.onNewCommand(userMessage);
    }

    @Override
    public String getBotUsername() {
        return "ExampleCsvStatsBot";
    }

    @Override
    public String getBotToken() {
        // hide me
        return "";
    }

    @Override
    public void showWelcome() {
        sendMessage("Команды: \n" +
                "LOAD - выбрать файл для анализа\n" +
                "1 - решение залачи 1 \n" +
                "2 - решение задачи 2\n" +
                "CHART - показать график\n" +
                "EXIT - завершить работу\n");
    }

    @Override
    public void showEnterCommand() {
        sendMessage("Введите команду: ");
    }

    @Override
    public void showPlayersError() {
        sendMessage("Игроки не загружены");
    }

    @Override
    public void showCommandError() {
        sendMessage("Команда не распознана");
    }

    @Override
    public void showSelectFile() {
        sendMessage("Выберите файл: ");
    }

    @Override
    public void showCountWithoutAgency(int count) {
        sendMessage("Количество игроков без агентства: " + count);
    }

    @Override
    public void showMaxDefenderGoalsCount(int score) {
        sendMessage("Максимум голов защитника: " + score);
    }

    private void sendMessage(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            // Логирование ошибки
        }
    }
}
