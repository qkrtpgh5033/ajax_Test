package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.dto.ArticleModifyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            tempWrite(title, body);
        });
    }

    public static long tempWrite(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body);

        datum.add(newArticleDto);

        return id;
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