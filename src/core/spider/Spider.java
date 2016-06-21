package core.spider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Spider {

	private ArrayList<URL> urls;
	private int gatherNum = 5;
	
	public Spider(){}
	
	public Spider(ArrayList<URL> urls)
	{
		this.urls = urls;
	}
	
	/**
	 * 启动线程gather，然后开始收集网页资料
	 */
	public void start() {
		Dispatcher disp = new Dispatcher(urls);
		for(int i = 0; i < gatherNum; i++)
		{
			Thread gather = new Thread(new Gather(String.valueOf(i), disp));
			gather.start();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<URL> urls = new ArrayList<URL>();
		try {
			
			//urls.add(new URL("http://ast.nlsde.buaa.edu.cn/"));		
			//urls.add(new URL("http://www.baidu.com"));
			//urls.add(new URL("http://www.google.com"));
			//urls.add(new URL("http://www.sohu.com"));
			urls.add(new URL("http://www.163.com"));
			urls.add(new URL("http://www.sina.com"));
			urls.add(new URL("http://edu.sina.com.cn/"));			
			urls.add(new URL("http://edu.163.com/"));
			urls.add(new URL("http://ast.nlsde.buaa.edu.cn/"));
			//urls.add(new URL("http://www.chsi.com.cn/"));
			//urls.add(new URL("http://www.eol.cn/"));
			//urls.add(new URL("http://www.edutv.net.cn/"));
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		Spider spider = new Spider(urls);
		spider.start();

	}

}
