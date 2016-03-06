package com.hyzx.xschool.util;

import java.util.Iterator;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串工具类
 *
 * @author caoxudong
 * @since 0.1.0
 */
public final class Strings {

  private Strings() { }

  /**
   * 空字符串
   */
  public static final String STRING_EMPTY = "";
  
  /**
   * null的字符串表示形式
   */
  public static final String STRING_NULL = "null";

  /**
   * <p>
   * 连接字符串，使用指定的分隔符分开。
   * 
   * <p>
   * 注意，这里不会抛弃掉null值，而是将之转化为字符串拼接起来。
   * 
   * @param objs
   * @param separator
   * @return
   */
  public static String joinIncludeNull(Iterator<String> iterator, String separator) {
    if (null == iterator) {
      return null;
    }

    if (!iterator.hasNext()) {
      return STRING_EMPTY;
    }

    String first = iterator.next();
    if (!iterator.hasNext()) {
      return first == null ? STRING_NULL : first;
    }

    StringBuilder sb = new StringBuilder(256);
    if (first != null) {
      sb.append(first);
    }

    if (null == separator) {
      separator = STRING_EMPTY;
    }

    while (iterator.hasNext()) {
      sb.append(separator).append(iterator.next());
    }

    return sb.toString();
  }

  /**
   * 判断字符串是否是中文
   * 
   * @param strName
   * @return
   */
  public static boolean isChinese(String strName) {
    char[] ch = strName.toCharArray();
    for (int i = 0; i < ch.length; i++) {
      char c = ch[i];
      if (isChinese(c)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断字符是否是中文中的Unicode字符.
   * 
   * @param c
   * @return
   */
  private static boolean isChinese(char c) {
    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
      return true;
    }
    return false;
  }

  /**
   * 汉字转拼音
   * 
   * @param chines
   * @return
   */
  public static String converterToFirstSpell(String chines) {
    String pinyinName = "";
    char[] nameChar = chines.toCharArray();
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

    try {
      pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[0], defaultFormat)[0].charAt(0);
    } catch (BadHanyuPinyinOutputFormatCombination e) {
      e.printStackTrace();
    }
    return pinyinName;
  }

}
