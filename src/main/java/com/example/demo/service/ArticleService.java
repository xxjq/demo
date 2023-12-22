package com.example.demo.service;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void delete(Integer id);

    void update(Article article);
}
