package com.hyzx.xschool.service.impl;

import com.hyzx.xschool.domain.Resource;
import com.hyzx.xschool.domain.repository.ResourceRepository;
import com.hyzx.xschool.service.ResourceService;
import com.hyzx.xschool.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-11-25 00:28
 */
@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ResourceRepository resourceRepository;

  @Override
  public void remove(Long id) {
    //删除资源记录
    Resource oldPicResource = resourceRepository.findOne(id);
    if (oldPicResource != null) {
      resourceRepository.delete(id);
    }

    // 删除本地服务上的旧文件
    String filePath = oldPicResource.getPath();
    if (StringUtils.startsWith(filePath, Constants.LOCAL_UPLOAD_DIR)) {
      FileSystemUtils.deleteRecursively(new File(oldPicResource.getPath()));
    } else {
      //TODO 删除OSS上的旧文件

    }
  }
}
