package org.example.modules.directory;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DirectoryFilesSizeModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return Files.isDirectory(Path.of(filePath));
    }

    @Override
    public String getDescription() {
        return "Считает суммарный размер всех файлов в директории";
    }

    @Override
    public void process(String filePath) throws Exception {
        long size = Files.walk(Path.of(filePath))
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();

        System.out.printf("Суммарный размер файлов в директории: %.2f MB%n", size / (1024.0 * 1024.0));
    }
}
