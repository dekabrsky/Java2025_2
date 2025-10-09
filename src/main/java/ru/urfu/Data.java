package ru.urfu;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {
    public List<Player> records = new ArrayList<>();

    public void load_data() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/fakePlayers.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(new Player(values));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
}