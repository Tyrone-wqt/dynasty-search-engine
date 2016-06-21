package core.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

	public HtmlParser(){}
	
	//&quot;&nbsp;
	public String html2Text(String inputString) 
	{    	
		String htmlStr = inputString; //含html标签的字符串    
		String textStr ="";    
		Pattern p_script,p_style,p_html,p_filter;    
		Matcher m_script,m_style,m_html,m_filter;      
	          
	    try { 
	    	//定义script正则式{或<script[^>]*?>[\s\S]*?<\/script> } 
	    	String regEx_script = "<script[^>]*?>[\\s\\S]*?</script>";    
	    	//定义style正则式{或<style[^>]*?>[\s\S]*?<\/style> }    
	    	String regEx_style = "<style[^>]*?>[\\s\\S]*?</style>"; 
	    	//定义HTML标签的正则表达式 
	    	String regEx_html = "<[^>]+>";
	        String[] filter = {"&quot;", "&nbsp;"};
	    	
	        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
	        m_script = p_script.matcher(htmlStr);    
	        htmlStr = m_script.replaceAll(""); //过滤script标签    
	   
	        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
	        m_style = p_style.matcher(htmlStr);    
	        htmlStr = m_style.replaceAll(""); //过滤style标签    
	           
	        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
	        m_html = p_html.matcher(htmlStr);    
	        htmlStr = m_html.replaceAll(""); //过滤html标签    
	           
	        //过滤style标签    &quot; &nbsp;
	        for(int i = 0; i < filter.length; i++)
	        {
	        	p_filter = Pattern.compile(filter[i],Pattern.CASE_INSENSITIVE);    
	        	m_filter = p_filter.matcher(htmlStr);    
		        htmlStr = m_filter.replaceAll(""); 
	        }
	        
	        textStr = htmlStr;    
	           
	    }catch(Exception e) {    
	       System.err.println("Html2Text: " + e.getMessage());    
	    }    
	          
	    return textStr;//返回文本字符串    
	}
	
	//URL还需要做的工作，去除一些无用链接，修复一些相对路径的链接
	public ArrayList<URL> urlDetector(String htmlDoc)
	{
		final String patternString = "<[a|A]\\s+href=([^>]*\\s*>)";   		
		Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);   
		
		ArrayList<URL> allURLs = new ArrayList<URL>();

		Matcher matcher = pattern.matcher(htmlDoc);
		String tempURL;
		//初次匹配到的url是形如：<a href="http://bbs.life.sina.com.cn/" target="_blank">
		//为此，需要进行下一步的处理，把真正的url抽取出来，可以对于前两个"之间的部分进行记录得到url
		while(matcher.find())
		{
			try {
				
				tempURL = matcher.group();			
				tempURL = tempURL.substring(tempURL.indexOf("\"")+1);			
				if(!tempURL.contains("\""))
					continue;
				
				tempURL = tempURL.substring(0, tempURL.indexOf("\""));					
				//System.out.println(tempURL);
				//即使在之前的处理下，还是有可能发生意外的，比如，程序用的是相对的url
				//这样，这个字符串就不可以用于url的初始化，我们先把这部分省略不考虑
				//之后可以写一个补充host的方法将这些url补齐
				if(tempURL.startsWith("http"))
					allURLs.add(new URL(tempURL));
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return allURLs;	
	}
	
}
