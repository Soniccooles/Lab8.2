package org.example.modules.text;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParserModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Парсит текст, оставляя только слова и пробелы между ними";
    }

    @Override
    public void process(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));

        List<String> words = Arrays.stream(content.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());

        System.out.println("Преобразованный текст:");
        words.forEach(System.out::println);
    }
}