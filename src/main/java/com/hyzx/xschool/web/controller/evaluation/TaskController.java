package com.hyzx.xschool.web.controller.evaluation;

import com.beust.jcommander.internal.Lists;
import com.hyzx.xschool.domain.Resource;
import com.hyzx.xschool.domain.Task;
import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.ResourceRepository;
import com.hyzx.xschool.domain.repository.TaskRepository;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.TaskService;
import com.hyzx.xschool.util.Constants;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.TaskFormBean;
import com.hyzx.xschool.web.controller.request.TaskQueryBean;
import com.hyzx.xschool.web.controller.response.TaskInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * 测评管理->任务管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "测评管理->任务管理", produces = "application/json")
@RestController
@RequestMapping("/evaluation/task")
public class TaskController {

  private static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

  @Autowired
  TaskRepository taskRepository;
  
  @Autowired
  TaskService taskService;
  @Autowired
  TopicStoreRepository topicStoreRepository;
  @Autowired
  ResourceRepository resourceRepository;

  /**
   * 查看任务列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "任务管理：任务列表", notes = "分页获取任务数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<TaskInfo> list(@RequestBody TaskQueryBean q) {
    LOGGER.info("查看任务 -> {}", q.toString());
    Page<Task> page = taskService.findByNameAndTopicStoreId(q);
    if (!page.hasContent()) {
      return RestResult.ok(Collections.emptyList());
    }

    List<Long> topicStoreIds = page.getContent().stream().map(Task::getTopicStoreId).collect(toList());
    List<TopicStore> topicStores = topicStoreRepository.findAll(topicStoreIds);
    Map<Long, String> topicStoreMap = topicStores.stream().collect(toMap(TopicStore::getId, TopicStore::getName));

    // 构造页面展示数据
    List<TaskInfo> list = page.getContent().stream().map(t -> {
      TaskInfo info = new TaskInfo();
      info.setId(t.getId());
      info.setName(t.getName());
      info.setHardType(t.getHardType());
      info.setHasVideo(t.getVideoResourceId() != null);
      info.setTpicStoreName(topicStoreMap.get(t.getTopicStoreId()));
      info.setNoPlayTimes(t.getNoPlayTimes());
      //TODO 最后操作人、操作时间
      info.setCreateTime(new DateTime(Long.parseLong(t.getCreateTime())).toString(Constants.DATE_TIME_LONG_FORMAT));
      info.setStatus(t.getStatus());
      return info;
    }).collect(toList());
    return RestResult.okPage(list, (int) page.getTotalElements());
  }

  /**
   * 编辑任务.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "任务管理-任务列表：编辑", notes = "获取待编辑的任务信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<TaskFormBean> detail(@PathVariable("id") Long id) {
    Task t = taskRepository.findOne(id);
    if (t == null) {
      throw new BizException("任务不存在");
    }

    TaskFormBean taskToEdit = new TaskFormBean();
    taskToEdit.setId(t.getId());
    taskToEdit.setName(t.getName());
    taskToEdit.setTopicStoreId(t.getTopicStoreId());
    taskToEdit.setHardType(t.getHardType().intValue());

    // 设置视频、图片地址
    List<Resource> resources = resourceRepository
        .findAll(Lists.newArrayList(t.getVideoPicResourceId(), t.getVideoResourceId()));
    Map<Long, String> resourceMap =
        resources.stream().collect(toMap(Resource::getId, Resource::getPath));
    taskToEdit.setVideoPath(resourceMap.get(t.getVideoResourceId()));
    taskToEdit.setPicPath(resourceMap.get(t.getVideoPicResourceId()));
    taskToEdit.setPicRescoureId(t.getVideoPicResourceId());
    taskToEdit.setDetail(t.getDetail());
    return RestResult.ok(taskToEdit);
  }

  /**
   * 保存任务.
   *
   * @param task
   * @return
   */
  @ApiOperation(value = "任务管理-任务列表：编辑->保存", notes = "保存任务信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save( @RequestBody TaskFormBean task) {
    LOGGER.info("保存任务 -> {}", task.toString());

    taskService.save(task);
    return RestResult.ok();
  }

  /**
   * 更新状态：启用/禁用.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "任务管理-任务列表：开启/禁用", notes = "更新任务状态")
  @RequestMapping(value = "/{id}/update/{status}", method = RequestMethod.POST)
  public RestResult<Void> updateStatus(@PathVariable("id") Long id, @PathVariable("status")
  int status) {

    Task task = taskRepository.findOne(id);
    if (task == null) {
      throw new BizException("任务不存在");
    }

    task.setStatus(status);
    taskRepository.save(task);
    return RestResult.ok();
  }
}
