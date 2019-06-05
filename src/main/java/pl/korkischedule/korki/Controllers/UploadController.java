package pl.korkischedule.korki.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.korkischedule.korki.service.StorageException;
import pl.korkischedule.korki.service.StorageService;

@Controller
public class UploadController {


    private static final String BASIC_SAVE_PATH = "C:\\Users\\Kiwi\\Desktop\\";
    private StorageService storageService;

    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/doUpload",
            consumes = {"multipart/form-data"})
    public String upload(@RequestParam MultipartFile file) {

        storageService.uploadFile(file);

        return "redirect:/profile/fileUser";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {

        return "redirect:/profile/fileUser";
    }


}
