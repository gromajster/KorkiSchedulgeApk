package pl.korkischedule.korki.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.korkischedule.korki.Entity.Add;
import pl.korkischedule.korki.Entity.Category;
import pl.korkischedule.korki.Entity.TypeOfAdd;
import pl.korkischedule.korki.Repository.AddRepo;

import java.util.Set;

@Controller
@AllArgsConstructor
public class StudentController {

    private AddRepo addRepo;

    @GetMapping("/student")
    public String index(Model model, @RequestParam(value = "category", required = false) String category) {
        if (category != null) {
            if (!Category.contains(category)) return "redirect:/";
            Set<Add> adds = addRepo.findAllByCategoryAndTypeOfAdd(Category.valueOf(category), TypeOfAdd.STUDENT);
            model.addAttribute("adds", adds);
        }
        return "uczeń";
    }
}




