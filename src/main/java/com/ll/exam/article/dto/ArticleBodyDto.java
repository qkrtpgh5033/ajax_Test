package com.ll.exam.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBodyDto {
    private String title;
    private String body;

    public static ArticleBodyDto from (ArticleDto articleDto){
        return new ArticleBodyDto(articleDto.getTitle(), articleDto.getBody());
    }
}
