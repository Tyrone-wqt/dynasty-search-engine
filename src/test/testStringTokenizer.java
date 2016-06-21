package test;

import java.util.*;

public class testStringTokenizer {

	public static void main(String[] args)
	{
		String str2 = "你好，董宇是个傻瓜。呵呵，真的很开心，能够一起玩！《年青文摘》、《报刊文摘》";
	    //创建StringTokenizer类的对象strT1,并构造字符串str1的分析器
        //以空格符、","、"."及"!"作为定界符
		String token = "。，、；：？！“”‘’《》（）-";
	    StringTokenizer strT1 = new StringTokenizer(str2,token);

	    //获取字符串str1中语言符号的个数
	    int num1 = strT1.countTokens();

	    System.out.println("str1 has "+num1+" words.They are:");
	    
	    //利用循环来获取字符串str1中下一个语言符号,并输出
	    while(strT1.hasMoreTokens())
	    {   
	    	String str = strT1.nextToken();
	    	System.out.print("\""+str+"\" ");
	    }

	            
	}

}
