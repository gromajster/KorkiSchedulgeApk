package pl.korkischedule.korki.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.korkischedule.korki.Entity.Add;
import pl.korkischedule.korki.Entity.UserEntity;
import pl.korkischedule.korki.Repository.AddRepo;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Security.SecurityUtils;


@Controller
@AllArgsConstructor
@RequestMapping("/add")

public class AddController {

    private UserRepo userRepo;
    private AddRepo addRepo;

    @PostMapping
    public String saveAdd(@ModelAttribute Add add, RedirectAttributes redirectAttributes) {
        UserEntity user = userRepo.findByUsername(SecurityUtils.getLoggedUsername());
        add.setOwner(user);
        System.out.println(add.toString());
        addRepo.save(add);
        return "redirect:/profile";
    }

    @GetMapping("/change-status/{id}")
    public String changeUserEnabled(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Add add = addRepo.findById(Long.parseLong(id)).orElseThrow(RuntimeException::new);
        add.setExpose(!add.isExpose());
        addRepo.save(add);
        redirectAttributes.addFlashAttribute("rdrmessage", "Pomyślnie zmieniono status.");
        return "redirect:/profile";
    }
}
