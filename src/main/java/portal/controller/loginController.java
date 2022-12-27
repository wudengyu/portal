package portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @GetMapping("/login")
    public String gl(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,Model model){
        model.addAttribute("name",username);
        return "greeting";
    }
    
}
