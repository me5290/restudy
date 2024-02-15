package com.restudy.controller;

import com.restudy.model.ArticleDao;
import com.restudy.model.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleDao articleDao;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleDto form){
        System.out.println(form.toString());
        ArticleDto result = articleDao.createArticle(form);
        return "redirect:/articles/"+result.getId();
    }
    
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id , Model model){
        System.out.println("id = " + id);
        // 1. id를 조회해서 데이터 가져오기
        ArticleDto articleDto = articleDao.show(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article",articleDto);
        // 3. 뷰 페이지 반환하기
        return "show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<ArticleDto> articleDtoList = articleDao.index();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleDtoList);
        // 3. 뷰 페이지 설정하기
        return "index";
    }
}
