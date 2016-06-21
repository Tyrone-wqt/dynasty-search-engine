package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import core.util.*;

public class testParseHtml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String dictFile = "test\\testSpace.txt";
		try {
			FileReader fileReader = new FileReader(dictFile);
			BufferedReader bfReader = new BufferedReader(fileReader);
			StringBuffer result = new StringBuffer();
			DBConnection dbc = new DBConnection();
			MD5 md5 = new MD5();
			
			
			
			String word;
			//skip注意，有一些不可显示的，比如换行符算2个字符的，如：
			//version:1.0
			//url:asdf
			//skip(13)之后就剩下url:asdf
			StringBuffer sb = new StringBuffer();
			word = bfReader.readLine();
			sb.append(word + "\n");
			//int i = bfReader.
			System.out.println("sb is: " + sb.toString() + "the length is: " + sb.length());
			//bfReader.skip(13);
//			while((word = bfReader.readLine()) != null)
//			{
//				System.out.println(word);
//			}
			
			//bfReader已经把version:1.0读入了
//			while((word = bfReader.readLine()) != null)
//			{
//				String url = readRawHead(bfReader);
//				String content = readRawContent(bfReader);
//				int offset = computeOffset(bfReader);
//				System.out.println(url);
//				System.out.println(content);
//			}	
			
			bfReader.close();
			dbc.close();
			System.out.println("finish all the program");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readRawHead(BufferedReader bfReader)
	{		
		String urlLine = null;
		try {
			
			urlLine = bfReader.readLine();			
			if(urlLine != null)
				urlLine = urlLine.substring(urlLine.indexOf(":")+1, urlLine.length());
			
			String temp;
			while(!(temp = bfReader.readLine()).trim().isEmpty())
			{		}
			
			//System.out.println("head finished! get url: " + urlLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return urlLine;
	}
	
	public static String readRawContent(BufferedReader bfReader)
	{
		StringBuffer strBuffer = new StringBuffer();
		
		try {		
			String word;
			while((word = bfReader.readLine()) != null)
			{
				if(word.trim().isEmpty())
					break;
				else
					strBuffer.append(word + "\n");
			}
			
			//System.out.println("readRawContent finished! get content: ");
			//System.out.println(strBuffer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strBuffer.toString();
	}
	
	public static int computeOffset(BufferedReader bfReader)
	{
		//int offset = bfReader.
		return 0;
	}
	
	public static String html2Text(String inputString) {    
		
		String htmlStr = inputString; //含html标签的字符串    
		String textStr ="";    
		Pattern p_script,p_style,p_html,p_space;    
		Matcher m_script,m_style,m_html,m_space;      
	          
	    try { 
	    	//定义script正则式{或<script[^>]*?>[\s\S]*?<\/script> } 
	    	String regEx_script = "<script[^>]*?>[\\s\\S]*?</script>";    
	    	//定义style正则式{或<style[^>]*?>[\s\S]*?<\/style> }    
	    	String regEx_style = "<style[^>]*?>[\\s\\S]*?</style>"; 
	    	//定义HTML标签的正则表达式 
	    	String regEx_html = "<[^>]+>";
	        String regEx_space = "[\\s]*?";
	    	
	        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
	        m_script = p_script.matcher(htmlStr);    
	        htmlStr = m_script.replaceAll(""); //过滤script标签    
	   
	        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
	        m_style = p_style.matcher(htmlStr);    
	        htmlStr = m_style.replaceAll(""); //过滤style标签    
	           
	        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
	        m_html = p_html.matcher(htmlStr);    
	        htmlStr = m_html.replaceAll(""); //过滤html标签    
	        
	        p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
	        m_space = p_space.matcher(htmlStr);
	        htmlStr = m_space.replaceAll("");
	        
	        textStr = htmlStr;    
	           
	    }catch(Exception e) {    
	       System.err.println("Html2Text: " + e.getMessage());    
	    }    
	          
	    return textStr;//返回文本字符串    
	}      

}
