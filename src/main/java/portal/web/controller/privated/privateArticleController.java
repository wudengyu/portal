package portal.web.controller.privated;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.business.Article;
import portal.business.ArticleStatus;
import portal.business.Employees;
import portal.repository.ArticleRepository;
import portal.repository.EmployeesRepository;
import portal.view.ArticleSearch;

@Controller
@RequestMapping("/privated/article")
public class privateArticleController {
    
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @ModelAttribute
    public void articleModel(Model model,Principal principal){
        List<Employees> users=employeesRepository.findAll();
        System.out.println(principal);
        model.addAttribute("userslist",users);
        model.addAttribute("statuses", ArticleStatus.values());

    }




    @GetMapping("/search")
    public String search(Model model,Principal principal){
        
        return "article/search";
    }

    @PostMapping("/search")
    public String search(Model model,Principal principal,ArticleSearch articleSearch){
        return "article/search";
    }

    @GetMapping("/edit/{id}")
    public String editarticle(Model model,@PathVariable int id,Principal principal){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        model.addAttribute("principal",principal);
        return "article/edit";
    }

}
