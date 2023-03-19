package portal.web.controller.privated;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.business.Article;
import portal.business.ArticleStatus;
import portal.business.Employees;
import portal.repository.ArticleRepository;
import portal.repository.EmployeesRepository;

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
        model.addAttribute("userslist",users);
        model.addAttribute("statuses", ArticleStatus.values());
    }

    @GetMapping
    public String search(Model model,Principal principal,@PageableDefault(page=1) Pageable paging){
        Page<Article> articles=articleRepository.findByUsername(principal.getName(), paging);
        model.addAttribute("articles", articles);
        return "article/personal";
    }

    @GetMapping("/edit/{id}")
    public String editarticle(Model model,@PathVariable int id,Principal principal){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        model.addAttribute("principal",principal);
        return "article/edit";
    }

}
