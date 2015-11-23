package com.muzhi.mdroid.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;

/**
 * 文本,字符串,TextView的辅助类
 * 
 * 
 * 
 */
public class TextUtils{
	
	
	/**
    * 实现文本复制功能
    * API 11之前： android.text.ClipboardManager
	* API 11之后： android.content.ClipboardManager
    * @param content
    */
    public static void copy(Context context,String msg){
	    // 得到剪贴板管理器 
	    ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
	    cmb.setText(msg.trim());
    }
	
	
    /**
    * 得到剪贴板管理器实现粘贴功能
    * add by wangqianzhou
    * @param context
    * @return
    */
    public static String paste(Context context){
	    // 得到剪贴板管理器
	    ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
	    return cmb.getText().toString().trim();
    }
    
    /**
     * 类似新浪微博内容计数器(中文算一个字符，二个英文算一个字符)
     * @param s
     * @return
     */
    public static int sinaCountWord(String s) {
        int i, n = s.length(), l = 0, a = 0, b = 0;
        char c;
        for (i = 0; i < n; i++) {
              c = s.charAt(i);
              if (Character.isWhitespace(c)) {
                    b++;
              } else if (c >= 0 && c <= 127) {
                    // } else if (!Character.isLetter(c)) {
                    a++;

              } else {
                    l++;
              }
        }
        if (a == 0 && l == 0)
              return 0;
        return l + (int) Math.ceil((float) (a + b) / 2.0);
    }
    
    
    /**
     * 转成MD5
     * @param val
     * @return
     * @throws NoSuchAlgorithmException
     */
  	public static String getMD5(String val){  
  		
  		try{
  	        MessageDigest md5 = MessageDigest.getInstance("MD5");  
  	        md5.update(val.getBytes());  
  	        byte[] m = md5.digest();//加密  
  	        
  	        StringBuffer sb = new StringBuffer();
  	        for(int i = 0; i < m.length; i ++){  
  	            sb.append(m[i]);  
  	        }
  	        
  	        return sb.toString();    
  		}
  		catch(NoSuchAlgorithmException e){
  			return "";
  		}
  	}
  	
  	/**
	 * 设置中划线并加清晰
	 * 
	 * @author 
	 * @date 
	 * @param 
	 */
	public static void setMiddleLine(TextView tv) {
		tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
	}
    
	/**
	 * 找到数字并染红
	 * 
	 * @author 
	 * @date 
	 * @param 
	 * @param 
	 * @param 
	 */
	public static void findNumberText(TextView textView, SpannableString ss, Pattern pattern) {
		Matcher matcher = pattern.matcher(ss.toString());
		while (matcher.find()) {
			String key = matcher.group();
			if (!"".equals(key)) {
				int start = ss.toString().indexOf(key);
				int end = start + key.length();
				setNumberTextRed(textView, ss, start, end);
			}
		}
	}
	private static void setNumberTextRed(TextView textView, SpannableString ss, int start, int end) {
		ss.setSpan(ss, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(ss);
	}
	
	
	/**
	 * 判断字符串是否纯数字
	 * 
	 * @author 
	 * @date 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return str.matches("[0-9]*");
	}
	
	/**
	 * 验证是否为手机号码
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str){
		String numReg = "^1\\d{10}";
		boolean result = Pattern.matches(numReg, str);
		return result;
	}
	
	/**
	 * 验证邮件格式
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	
	
	
	
}
