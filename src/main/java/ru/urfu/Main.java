package ru.urfu;

import ru.urfu.data.database.DatabaseHandler;
import ru.urfu.domain.repository.CountryRepository;
import ru.urfu.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CountryRepository repository = new DatabaseHandler();
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(repository);
            window.setVisible(true);
        });
    }
}