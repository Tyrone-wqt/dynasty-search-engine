package test;

import java.util.*;

public class testStringTokenizer {

	public static void main(String[] args)
	{
		String str2 = "��ã������Ǹ�ɵ�ϡ��Ǻǣ���ĺܿ��ģ��ܹ�һ���棡��������ժ������������ժ��";
	    //����StringTokenizer��Ķ���strT1,�������ַ���str1�ķ�����
        //�Կո����","��"."��"!"��Ϊ�����
		String token = "������������������������������-";
	    StringTokenizer strT1 = new StringTokenizer(str2,token);

	    //��ȡ�ַ���str1�����Է��ŵĸ���
	    int num1 = strT1.countTokens();

	    System.out.println("str1 has "+num1+" words.They are:");
	    
	    //����ѭ������ȡ�ַ���str1����һ�����Է���,�����
	    while(strT1.hasMoreTokens())
	    {   
	    	String str = strT1.nextToken();
	    	System.out.print("\""+str+"\" ");
	    }

	            
	}

}
