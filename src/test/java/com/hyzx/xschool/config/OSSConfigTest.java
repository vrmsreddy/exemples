package com.hyzx.xschool.config;

import com.aliyun.oss.OSS;
import com.hyzx.xschool.base.TestNGBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-11-19 10:34
 */
public class OSSConfigTest extends TestNGBase {


    @Autowired
    OSS uploadOSSClient;

    @Autowired
    OSSConfig ossConfig;

    @Test
    public void test(){
        assertNotNull(ossConfig);
        assertNotNull(ossConfig.getAccessKeyId());
    }

    @Test
    public void test2(){
        assertNotNull(uploadOSSClient);
    }

}
