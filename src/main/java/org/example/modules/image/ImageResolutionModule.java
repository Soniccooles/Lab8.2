package org.example.modules.image;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class ImageResolutionModule implements FileProcessor {

    @Override
    public boolean supports(String filePath) {
        String lower = filePath.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                || lower.endsWith(".png") || lower.endsWith(".gif");
    }

    @Override
    public String getDescription() {
        return "Показывает размерность изображения";
    }

    @Override
    public void process(String filePath) throws Exception {
        BufferedImage image = ImageIO.read(new File(filePath));
        System.out.printf("Размер изображения: %d x %d пикселей %n",
                image.getWidth(), image.getHeight());
    }
}