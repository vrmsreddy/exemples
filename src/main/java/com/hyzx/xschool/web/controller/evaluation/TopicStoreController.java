package com.hyzx.xschool.web.controller.evaluation;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyzx.xschool.domain.TopicStore;
import com.hyzx.xschool.domain.repository.TopicStoreRepository;
import com.hyzx.xschool.service.TopicStoreService;
import com.hyzx.xschool.web.RestResult;
import com.hyzx.xschool.web.controller.request.TopicStoreFormBean;
import com.hyzx.xschool.web.controller.request.TopicStoreQueryBean;
import com.hyzx.xschool.web.controller.response.SelectedTopicStore;
import com.hyzx.xschool.web.controller.response.TopicStoreInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 测评管理->题库管理
 *
 * @author iceman
 * @since 0.1.0
 */
@Api(value = "测评管理->题库管理", produces = "application/json")
@RestController
@RequestMapping("/evaluation/topicStore")
public class TopicStoreController {

  private static Logger LOGGER = LoggerFactory.getLogger(TopicStoreController.class);

  @Autowired
  TopicStoreRepository topicStoreRepository;
  
  @Autowired
  TopicStoreService topicStoreService;

  /**
   * 查看题库列表.
   *
   * @param q
   * @return
   */
  @ApiOperation(value = "题库管理：题库列表", notes = "分页获取题库数据，带搜索条件")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public RestResult<TopicStore> list(@RequestBody TopicStoreQueryBean q) {
    LOGGER.info("查看题库 -> {}", q.toString());
//
//    Pageable pageable =
//        new PageRequest(q.getPageNo() - 1 , q.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
//    Page<TopicStore> page = topicStoreRepository.findByName(q.getName(), pageable);
    Page<TopicStore>page=topicStoreService.findByPage(q);

    // 构造页面展示数据
    List<TopicStoreInfo> list = page.getContent().stream().map(c -> {
      TopicStoreInfo info = new TopicStoreInfo();
      info.setId(c.getId());
      info.setName(c.getName());
      //      info.setTopicNum(topicRepository.countByTopicStoreId(c.getId()));
      info.setTopicNum(c.getTopicNum());
      info.setTaskNum(c.getTaskNum());
      info.setBadDescription(c.getBadDescription());
      info.setGoodDescription(c.getGoodDescription());
      info.setTrainPlanName(c.getTrainPlanName());
      return info;
    }).collect(toList());
    return RestResult.okPage(list, (int) page.getTotalElements());
  }

  /**
   * 编辑题库.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "题库管理-题库列表：编辑", notes = "获取待编辑的题库信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public RestResult<TopicStore> detail(@PathVariable("id") Long id) {
    TopicStore location = topicStoreRepository.findOne(id);
    return RestResult.ok(location);
  }

  /**
   * 验证题库是否重名.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "题库管理-题库列表：验证", notes = "验证题库是否重名")
  @ResponseBody
  @RequestMapping(value = "/checkName", method = RequestMethod.GET)
  public String checkName(String oldName,String name) {
	  if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && topicStoreRepository.findByName(name) == null) {
			return "true";
		}
		return "false";
   
  }
  

 
  /**
   * 保存题库.
   *
   * @param topicStore
   * @return
   */
  @ApiOperation(value = "题库管理-题库列表：编辑->保存", notes = "保存题库信息")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public RestResult<Void> save(@Valid @RequestBody TopicStoreFormBean topicStore) {
    LOGGER.info("保存题库 -> {}", topicStore.getBadDesc());
   
    TopicStore topicStoreToSave =null;
    if (topicStore.getId() != null) {
      topicStoreToSave = topicStoreRepository.findOne(topicStore.getId());
      
    }
    else{
    	topicStoreToSave=new TopicStore();
    	topicStoreToSave.setTaskNum(0);
    	topicStoreToSave.setTopicNum(0);
    }


    topicStoreToSave.setName(topicStore.getName());
 
    topicStoreToSave.setGoodDescription(topicStore.getGoodDesc());
    topicStoreToSave.setBadDescription(topicStore.getBadDesc());
    topicStoreToSave.setTrainPlanName(topicStore.getTrainPlanName());
    topicStoreRepository.save(topicStoreToSave);

    return RestResult.ok();
  }

  /**
   * 删除题库.
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "题库管理-题库列表：删除", notes = "删除题库记录")
  @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
  public RestResult<Void> remove(@PathVariable("id") Long id) {
	  topicStoreService.deleteTopicStoreById(id);
    return RestResult.ok();
  }
  
  /**
   * 题库下拉列表
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "题库管理-题库列表：题库下拉列表", notes = "题库下拉列表")
  @RequestMapping(value = "/select/list", method = RequestMethod.POST)
  public RestResult<List<SelectedTopicStore>> selectList(HttpServletRequest request) {
	 List<SelectedTopicStore>list=topicStoreService.selectTopicStoreList();
    return RestResult.ok(list);
  }

}
