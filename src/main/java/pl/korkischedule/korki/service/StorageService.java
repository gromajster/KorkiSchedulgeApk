package pl.korkischedule.korki.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.korkischedule.korki.Security.SecurityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private static final String BASIC_SAVE_PATH = "C:\\Users\\Kiwi\\Desktop\\";


    public void uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            String path = BASIC_SAVE_PATH + SecurityUtils.getLoggedUsername() + "\\" + fileName;

            Files.copy(is, Paths.get(path),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            String msg = String.format("Failed to store file", file.getName());

            throw new StorageException(msg, e);
        }

    }

    public byte[] findFileByName(String filename) throws IOException {
        Path path = Files.walk(Paths.get(BASIC_SAVE_PATH))
                .filter(Files::isRegularFile)
                .filter(file -> file.endsWith(filename))
                .findFirst().orElseThrow(RuntimeException::new);
        System.out.println(path);

        File file = path.toFile();

        return Files.readAllBytes(file.toPath());
    }
}
