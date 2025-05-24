package org.example.service;

import org.example.modules.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;


@Service
public class FileProcessingService {

    @Autowired
    private List<FileProcessor> processors;

    public void processFile(String filepath) throws Exception {
        System.out.println("Обработка началась...\n");

        List<FileProcessor> supportedProcessors = processors.stream().filter(p -> p.supports(filepath)).toList();

        if (supportedProcessors.isEmpty()) {
            System.out.println("Не найдено ни одного модуля для обработки. Game over.");
        }

        System.out.println("Найденные обработчики: ");
        for (int i = 0; i < supportedProcessors.size(); i++) {
            System.out.println(i+1 + ". " + supportedProcessors.get(i).getDescription());
        }

        System.out.println("Выберите номер обработчика.");
        Scanner scanner = new Scanner(System.in);
        int choice;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Введите число от 1 до 5.");
            return;
        }

        if (choice < 1 || choice > supportedProcessors.size()) {
            System.out.println("Укажите цифру от 1 до " + supportedProcessors.size());
        }

        FileProcessor selectedProcessor = supportedProcessors.get(choice - 1);
        selectedProcessor.process(filepath);
    }

}
