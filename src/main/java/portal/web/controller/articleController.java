package portal.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.business.Article;
import portal.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class articleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/list/{columnid}")
    public String getList(Model model,@PathVariable int columnid,@PageableDefault Pageable paging){
        Page<Article> articles=articleRepository.loadByColumnId(columnid,paging);
        model.addAttribute("articles",articles);
        return "article/list";
    }
    
    @GetMapping("/edit/{id}")
    public String editarticle(Model model,@PathVariable int id,Principal principal){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        model.addAttribute("principal",principal);
        return "article/edit";
    }

    @GetMapping("/show/{id}")
    public String showarticle(Model model,@PathVariable int id){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        return "article/show";
    }



}
