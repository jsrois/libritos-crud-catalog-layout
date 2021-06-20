package net.jsrois.libritos.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class BookCoverService {

    private final Logger logger = LoggerFactory.getLogger(BookCoverService.class);

    public void saveBookCover(Long id, String imageName, MultipartFile imageFile) throws IOException {
        Path uploadPath = Paths.get("data" +
                "/" + id );

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            logger.info(String.format("Creating folder: %s", uploadPath));
        }

        try (InputStream inputStream = imageFile.getInputStream()) {
            Path filePath = uploadPath.resolve(imageName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info(String.format("Uploaded file: %s", filePath));
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + imageName, ioe);
        }
    }
}
