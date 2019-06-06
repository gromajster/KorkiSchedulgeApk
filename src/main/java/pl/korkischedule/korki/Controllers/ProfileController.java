package pl.korkischedule.korki.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.korkischedule.korki.Entity.*;
import pl.korkischedule.korki.Repository.AddRepo;
import pl.korkischedule.korki.Repository.OpinionRepo;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Repository.YoutubeVideoRepo;
import pl.korkischedule.korki.Security.SecurityUtils;
import pl.korkischedule.korki.UserNotFoundException;
import pl.korkischedule.korki.service.EmailSender;
import pl.korkischedule.korki.service.StorageService;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {


    private UserRepo userRepo;
    private AddRepo addRepo;
    private OpinionRepo opinionRepo;
    private EmailSender emailSender;
    private static final String BASIC_SAVE_PATH = "C:\\Users\\Kiwi\\Desktop\\";
    private StorageService storageService;
    private YoutubeVideoRepo youtubeVideoRepo;

    @GetMapping("/{id}")
    public String userProfile(@PathVariable(value = "id") String sId, Model model) {
        final Long id;
        try {
            id = Long.valueOf(sId);

        } catch (NumberFormatException e) {
            throw new UserNotFoundException("UserNotFound");
        }


        userRepo.findById(id)
                .map(usr -> setUserAndAdds(usr, model))
                .orElseThrow(() -> new UserNotFoundException("UserEntity not found"));
        model.addAttribute("add", new Add());
        return "profile";
    }


    @GetMapping
    public String profileTeacher(Model model) {
        model.addAttribute("add", new Add());
        model.addAttribute("formEmail", new ContactEmail());
        model.addAttribute("opinion", new Opinion());
        UserEntity user = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        Set<Add> addsTeacher = addRepo.findAllByOwnerAndTypeOfAdd(user, TypeOfAdd.TEACHER);
        Set<Add> addsStudent = addRepo.findAllByOwnerAndTypeOfAdd(user, TypeOfAdd.STUDENT);
        model.addAttribute("opinions", user.getOpinions());
        model.addAttribute("addsTeacher", addsTeacher);
        model.addAttribute("addsStudent", addsStudent);
        return "profile";
    }


    @PostMapping("/opinion")
    public String saveOptinion(@ModelAttribute Opinion opinion) {
        opinionRepo.save(opinion);
        return "redirect:/profile/" + opinion.getReceiver().getId().toString();
    }

    @PostMapping("/send")
    public String sendEmail(@ModelAttribute ContactEmail contactEmail, RedirectAttributes redirectAttributes) {
        emailSender.sendEmail(contactEmail);
        redirectAttributes.addFlashAttribute("message", "Your email was has been send");
        return "redirect:/";
    }

    @GetMapping("/user-info")
    public String userDetails(Model model) {
        UserEntity user = userRepo.findByUsername(SecurityUtils.getLoggedUsername());

        model.addAttribute("user", user);
        return "user-info";
    }

    @PostMapping("/change")
    public String changeDetails(@ModelAttribute UserEntity user) {
        UserEntity backuser = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        backuser.setName(user.getName());
        backuser.setSurname(user.getSurname());
        backuser.setDescription(user.getDescription());
        backuser.setTelephone(user.getTelephone());
        backuser.setEmail(user.getEmail());
        userRepo.save(backuser);
        //userRepo.save(user);
        return "user-info";
    }

    @GetMapping("/fileUser")
    public String fileList(Model model) throws IOException {
        UserEntity user = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        model.addAttribute("listOfFiles", listFilesUsingDirectoryStream(BASIC_SAVE_PATH + SecurityUtils.getLoggedUsername()));
        model.addAttribute("youtubeVideo", new YoutubeVideo());
        model.addAttribute("listOfVideos", youtubeVideoRepo.findAllByOwnerVideo(user));
        return "fileuser";
    }

    @PostMapping("/fileUser")
    public String sendVideo(@ModelAttribute YoutubeVideo youtubeVideo) {
        UserEntity user = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        String link = youtubeVideo.getLink();
        link = link.substring(32);
        youtubeVideo.setLink(link);
        youtubeVideo.setOwnerVideo(user);
        youtubeVideoRepo.save(youtubeVideo);

        return "redirect:/profile/fileUser";
    }

    @GetMapping("/fileUser/{pathFile}")
    public ResponseEntity<byte[]> getPDF1(@PathVariable(value = "pathFile") String pathFile) throws IOException {


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));

        headers.add("content-disposition", "inline;filename=" + pathFile);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");


        byte[] file = storageService.findFileByName(pathFile);
        ResponseEntity<byte[]> response = new ResponseEntity<>(file, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}/fileUser")
    public String userProfileFiles(@PathVariable(value = "id") String sId, Model model) throws IOException {
        final Long id;
        try {
            id = Long.valueOf(sId);

        } catch (NumberFormatException e) {
            throw new UserNotFoundException("UserNotFound");
        }


        Optional<UserEntity> user = userRepo.findById(id);
        model.addAttribute("listOfFiles", listFilesUsingDirectoryStream(BASIC_SAVE_PATH + user.get().getUsername()));
        model.addAttribute("listOfVideos", youtubeVideoRepo.findAllByOwnerVideo(user.get()));
        return "fileuser";
    }

    public Set<String> listFilesUsingDirectoryStream(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(path.getFileName()
                            .toString());
                }
            }
        }

        return fileList;
    }


    private UserEntity setUserAndAdds(UserEntity user, Model model) {
        model.addAttribute("user", user);
        ContactEmail contactEmail = ContactEmail.builder()
                .destination(user.getEmail()).build();
        UserEntity creator = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        Opinion opinion = new Opinion(null, null, creator, user);
        model.addAttribute("opinion", opinion);
        model.addAttribute("formEmail", contactEmail);
        model.addAttribute("opinions", user.getOpinions());
        Set<Add> adds = addRepo.findAllByOwner(user);
        model.addAttribute("addsTeacher", adds.stream().filter(add -> add.getTypeOfAdd().equals(TypeOfAdd.TEACHER)).collect(Collectors.toList()));
        model.addAttribute("addsStudent", adds.stream().filter(add -> add.getTypeOfAdd().equals(TypeOfAdd.STUDENT)).collect(Collectors.toList()));
        return user;
    }

}
