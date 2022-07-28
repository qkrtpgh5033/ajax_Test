package com.ll.exam.article.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleModifyDto {
    private String title;
    private String body;

}
