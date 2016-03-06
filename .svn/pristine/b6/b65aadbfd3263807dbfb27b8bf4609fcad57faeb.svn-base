package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-1.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

  Page<Article> findByTitleLike(String title, Pageable pageable);
  
  Page<Article>findByTitleContaining(String title,Pageable pageable);

  List<Article> findByOrganizationId(Long organId);
}
