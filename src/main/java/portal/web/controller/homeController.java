package portal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.business.Column;
import portal.business.Menuitem;
import portal.repository.ArticleRepository;
import portal.repository.ColumnRepository;
import portal.repository.MenuitemRepository;

@Controller
@RequestMapping(path={"/","/home"})
public class homeController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private MenuitemRepository menuitemRepository;
    
    @GetMapping
    public String home(Model model){
        List<Menuitem> mainmenu=menuitemRepository.findByMenuid(1);
        for (Menuitem menuitem : mainmenu) {
            menuitem.setChild(menuitemRepository.findByParentid(menuitem.getId()));
        }
        model.addAttribute("menu", mainmenu);
        List<Column> homepageColumn=columnRepository.findAllLeaf();
        for(Column column:homepageColumn){
            column.setArticlelist(articleRepository.loadByColumnId(column.getId(), 0, 10));
        }
        model.addAttribute("columns", homepageColumn);
        return "home";
    }
}
