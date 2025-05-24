package org.example.modules.directory;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileCountModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return Files.isDirectory(Path.of(filePath));
    }

    @Override
    public String getDescription() {
        return "Считает количество файлов в директории";
    }

    @Override
    public void process(String filePath) throws Exception {
        long count = Files.walk(Path.of(filePath))
                .filter(p -> p.toFile().isFile())
                .count();

        System.out.println("Количество файлов: " + count);
    }
}
