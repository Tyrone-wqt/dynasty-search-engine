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

	//函数完成一些分析的功能，抽取doc中的url并且返回，其次，将抽取了内容的doc
	//按照指定的格式写入文件中进行保存
	public ArrayList<URL> doAnalyzer(BufferedWriter bfWriter, URL url, String htmlDoc) {
	
		System.out.println("in doing analyzer the size of doc is: " + htmlDoc.length());

		ArrayList<URL> urlInHtmlDoc = (new HtmlParser()).urlDetector(htmlDoc);	
		saveDoc(bfWriter, url, htmlDoc);
	
		return urlInHtmlDoc;
	}
	
	//格式参照：
	//每条记录：头部 + 空行 + 数据 + 空行
	//头部为若干属性，每个属性是非空行，包含属性名 + 冒号 + 属性值
	//第一个属性为 version:1.0，最后一个属性为数据长度属性，length：8021，是data的长度，不包含空行
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
			
			//数据头部分
			bfWriter.append(versionStr);
			bfWriter.append(URLStr);
			bfWriter.append(dateStr);
			bfWriter.append(IPStr);
			bfWriter.append(htmlLen);
			bfWriter.newLine();
			
			//数据部分
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
