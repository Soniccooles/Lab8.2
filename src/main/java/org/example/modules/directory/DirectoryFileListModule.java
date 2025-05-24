package org.example.modules.directory;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Component
public class DirectoryFileListModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return Files.isDirectory(Path.of(filePath));
    }

    @Override
    public String getDescription() {
        return "List files in directory";
    }

    @Override
    public void process(String filePath) throws Exception {
        File[] files = new File(filePath).listFiles();
        if (files != null) {
            System.out.println("Список файлов:");
            Arrays.stream(files)
                    .map(File::getName)
                    .forEach(System.out::println);
        }
    }
}
