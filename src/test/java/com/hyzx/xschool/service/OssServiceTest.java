package com.hyzx.xschool.service;

import com.hyzx.xschool.base.TestNGBase;
import com.hyzx.xschool.config.OSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertNotNull;

/**
 * TODO
 *
 * @author youblade
 * @created 2015-11-24 22:52
 */
public class OssServiceTest extends TestNGBase {

    @Autowired OssService ossService;
    @Autowired OSSConfig ossConfig;

//    @Test
    public void test_UploadWithWeb() throws Exception {

        File file = new File("README.MD");
        assertNotNull(file);
        System.out.println("file path:" + file.getAbsolutePath());
        System.out.println("file name:" + file.getName());

        MockMultipartFile mockMultipartFile =
            new MockMultipartFile(file.getName(), file.getName(), null,
                Files.readAllBytes(Paths.get(file.getName())));
        String upload = ossService.upload("test", mockMultipartFile);
        System.out.println("oss url:" + upload);
    }

//    @Test
    public void test_Upload() throws Exception {

        File file = new File("README.MD");
        assertNotNull(file);
        System.out.println("file path:" + file.getAbsolutePath());
        System.out.println("file name:" + file.getName());
        String upload = ossService.upload("test", file);
        System.out.println("oss url:" + upload);
    }
}
