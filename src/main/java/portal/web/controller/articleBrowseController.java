package portal.web.controller;

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
public class articleBrowseController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/list/{columnid}")
    public String getList(Model model,@PathVariable int columnid,@PageableDefault(page=1) Pageable paging){
        Page<Article> articles=articleRepository.findByColumnId(columnid,paging);
        model.addAttribute("articles",articles);
        return "article/columnlist";
    }

    @GetMapping("/show/{id}")
    public String showarticle(Model model,@PathVariable int id){
        Article article=articleRepository.loadById(id);
        model.addAttribute("article",article);
        return "article/show";
    }



}
