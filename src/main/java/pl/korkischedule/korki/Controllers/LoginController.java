package pl.korkischedule.korki.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/test/{testString}")
    public String test(@PathVariable String testString) {
        System.out.println(testString);
        return "index";
    }
}
