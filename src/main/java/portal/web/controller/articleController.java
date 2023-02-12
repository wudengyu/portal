package portal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import portal.business.Article;
import portal.repository.ArticleRepository;

@Controller
public class articleController {
    @Autowired
    private ArticleRepository articleRepository;
    
    @GetMapping("/article")
    public String article(Model model,@RequestParam int id){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        return "article";
    }
}
