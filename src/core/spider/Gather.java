package core.spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gather implements Runnable {

	private Dispatcher disp;
	private String ID;
	private URLClient client = new URLClient();
	private WebAnalyzer analyzer = new WebAnalyzer();
	private File file;
	private BufferedWriter bfWriter;
	
	public Gather(String ID, Dispatcher disp)
	{
		this.ID = ID;
		this.disp = disp;
		
		file = new File("Raws\\RAW__" + ID + ".txt");           //设定输出的文件名

		try {
			file.createNewFile();
			bfWriter = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//注意，最好还能够探测链接建立的时间，从而把持续时间太长的链接直接kill掉
	//防止在一个url上停留过久的问题
	public void run() {
		
		int counter = 0;
		while(counter++ <= 2)		//每个线程提取100个网页
		{
			URL url = disp.getURL();
			System.out.println("in running: " + ID + " get url: " + url.toString());
			String htmlDoc = client.getDocumentAt(url);
			
			//doanalyzer完成url解析并且返回，保存指定格式的doc
			//htmlDoc可能为空，即没有获得页面的代码信息，这样就需要删除
			if(htmlDoc.length() != 0)
			{
				ArrayList<URL> newURL = analyzer.doAnalyzer(bfWriter, url, htmlDoc);	
				if(newURL.size() != 0)
					disp.insert(newURL);
			}
			
		}
		
	}

}
