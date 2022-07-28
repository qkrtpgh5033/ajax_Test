package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.dto.ArticleModifyDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;
    }

    public long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);

        datum.add(newArticleDto);

        return id;
    }

    public List<ArticleDto>  getList() {
        return this.datum;
    }

    public ArticleDto findById(long id) {
        for (ArticleDto findDto : datum) {
            if (findDto.getId() == id) {
                return findDto;
            }
        }
        return null;
    }

    public void articleDelete(long id) {
        ArticleDto findArticle = findById(id);
        if (findArticle != null) {
            datum.remove(findArticle);
        }
    }

    public void articleModify(long id, ArticleModifyDto articleModifyDto) {
        ArticleDto findArticle = findById(id);
        if (findArticle != null) {
            findArticle.setTitle(articleModifyDto.getTitle());
            findArticle.setBody(articleModifyDto.getBody());
        }
    }
}