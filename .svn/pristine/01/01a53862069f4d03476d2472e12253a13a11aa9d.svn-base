package com.hyzx.xschool.service;

import com.hyzx.xschool.util.PasswordEncoder;
import org.testng.annotations.Test;

public class UserServiceTest {

  @Test
  public void test() {

    for (int i = 0; i < 10; i++) {

      String pass = (PasswordEncoder.encode("001001"));
      System.out.println(pass);
      System.out.println(PasswordEncoder
          .checkPassword("001001", pass));
    }

  }

}
