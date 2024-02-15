package com.restudy.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
}
