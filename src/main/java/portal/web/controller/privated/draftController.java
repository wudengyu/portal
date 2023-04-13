package portal.web.controller.privated;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.business.Article;
import portal.repository.ArticleRepository;

@Controller
@RequestMapping("/privated/draft")
public class draftController {
    
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public String searchdraft(Model model,Principal principal){
        List<Article> articles=articleRepository.findByUsernameAndStatus(principal.getName(),1);
        model.addAttribute("articles", articles);
        return "article/draft";
    }

    @GetMapping("/edit/{id}")
    public String editarticle(Model model,@PathVariable int id,Principal principal){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        model.addAttribute("principal",principal);
        return "article/edit";
    }

}
