package uz.app.authapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.app.authapp.db.UserDAO;
import uz.app.authapp.entity.User;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDAO userDAO;
    @GetMapping("/signUp")
    public String signUp() {return "signUp";}

    @GetMapping("/signIn")
    public String signIn() {
//        model.addAttribute("_csrf", new DefaultCsrfToken("","",""));
        return "signIn";
    }


    @PostMapping("/signUp")
    public String signUp(@ModelAttribute User user){
        // tekshiruvlar
        user.setRole("USER");
        userDAO.save(user);
        return "redirect:/";
    }
}
