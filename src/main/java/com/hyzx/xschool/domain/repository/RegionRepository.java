package com.hyzx.xschool.domain.repository;

import com.hyzx.xschool.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jack on 15-11-5.
 */
public interface RegionRepository extends JpaRepository<Region, Long> {


  /**
   * 根据类型和开通标识获取地区数据
   *
   * @param type
   * @param isOpened
   * @return
   */
  List<Region> findByTypeAndIsOpened(int type, boolean isOpened);

  List<Region> findByParentId(Long parentId);
}
