package core.preprocess.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import configure.Configuration;

import core.util.DBConnection;
import core.util.MD5;
import core.util.Page;

/************************************************* 
originalPageGetter��ʵ�ָ������������ԭʼraws�ļ��ж�ȡ��ҳ�Ĺ���
����Ĳ����ǣ�rawsname�� offset����֤��url��Ϣ����֤��connentժҪ��Ϣ
*************************************************/  

/**
* <p>Title: originalPageGetter</p> 
* <p>Description: ��ԭʼraws�л�ȡ��ҳ</p> 
* <p>Copyright: Copyright (c) 2003</p> 
* <p>Company: </p> 
*  @author  <a href="dreamhunter.dy@gmail.com">dongyu</a> 
*  @version  1.0 
*  @created 2010-03-17
*/  

public class originalPageGetter {

	private String url="";
	private DBConnection dbc = new DBConnection();
	private MD5 md5 = new MD5();
	private String date="";
	private String urlFromHead="";
	private Configuration conf = new Configuration();
	
	public originalPageGetter()
	{	}
	
	public originalPageGetter(String url)
	{
		this.url = url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getPage()
	{
		Page page = getRawsInfo(url);
		String content = "";
		try {
			//�����ݿ��ж������ļ����в����зָ�������ҪԤ����һ��
			StringBuffer tfileName = new StringBuffer();
			tfileName.append(page.getRawName());
			tfileName.insert(4, "\\");
			String fileName = conf.getValue
				("RAWSPATH")+"\\"+tfileName.toString();
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bfReader = new BufferedReader(fileReader);

			String word;
			bfReader.skip(page.getOffset());
			
			readRawHead(bfReader);
			content = readRawContent(bfReader);
			String contentMD5 = md5.getMD5ofStr(content);
			
			if(contentMD5.equals(page.getConnent()))
				System.out.println("һ����Ŷ");
			else
				System.out.println("��һ����Ŷ");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;	
	}
	
	//���ص�getPage�������Ľ�getContent�ˣ�ͨ��������ļ�����ƫ�Ƶõ���ҳ����
	public String getContent(String file, int offset)
	{
		
		String content = "";
		BufferedReader bfReader = null;
		try {			
			//�����ݿ��ж������ļ����в����зָ�������ҪԤ����һ��
			StringBuffer tfileName = new StringBuffer();
			tfileName.append(file);
			tfileName.insert(4, "\\");
			String fileName = conf.getValue
			("RAWSPATH")+"\\"+tfileName.toString();
			
			FileReader fileReader = new FileReader(fileName.toString());
			bfReader = new BufferedReader(fileReader);

			String word;
			bfReader.skip(offset);
			readRawHead(bfReader);
			content = readRawContent(bfReader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bfReader!=null)
				try {
					bfReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return content;	
	}
	
	public Page getRawsInfo(String url)
	{
		String sql = " select * from pageindex where url='"+url+"' ";
		ResultSet rs = dbc.executeQuery(sql);
		
		String connent = "";
		String raws = "";
		int offset = 0;
		
		try {
			while(rs.next())
			{
				connent = rs.getString("connent"); // ѡ��connent��������
				offset = Integer.parseInt(rs.getString("offset")); // ѡ��offset��������
				raws = rs.getString("raws"); // ѡ��connent��������
				
				System.out.println(url + "\t" + connent + "\t" + offset + "\t" 
						+ raws ); // ������
			}
			
			return new Page(url, offset, connent, raws);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String readRawHead(BufferedReader bfReader)
	{		
		//String urlLine = null;
		String headStr = "";
		try {	
			bfReader.readLine();    //version
			urlFromHead = bfReader.readLine();
			headStr += urlFromHead;
			if(urlFromHead != null)
				urlFromHead = urlFromHead.substring(urlFromHead.indexOf(":")+1, urlFromHead.length());
			
			date = bfReader.readLine();	
			headStr += date;
			if(date != null)
				date = date.substring(date.indexOf(":")+1, date.length());
			
			String temp;
			while(!(temp = bfReader.readLine()).trim().isEmpty())
			{ 
				headStr += temp;
			}		

		} catch (IOException e) {
			e.printStackTrace();
		}		
		return headStr;
	}
	
	private String readRawContent(BufferedReader bfReader)
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strBuffer.toString();
	}
	
}
