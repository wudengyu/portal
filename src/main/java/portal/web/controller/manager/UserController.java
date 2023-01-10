package portal.web.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.repository.UserRepository;

@Controller
@RequestMapping("/manager")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public String getallusers(Model model){
        model.addAttribute("allUsers",userRepository.findAll());
        return "userlist";
    }
}
