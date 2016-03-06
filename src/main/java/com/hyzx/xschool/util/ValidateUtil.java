package com.hyzx.xschool.util;
import org.apache.commons.lang.math.IntRange;


public class ValidateUtil {

	public static boolean range(int o,int min,int max){
		IntRange ir = new IntRange(min,max);
		return ir.containsInteger(o);
		 
	}
	
	  public static boolean checkNumber(String str){  
	        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
	        return str.matches(regex);  
	    } 
  
	  public static boolean checkPhone(String str){  
		  String regex="1([\\d]{10})|[\\d]{3,4}-[\\d]{6,8}|400[\\d]{5,10}"; 
		    return str.matches(regex);  
	    } 
	  
//	  public static void main(String[] args) {
//		// 或者400223534
//		  System.out.println( checkPhone("400223534"));
//	}
}