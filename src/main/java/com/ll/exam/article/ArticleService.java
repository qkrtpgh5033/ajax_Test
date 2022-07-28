package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.dto.ArticleModifyDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = new ArticleRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);
    }

    public List<ArticleDto> getList(){
        return articleRepository.getList();
    }

    public ArticleDto findById(long id) {
        return articleRepository.findById(id);
    }

    public void articleDelete(long id) {
        articleRepository.articleDelete(id);
    }

    public void articleModify(long id, ArticleModifyDto articleModifyDto) {
        articleRepository.articleModify(id, articleModifyDto);
    }
}