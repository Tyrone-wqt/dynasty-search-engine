package core.spider;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import core.util.HtmlParser;

public class WebAnalyzer {

	private static final String ENDPAGE = "**************************************************";
	
	public WebAnalyzer()
	{}

	//�������һЩ�����Ĺ��ܣ���ȡdoc�е�url���ҷ��أ���Σ�����ȡ�����ݵ�doc
	//����ָ���ĸ�ʽд���ļ��н��б���
	public ArrayList<URL> doAnalyzer(BufferedWriter bfWriter, URL url, String htmlDoc) {
	
		System.out.println("in doing analyzer the size of doc is: " + htmlDoc.length());

		ArrayList<URL> urlInHtmlDoc = (new HtmlParser()).urlDetector(htmlDoc);	
		saveDoc(bfWriter, url, htmlDoc);
	
		return urlInHtmlDoc;
	}
	
	//��ʽ���գ�
	//ÿ����¼��ͷ�� + ���� + ���� + ����
	//ͷ��Ϊ�������ԣ�ÿ�������Ƿǿ��У����������� + ð�� + ����ֵ
	//��һ������Ϊ version:1.0�����һ������Ϊ���ݳ������ԣ�length��8021����data�ĳ��ȣ�����������
	//	example:
	//	version:1.0
	//	url:http//www.pku.edu.cn
	//	origin:http://www.somewhere.cn
	//	date:Tue, 15 Apr 2003 08:13:06 GMT
	//	ip:162.105.129.12
	//	length:18133
	//	
	//	XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	//	data segement
	//	XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	//	
	
	private void saveDoc(BufferedWriter bfWriter, URL url, String htmlDoc) {

		try {
			
			String versionStr = "version:1.0\n";
			String URLStr = "url:" + url.toString() + "\n";
			
			Date date = new Date();     
			String dateStr = "date:" + date.toString() + "\n";
			
			InetAddress address = InetAddress.getByName(url.getHost()); 
			String IPStr = address.toString();
			IPStr = "IP:" + IPStr.substring(IPStr.indexOf("/")+1, IPStr.length()) + "\n";
			
			String htmlLen = "length:" + htmlDoc.length() + "\n";
			
			//����ͷ����
			bfWriter.append(versionStr);
			bfWriter.append(URLStr);
			bfWriter.append(dateStr);
			bfWriter.append(IPStr);
			bfWriter.append(htmlLen);
			bfWriter.newLine();
			
			//���ݲ���
			bfWriter.append(htmlDoc);
			//bfWriter.append(ENDPAGE);
			//bfWriter.newLine();
			bfWriter.newLine();
			
			bfWriter.flush();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
}
