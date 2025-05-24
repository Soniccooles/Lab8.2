package org.example;

import org.example.service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileApplication implements CommandLineRunner {

    @Autowired
    private FileProcessingService processingService;

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу или каталогу.");
        }

        String filepath = args[0];
        processingService.processFile(filepath);
    }
}
