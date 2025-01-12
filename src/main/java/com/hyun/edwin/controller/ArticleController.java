package com.hyun.edwin.controller;

import com.hyun.edwin.dto.ArticleDto;
import com.hyun.edwin.dto.CommentDto;
import com.hyun.edwin.entity.Article;
import com.hyun.edwin.repository.ArticleRepository;
import com.hyun.edwin.service.ArticleService;
import com.hyun.edwin.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /* List All */
    @GetMapping("/articles")
    public String list(Model model){
        ArrayList<Article> articleShowList = articleService.selectListAll();

        model.addAttribute("articleShowList", articleShowList);
        model.addAttribute("titleheader", "List");

        return "articles/list";
    }

    /* List One */
    @GetMapping("/articles/{articleId}")
    public String view(@PathVariable("articleId") Long articleId, Model model){

        Article articleEntity = articleService.selectArticle(articleId);
        List<CommentDto> commentDtos = commentService.comments(articleId);

        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        model.addAttribute("titleheader", "View");

        return "articles/view";
    }

    /* Create View */
    @GetMapping("/articles/new")
    public String createView(Model model){

        model.addAttribute("titleheader", "New View");

        return "articles/newView";
    }

    /* Create Action */
    @PostMapping("/articles/new")
    public String create(@ModelAttribute ArticleDto articleDto, Model model){

        Article articleSaved = articleService.articleCreate(articleDto);

        return "redirect:/articles/"+ articleSaved.getId();
    }

    /* Update View */
    @GetMapping("/articles/{articleId}/update")
    public String updateView(@PathVariable("articleId") Long articleId, Model model){
        Article articleEntity = articleService.selectArticle(articleId);

        model.addAttribute("article", articleEntity);
        model.addAttribute("titleheader", "Update View");

        return "articles/updateView";
    }

    /* Update Action */
    @PostMapping("/articles/update")
    public String update(@ModelAttribute ArticleDto articleDto, Model model){

        Article articleEntity = articleService.articleUpdate(articleDto);

        return "redirect:/articles/" + articleEntity.getId() ;
    }


    /* Delete */
    @GetMapping("/articles/{articleId}/delete")
    public String delete(@PathVariable("articleId") Long articleId, RedirectAttributes rttr){
        Article articleEntity = articleService.selectArticle(articleId);
        if(articleEntity != null){
            articleService.deleteArticle(articleEntity);
            rttr.addFlashAttribute("msg", "삭제했습니다.");
        }

        return "redirect:/articles";
    }
}
