package org.example.modules.text;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CharFrequencyModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "SПоказывает частоту встречаемости символа в файле";
    }

    @Override
    public void process(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));

        Map<Character, Long> frequency = content.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Частота:");
        frequency.forEach((k, v) -> System.out.printf("'%s': %d%n", k, v));
    }
}