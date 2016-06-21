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
		
		file = new File("Raws\\RAW__" + ID + ".txt");           //�趨������ļ���

		try {
			file.createNewFile();
			bfWriter = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ע�⣬��û��ܹ�̽�����ӽ�����ʱ�䣬�Ӷ��ѳ���ʱ��̫��������ֱ��kill��
	//��ֹ��һ��url��ͣ�����õ�����
	public void run() {
		
		int counter = 0;
		while(counter++ <= 2)		//ÿ���߳���ȡ100����ҳ
		{
			URL url = disp.getURL();
			System.out.println("in running: " + ID + " get url: " + url.toString());
			String htmlDoc = client.getDocumentAt(url);
			
			//doanalyzer���url�������ҷ��أ�����ָ����ʽ��doc
			//htmlDoc����Ϊ�գ���û�л��ҳ��Ĵ�����Ϣ����������Ҫɾ��
			if(htmlDoc.length() != 0)
			{
				ArrayList<URL> newURL = analyzer.doAnalyzer(bfWriter, url, htmlDoc);	
				if(newURL.size() != 0)
					disp.insert(newURL);
			}
			
		}
		
	}

}
