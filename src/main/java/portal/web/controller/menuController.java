package portal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import portal.business.Menuitem;
import portal.repository.MenuitemRepository;

@Controller
public class menuController {
    @Autowired
    private MenuitemRepository menuitemRepository;

    @GetMapping("/showmenu")
    public String showmenu(Model model){
        List<Menuitem> mainmenu=menuitemRepository.findByMenuid(1);
        for (Menuitem menuitem : mainmenu) {
            menuitem.setChild(menuitemRepository.findByParentid(menuitem.getId()));
        }
        model.addAttribute("menu", mainmenu);
        return "showmenu";
    }

    
}
