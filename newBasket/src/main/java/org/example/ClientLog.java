package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class ClientLog {
    private StringBuilder log;

    public ClientLog() {
        this.log = new StringBuilder();
    }

    public void log(int productNum, int amount) {
        log.append(productNum).append("\n").append(amount).append("\n");
    }

    public void exportAsCSV(File txtFile) {
        try (PrintWriter writer = new PrintWriter(txtFile)) {
            writer.println("productNum,amount");
            writer.print(log.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка сохранения журнала в файл.");
            e.printStackTrace();
        }
    }
}