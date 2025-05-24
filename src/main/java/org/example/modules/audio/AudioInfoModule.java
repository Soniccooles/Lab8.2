package org.example.modules.audio;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Map;

@Component
public class AudioInfoModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Показывает информацию об MP3 файле";
    }

    @Override
    public void process(String filePath) throws Exception {
        File file = new File(filePath);
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
        Map<String, Object> properties = fileFormat.properties();

        System.out.println("Информация об MP3 файле:");
        System.out.println("Размер: " + file.length() + " bytes");
        System.out.println("Название: " + properties.getOrDefault("title", "Без названия"));
        System.out.println("Артист: " + properties.getOrDefault("author", "Автора нет"));
        System.out.println("Длительность: " + (Long)properties.get("duration") / 1_000_000 + " секунд");
    }
}
