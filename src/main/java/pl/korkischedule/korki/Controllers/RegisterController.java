package pl.korkischedule.korki.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.korkischedule.korki.Entity.UserEntity;
import pl.korkischedule.korki.Entity.UserRole;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Repository.UserRoleRepo;
import pl.korkischedule.korki.Security.SecurityUtils;

import java.io.File;
import java.util.Arrays;

@Controller
@AllArgsConstructor
public class RegisterController {

    private final static String DEFAULT_ROLE = "ROLE_USER";
    private UserRepo userRepo;
    private UserRoleRepo userRoleRepo;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity user, RedirectAttributes redirectAttributes) {
        UserEntity usernameChecker = userRepo.findByUsername(user.getUsername());
        if (usernameChecker != null) {
            redirectAttributes.addFlashAttribute("rdrmessage", "Nazwa użytkownika jest zajęta. Wybierz inny.");
            return "redirect:/register";
        }

        File file = new File("C:\\Users\\Kiwi\\Desktop\\" + user.getUsername());
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRole role = userRoleRepo.findByRole(DEFAULT_ROLE);
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println(SecurityUtils.isLoggedIn() + " " + SecurityUtils.getLoggedUsername());
        if (SecurityUtils.isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("user", new UserEntity());
        return "register";
    }

}
