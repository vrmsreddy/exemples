package com.hyzx.xschool.service;

import com.hyzx.xschool.domain.Resource;
import org.springframework.data.domain.Page;

import com.hyzx.xschool.domain.Article;
import com.hyzx.xschool.web.controller.request.ArticleQueryVo;

import java.util.List;

/**
 * 文章service
 *
 * @author qly
 */
public interface ArticleService {

  /**
   * 分页查询
   *
   * @param arc
   * @return
   */
  Page<Article> findByPage(ArticleQueryVo arc);

  void save(Article article);

  void saveArticleImagesToOSS(Article article);
}
