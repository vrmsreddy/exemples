package com.hyzx.xschool.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.hyzx.xschool.config.OSSConfig;
import com.hyzx.xschool.service.OssService;
import com.hyzx.xschool.util.FileUtil;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;



@Service("ossService")
public class OssServiceImpl implements OssService {

  private static final Logger LOG = LoggerFactory.getLogger(OssServiceImpl.class);

  @Resource
  private OSSConfig ossConfig;
  @Resource
  private OSSClient uploadOSSClient;
  @Resource
  private OSSClient downloadOSSClient;

  /**
   * <pre>
   * 上传文件，返回文件访问路径
   * storagePath示例：images/icons
   * storagePath最后不要（切记）File.separator
   * </pre>
   *
   * @param storagePath：存储的路径
   * @param file：文件
   * @return
   */
  @Override
  public String upload(String storagePath, MultipartFile file) {
    String originFileName = file.getOriginalFilename();
    // 设置文件名
    String filePathName = (generateRelativeStoragePath(storagePath) + FileUtil.getExtension(originFileName)).replaceAll("\\\\", "/");
    // 设置校验md5
    MessageDigest messageDigest = null;
    byte[] fileContent = null;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
      fileContent = file.getBytes();
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Cannot get MD5 algorithm when preparing upload file to OSS.");
      // throw new InvalidStateException("Cannot get MD5 algorithm", e);
    } catch (IOException e) {
      LOG.error("Cannot get file content from {}.", originFileName);
      // throw new InvokeException("Cannot get file content" + originFileName, e);
    }
    messageDigest.update(fileContent);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setHeader("Content-MD5", Base64Utils.encodeToString(messageDigest.digest()));

    // 存储
    try {
      uploadOSSClient.putObject(ossConfig.getBucketName(), filePathName, file.getInputStream(),
          metadata);
    } catch (OSSException | ClientException | IOException e) {
      LOG.error("OSS storage error", e);
      // throw new BusinessException(Errors.SYSTEM_CUSTOM_ERROR.code, "OSS storage exception",
      // "OSS storage exception", e);
    }

    return ossConfig.getDownloadEndpoint() +  "/"  + filePathName;
  }


  @Override
  public String upload(String storagePath, File file) {
    // 设置文件名
    String filePathName = (generateRelativeStoragePath(storagePath) + FileUtil.getExtension(file.getName())).replaceAll("\\\\", "/");
    // 设置校验md5
    MessageDigest messageDigest = null;
    byte[] fileContent = null;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
      fileContent = Files.readAllBytes(Paths.get(file.getPath()));
    } catch (NoSuchAlgorithmException e) {
      LOG.error("Cannot get MD5 algorithm when preparing upload file to OSS.");
      // throw new InvalidStateException("Cannot get MD5 algorithm", e);
    } catch (IOException e) {
      LOG.error("Cannot get file content from {}.", file.getName());
      // throw new InvokeException("Cannot get file content" + originFileName, e);
    }
    messageDigest.update(fileContent);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.length());
    metadata.setHeader("Content-MD5", Base64Utils.encodeToString(messageDigest.digest()));

    // 存储
    try {
      uploadOSSClient.putObject(ossConfig.getBucketName(), filePathName, new FileInputStream(file),
          metadata);
    } catch (OSSException | ClientException | IOException e) {
      LOG.error("OSS storage error", e);
    }

    return ossConfig.getDownloadEndpoint() +  "/" + filePathName;
  }

  /**
   * <pre>
   * 获取存储的相对路径
   * 规则path + FileUtil.getFileSeparator() + yyyyMMddHH + FileUtil.getFileSeparator() + uuid
   * </pre>
   *
   * @param storagePath
   * @return
   */
  private String generateRelativeStoragePath(String storagePath) {
    String time = DateUtils.formatDate(new Date(), "yyyyMMddHH");
    StringBuilder sb = new StringBuilder(ossConfig.getRealPath()).append(getFileSeparator());
    if (!StringUtils.isEmpty(storagePath)) {
      sb.append(storagePath).append(getFileSeparator());
    }
    sb.append(time).append(getFileSeparator()).append(UUID.randomUUID().toString());
    return sb.toString();
  }

  private String getFileSeparator() {
	  return FileUtil.getFileSeparator();
  }
}
