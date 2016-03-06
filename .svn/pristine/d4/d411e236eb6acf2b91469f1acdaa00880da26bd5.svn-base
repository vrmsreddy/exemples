package com.hyzx.xschool.service.impl;

import com.google.common.base.Strings;
import com.hyzx.xschool.domain.Evaluation;
import com.hyzx.xschool.domain.EvaluationTopicStore;
import com.hyzx.xschool.domain.UserTrainPlanRecord;
import com.hyzx.xschool.domain.enums.OpenStatus;
import com.hyzx.xschool.domain.repository.EvaluationRepository;
import com.hyzx.xschool.domain.repository.EvaluationTopicStoreRepository;
import com.hyzx.xschool.domain.repository.UserTrainPlanRecordRepository;
import com.hyzx.xschool.exception.BizException;
import com.hyzx.xschool.service.EvaluationService;
import com.hyzx.xschool.web.controller.request.EvaluationFormBean;
import com.hyzx.xschool.web.controller.request.EvaluationQueryFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by jack on 15-11-8.
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	EvaluationRepository evaluationRepository;
	@Autowired
	EvaluationTopicStoreRepository evaluationTopicStoreRepository;
  @Autowired
  private UserTrainPlanRecordRepository userTrainPlanRecordRepository;

  @Override
	@Transactional
	public void save(EvaluationFormBean vo) {
	Evaluation dbVo =  evaluationRepository.findByName(vo.getName());
	if(dbVo!=null && vo.getId()==null){
		throw new BizException("测评名称已存在");
		
	}
	if(dbVo!=null && !vo.getId().equals(dbVo.getId())){
		throw new BizException("测评名称已存在");
		
	}
    Evaluation evaluationToSave = null;

    // 如果是新增评测记录，则初始化点击量
    boolean isModifyOld = false;
    if (vo.getId() == null) {
      evaluationToSave = new Evaluation();
      evaluationToSave.setClickNum(0);
      evaluationToSave.setStatus(OpenStatus.INVALID.getCode());
    } else {
      // 检查是否可修改
      checkBeforModify(vo.getId());

      isModifyOld = true;
      evaluationToSave = evaluationRepository.findOne(vo.getId());
    }

    // 仅设置添加、更新的字段
    evaluationToSave.setName(vo.getName());
    evaluationToSave.setDetail(vo.getDetail());

    // 过滤出需要保存的题库
    List<EvaluationTopicStore> evaluationTopicStoresToSave =
      vo.getTopicStoreList().stream().filter(ets -> ets.getTopicStoreId() != null)
        .collect(toList());
    int totalTopicNum =
      evaluationTopicStoresToSave.stream().mapToInt(EvaluationTopicStore::getTopicNum).sum();
    evaluationToSave.setTopicNum(totalTopicNum);
    evaluationRepository.save(evaluationToSave);

    /**
     * 保存题库列表
     */
    // 如果是修改旧的评测，则先移除该评测下的旧题库
    final Long evaluationId = evaluationToSave.getId();
    if (isModifyOld) {
      evaluationTopicStoreRepository.deleteByEvaluationId(evaluationId);
    }

    evaluationTopicStoresToSave.forEach(ets->{
      ets.setEvaluationId(evaluationId);
      // 设置默认的优先级
      ets.setPriority(0L);
    });
    evaluationTopicStoreRepository.save(evaluationTopicStoresToSave);
  }

	/**
	 * 分页查询
	 */
	@Override
	public Page<Evaluation> findByPage(EvaluationQueryFormBean eq) {
		Pageable pageable = new PageRequest(eq.getPageNo() - 1, eq.getPageSize(), new Sort(Sort.Direction.DESC, "id"));
		Page<Evaluation> page = null;
		if (Strings.isNullOrEmpty(eq.getName())) {
			page = evaluationRepository.findAll(pageable);
		} else {
			page = evaluationRepository.findByNameContaining(eq.getName(), pageable);
		}

		return page;
	}

  @Override
  public void remove(Long id) {
    checkBeforModify(id);
    evaluationTopicStoreRepository.deleteByEvaluationId(id);
    evaluationRepository.delete(id);
  }

  @Override
  public void modifyStatus(Long id, int status) {
    checkBeforModify(id);
    Evaluation evaluation = evaluationRepository.findOne(id);
    if (evaluation == null) {
      throw new BizException("测评不存在");
    }

    evaluation.setStatus(status);
    evaluationRepository.save(evaluation);
  }

  public void checkBeforModify(Long id) {
    UserTrainPlanRecord record = userTrainPlanRecordRepository.findFirstByEvaluationId(id);
    if (record != null) {
      throw new BizException("该评测已经开始，不允许做任何修改");
    }
  }
}
