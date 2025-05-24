package org.example.modules.audio;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;

@Component
public class AudioYearModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        return filePath.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "Показывает год выпуска аудиофайла";
    }

    @Override
    public void process(String filePath) throws Exception {
        AudioFile audioFile = AudioFileIO.read(new File(filePath));
        String year = audioFile.getTag().getFirst(FieldKey.YEAR);
        System.out.println("Год выпуска: " + (year.isEmpty() ? "не указан" : year));
    }
}