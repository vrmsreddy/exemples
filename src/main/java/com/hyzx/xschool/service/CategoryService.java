package com.hyzx.xschool.service;

import com.hyzx.xschool.domain.Category;
import com.hyzx.xschool.web.controller.request.CategoryFormBean;
import com.hyzx.xschool.web.controller.request.CategoryQueryFormBean;
import org.springframework.data.domain.Page;

/**
 * 课程分类service
 * @author qly
 *
 */
public interface CategoryService {

    void save(CategoryFormBean category);

    void remove(Long id);

    Page<Category> finzdByPage(CategoryQueryFormBean q);
}
