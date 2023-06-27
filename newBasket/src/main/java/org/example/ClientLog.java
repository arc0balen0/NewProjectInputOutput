package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class ClientLog {
    private List<String> log;

    public ClientLog() {
        this.log = new ArrayList<>();
    }

    public void log(int productNum, int amount) {
        log.add(productNum + "," + amount);
    }

    public void exportAsCSV(File csvFile) {
        try (PrintWriter writer = new PrintWriter(csvFile)) {
            writer.println("productNum,amount");
            for (String entry : log) {
                writer.println(entry);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка сохранения лога в файл.");
            e.printStackTrace();
        }
    }
}