package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-7.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Page<Category> findByParentCategoryId(Long parentCateId, Pageable pageable);

  List<Category> findByParentCategoryIdIsNull();

  Category findFirstByParentCategoryId(Long id);
}
