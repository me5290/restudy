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

    // 1. 글쓰기 페이지로 이동
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "new";
    }

    // 2. 글쓴 값 저장 후 개별글 출력으로 리다이렉트
    @PostMapping("/articles/create")
    public String createArticle(ArticleDto form){
        System.out.println(form.toString());
        ArticleDto result = articleDao.createArticle(form);
        return "redirect:/articles/"+result.getId();
    }

    // 3. 개별글 보기 페이지 출력
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

    // 4. 전체글 보기 페이지 출력
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        List<ArticleDto> articleDtoList = articleDao.index();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleDtoList);
        // 3. 뷰 페이지 설정하기
        return "index";
    }

    // 5. 수정 페이지 출력
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        ArticleDto articleDto = articleDao.edit(id);
        model.addAttribute("article",articleDto);
        return "edit";
    }

    // 6. 수정된 값 저장 후 개별글 출력으로 리다이렉트
    @PostMapping("/articles/update")
    public String update(ArticleDto articleDto){
        ArticleDto updated = articleDao.update(articleDto);
        return "redirect:/articles/"+updated.getId();
    }

    // 7. 삭제 후 전체글 출력으로 리다이렉트
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id){
        System.out.println("삭제 요청이 들어왔습니다.");
        boolean result = articleDao.delete(id);
        return "redirect:/articles";
    }
}
