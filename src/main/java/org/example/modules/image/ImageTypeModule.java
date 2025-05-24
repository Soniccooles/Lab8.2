package org.example.modules.image;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class ImageTypeModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        String lower = filePath.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                || lower.endsWith(".png") || lower.endsWith(".gif");
    }

    @Override
    public String getDescription() {
        return "Показывает расширение и размер изображения";
    }

    @Override
    public void process(String filePath) throws Exception {
        File file = new File(filePath);
        String type = filePath.substring(filePath.lastIndexOf(".") + 1).toUpperCase();
        System.out.printf("Image type: %s, Size: %d bytes%n", type, file.length());
    }
}
