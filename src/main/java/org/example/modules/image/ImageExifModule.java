package org.example.modules.image;

import org.example.modules.FileProcessor;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageExifModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        String lower = filePath.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                || lower.endsWith(".png");
    }

    @Override
    public String getDescription() {
        return "Показывает EXIF метаданные";
    }

    @Override
    public void process(String filePath) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(new File(filePath));

        System.out.println("EXIF метаданные:");
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.printf("%s: %s%n", tag.getTagName(), tag.getDescription());
            }
        }
    }
}
