package com.hyzx.xschool.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 测试{@link Strings}类
 *
 * @author caoxudong
 * @since 0.1.0
 */
public class StringsTest {

  /**
   * 生成测试数据
   * @return
   */
  @DataProvider(name = "joinData")
  public Object[][] joinData() {
    List<String> strs = new LinkedList<String>();
    strs.add("1");
    strs.add(null);
    strs.add("2");
    strs.add(null);
    strs.add("3");
    List<String> emptyList = new LinkedList<String>();
    return new Object[][] {
        new Object[] {
            strs.iterator(), ",", "1,null,2,null,3"
        },
        new Object[] {
            emptyList.iterator(), ",", ""
        },
        new Object[] {
            null, ",", null
        }
    };
  }
  
  /**
   * 测试{@link Strings#joinIncludeNull(Iterator, String)}方法
   * @param iterator
   * @param separator
   * @param expected
   */
  @Test(dataProvider = "joinData")
  public void join(Iterator<String> iterator, String separator, String expected) {
    String joinedString = Strings.joinIncludeNull(iterator, separator);
    Assert.assertEquals(joinedString, expected);
    
  }
}
