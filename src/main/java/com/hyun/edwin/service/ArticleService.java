package com.hyun.edwin.service;

import com.hyun.edwin.dto.ArticleDto;
import com.hyun.edwin.entity.Article;
import com.hyun.edwin.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArrayList<Article> selectListAll(){
        ArrayList<Article> articleShowList = articleRepository.findAll();
        return articleShowList;
    }

    public Article selectArticle(Long articleId) {
        Article articleEntity = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("조회 실패!"));
        return articleEntity;
    }

    public Article articleCreate(ArticleDto articleDto){
        Article articleEntity = articleDto.toEntity();
        Article articleSaved = articleRepository.save(articleEntity);
        return articleSaved;
    }

    public Article articleUpdate(ArticleDto articleDto){
        Article articleEntity = articleDto.toEntity();
        Article articleSaved = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(articleSaved != null){
            articleRepository.save(articleEntity);
        }
        return articleEntity;
    }

    public void deleteArticle(Article articleEntity){
        articleRepository.delete(articleEntity);
    }


}
