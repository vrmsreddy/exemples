package com.hyzx.xschool.service;

import com.hyzx.xschool.web.controller.request.TaskFormBean;
import org.springframework.data.domain.Page;

import com.hyzx.xschool.domain.Task;
import com.hyzx.xschool.web.controller.request.TaskQueryBean;

/**
 * 任务service
 *
 * @author qly
 */
public interface TaskService {

    /**
     * 分页列表
     *
     * @param q
     * @return
     */
    Page<Task> findByNameAndTopicStoreId(TaskQueryBean q);

    void save(TaskFormBean task);
}
