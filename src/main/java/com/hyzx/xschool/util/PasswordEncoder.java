package com.hyzx.xschool.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PasswordEncoder {

   public static String encode(String rawPassword) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(rawPassword);
  }

  public static boolean checkPassword(String password, String encodedPassword) {
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   // return encoder.matches(password, encodedPassword);
    return password.equals(encodedPassword);
  }
}
