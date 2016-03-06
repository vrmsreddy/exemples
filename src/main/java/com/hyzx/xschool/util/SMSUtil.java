package com.hyzx.xschool.util;

import com.alibaba.fastjson.JSONObject;
import com.hyzx.xschool.exception.BizException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSUtil {

  private static final Logger log = LoggerFactory.getLogger(SMSUtil.class);

  private static String uid = "1068";
  private static String sign = "百家学";
  private static String token = "7db63c5f0d83c8b539bb7cfe96b79836293809dd0a76c153d2b6f52faaeb881c";
  private static String url = "http://sms.reactor.uworks.cc/sms/msg/send";

  public static void sendSms(String mobile, String content) {
    try {
      JSONObject data = new JSONObject();
      data.put("content", content);
      data.put("mobile", mobile);
      data.put("uid", uid);
      data.put("sign", sign);
      data.put("token", token);
      HttpClient client = new HttpClient();
      PostMethod method = new PostMethod(url);
      RequestEntity requestEntity =
          new StringRequestEntity(data.toString(), "application/json", "UTF-8");
      method.setRequestEntity(requestEntity);
      int code = client.executeMethod(method);
      if (code != 200) {
        log.error("Send sms response code={}, response content={}, send content={}", code, method.getResponseBodyAsString(), 
        		data.toJSONString());
        throw new Exception();
      }
    } catch (Exception e) {
      throw new BizException("短信发送失败！");
    }
  }
}




