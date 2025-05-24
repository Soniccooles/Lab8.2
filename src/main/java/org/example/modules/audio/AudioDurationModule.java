package org.example.modules.audio;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Map;

@Component
public class AudioDurationModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Показывает длительность аудиофайла в секундах";
    }

    @Override
    public void process(String filePath) throws Exception {
        try {
            File file = new File(filePath);
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            Map<String, Object> properties = fileFormat.properties();

            if (properties.containsKey("duration")) {
                long microseconds = (Long) properties.get("duration");
                double seconds = microseconds / 1_000_000.0;
                System.out.printf("Длительность: %.2f секунд%n", seconds);
            } else {
                System.out.println("Длительность не доступна для данного файла");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при обработке аудиофайла: " + e.getMessage());
            throw e;
        }
    }
}