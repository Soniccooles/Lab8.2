package org.example.modules.text;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class LinesCountModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "Считает количество строк в файле";
    }

    @Override
    public void process(String filePath) throws IOException {
        long lineCount = Files.lines(Paths.get(filePath)).count();
        System.out.println("Количество строк: " + lineCount);
    }
}