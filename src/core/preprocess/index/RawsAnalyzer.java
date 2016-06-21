package core.preprocess.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.util.DBConnection;
import core.util.MD5;
import core.util.Page;

/************************************************* 
RawsAnalyzer类实现了从原始网页集合Raws的分析操作，在完整MD5摘要算法之后，
建立网页URL、网页内容摘要、网页在Raws中偏移的映射、所属Raws的映射 
算法传入的参数为raws所在的目录，需要遍历其中的众多文件
*************************************************/  

/**
* <p>Title: RawsAnalyzer</p> 
* <p>Description: 从原始网页集合，完成映射解析</p> 
* <p>Copyright: Copyright (c) 2003</p> 
* <p>Company: </p> 
*  @author  <a href="dreamhunter.dy@gmail.com">dongyu</a> 
*  @version  1.0 
*  @created 2010-03-16 
*/  

public class RawsAnalyzer {

	private DBConnection dbc = new DBConnection();
	private MD5 md5 = new MD5();
	private int offset;
	private Page page;
	private String rootDirectory;
	
	public RawsAnalyzer(String rootName)
	{
		this.rootDirectory = rootName;
		page = new Page();
	}
	
	public void createPageIndex()
	{
		ArrayList<String> fileNames = getSubFile(rootDirectory);
		for(String fileName : fileNames)
			createPageIndex(fileName);	
	}
	
	public void createPageIndex(String fileName)
	{
		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bfReader = new BufferedReader(fileReader);

			String word;
			offset = 0;
			int oldOffset = 0;
			
			//bfReader已经把version:1.0读入了
			while((word = bfReader.readLine()) != null)
			{
				oldOffset = offset;
				offset += word.length() + 1;
				String url = readRawHead(bfReader);
				String content = readRawContent(bfReader);
				
				System.out.println("the offset in " + fileName +" is: " + offset);
				String contentMD5 = md5.getMD5ofStr(content);
				page.setPage(url, oldOffset, contentMD5, fileName);
				page.add2DB(dbc);
			}	
			
			bfReader.close();
			//dbc.close();
			
			System.out.println("finish the exectution of this raw file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readRawHead(BufferedReader bfReader)
	{		
		String urlLine = null;
		try {
			
			urlLine = bfReader.readLine();	
			offset = offset + urlLine.length() + 1;
			if(urlLine != null)
				urlLine = urlLine.substring(urlLine.indexOf(":")+1, urlLine.length());
			
			String temp;
			while(!(temp = bfReader.readLine()).trim().isEmpty())
			{
				offset = offset + temp.length() + 1;
			}		
			offset += 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return urlLine;
	}
	
	private String readRawContent(BufferedReader bfReader)
	{
		StringBuffer strBuffer = new StringBuffer();
		
		try {		
			String word;
			while((word = bfReader.readLine()) != null)
			{
				offset = offset + word.length() + 1;
				if(word.trim().isEmpty())
					break;
				else
					strBuffer.append(word + "\n");
			}
			offset += 2;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strBuffer.toString();
	}
	
	public static ArrayList<String> getSubFile(String fileName) {   
		
		ArrayList<String> fileNames = new ArrayList<String>();
		
		File parentF = new File(fileName);   
		
		if (!parentF.exists()) {   
			System.out.println("unexisting file or directory");   
		    return null;   
		}   
		if (parentF.isFile()) {   
			System.out.println("it is a file");
			fileNames.add(parentF.getAbsolutePath());   
		    return fileNames;   
		}   
		
		System.out.println(fileName + " isn't  a file");
		String[] subFiles = parentF.list();   
		for (int i = 0; i < subFiles.length; i++) {   
			fileNames.add(fileName + "\\" + subFiles[i]); 
		}   
		
		return fileNames;
	}
	
	public static void main(String[] args) {

		RawsAnalyzer analyzer = new RawsAnalyzer("Raws");
		analyzer.createPageIndex();

	}
	
}
