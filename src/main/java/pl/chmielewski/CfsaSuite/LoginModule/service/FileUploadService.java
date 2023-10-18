package pl.chmielewski.CfsaSuite.LoginModule.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class FileUploadService {

    public void uploadFile(MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String newFileName = "Nowy_pliczek" + getFileExtension(Objects.requireNonNull(originalFileName));

        file.transferTo(new File(createLocationForFile(file) + "\\" + newFileName));
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public Path createLocationForFile(MultipartFile file) throws IOException {
        LocalDate currentDate = LocalDate.now();
        String targetDirectory = "C:\\Cfsa_suite\\" + currentDate.getYear() + "\\" + currentDate.getMonthValue() + "\\" + currentDate.getDayOfMonth() + "\\";
        Path targetPath = Paths.get(targetDirectory);

        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }
        return targetPath;
    }

}
