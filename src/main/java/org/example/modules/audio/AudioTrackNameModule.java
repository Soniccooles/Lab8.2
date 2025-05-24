package org.example.modules.audio;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.Map;

@Component
public class AudioTrackNameModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Показывает название аудиофайла mp3";
    }

    @Override
    public void process(String filePath) throws Exception {
        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(new File(filePath));
        Map<?, ?> properties = fileFormat.properties();
        String title = (String) properties.get("title");
        System.out.println("Название файла: " + (title != null ? title : "Без названия"));
    }
}
