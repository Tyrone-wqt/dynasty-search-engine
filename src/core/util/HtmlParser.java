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
		String htmlStr = inputString; //��html��ǩ���ַ���    
		String textStr ="";    
		Pattern p_script,p_style,p_html,p_filter;    
		Matcher m_script,m_style,m_html,m_filter;      
	          
	    try { 
	    	//����script����ʽ{��<script[^>]*?>[\s\S]*?<\/script> } 
	    	String regEx_script = "<script[^>]*?>[\\s\\S]*?</script>";    
	    	//����style����ʽ{��<style[^>]*?>[\s\S]*?<\/style> }    
	    	String regEx_style = "<style[^>]*?>[\\s\\S]*?</style>"; 
	    	//����HTML��ǩ��������ʽ 
	    	String regEx_html = "<[^>]+>";
	        String[] filter = {"&quot;", "&nbsp;"};
	    	
	        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
	        m_script = p_script.matcher(htmlStr);    
	        htmlStr = m_script.replaceAll(""); //����script��ǩ    
	   
	        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
	        m_style = p_style.matcher(htmlStr);    
	        htmlStr = m_style.replaceAll(""); //����style��ǩ    
	           
	        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
	        m_html = p_html.matcher(htmlStr);    
	        htmlStr = m_html.replaceAll(""); //����html��ǩ    
	           
	        //����style��ǩ    &quot; &nbsp;
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
	          
	    return textStr;//�����ı��ַ���    
	}
	
	//URL����Ҫ���Ĺ�����ȥ��һЩ�������ӣ��޸�һЩ���·��������
	public ArrayList<URL> urlDetector(String htmlDoc)
	{
		final String patternString = "<[a|A]\\s+href=([^>]*\\s*>)";   		
		Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);   
		
		ArrayList<URL> allURLs = new ArrayList<URL>();

		Matcher matcher = pattern.matcher(htmlDoc);
		String tempURL;
		//����ƥ�䵽��url�����磺<a href="http://bbs.life.sina.com.cn/" target="_blank">
		//Ϊ�ˣ���Ҫ������һ���Ĵ�����������url��ȡ���������Զ���ǰ����"֮��Ĳ��ֽ��м�¼�õ�url
		while(matcher.find())
		{
			try {
				
				tempURL = matcher.group();			
				tempURL = tempURL.substring(tempURL.indexOf("\"")+1);			
				if(!tempURL.contains("\""))
					continue;
				
				tempURL = tempURL.substring(0, tempURL.indexOf("\""));					
				//System.out.println(tempURL);
				//��ʹ��֮ǰ�Ĵ����£������п��ܷ�������ģ����磬�����õ�����Ե�url
				//����������ַ����Ͳ���������url�ĳ�ʼ���������Ȱ��ⲿ��ʡ�Բ�����
				//֮�����дһ������host�ķ�������Щurl����
				if(tempURL.startsWith("http"))
					allURLs.add(new URL(tempURL));
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return allURLs;	
	}
	
}
