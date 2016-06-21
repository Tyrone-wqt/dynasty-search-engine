package core.spider;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Dispatcher {

	private static ArrayList<URL> urls = new ArrayList<URL>();
	private static ArrayList<URL> visitedURLs = new ArrayList<URL>();
	private static ArrayList<URL> unvisitedURLs = new ArrayList<URL>();
	
	public Dispatcher(ArrayList<URL> urls) {    
		this.urls = urls; 
	}    
	
	public synchronized URL getURL()		
	{
		//��ջ�����ݣ����ܳ�ջ
		while(urls.isEmpty()){ 
			try{ 
				wait(); // �ȴ�������д������ 
			} catch (InterruptedException e) { 
				e.printStackTrace(); 
			} 
		}
		
		this.notify(); 
		URL url = urls.get(0);
		visitedURLs.add(url);
		urls.remove(url);
		
	    return url; 
	}

	public synchronized void insert(URL url)
	{
		if(!urls.contains(url) && !visitedURLs.contains(url))
			urls.add(url);
	}

	public synchronized void insert(ArrayList<URL> analyzedURL)
	{
		for(URL url : analyzedURL)
		{
			if(!urls.contains(url) && !visitedURLs.contains(url))
			urls.add(url);
		}
	}
    
}
