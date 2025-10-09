package ru.urfu.data.dataSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class RemoteDataSource {
    //private static final String CSV_URL = "https://drive.usercontent.google.com/u/0/uc?id=1L1AGM6AdjSj__6FzdKYNtsfwKgHuQp7A&export=download";
    private static final String LOCAL_FILE = "players.csv";

    public void downloadCsvFile(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, Paths.get(LOCAL_FILE), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Файл успешно загружен");
            }
        } catch (IOException e) {
            System.out.println("Ошибка загрузки файла");
        }
    }

    public boolean isFileExists() {
        return Files.exists(Paths.get(LOCAL_FILE));
    }
}
