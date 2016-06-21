package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import core.util.DBConnection;
import core.util.MD5;
import core.util.Page;

public class testOffset {

	//之后的offset计算的时候需要+2，是因为，
	//readline函数读取的字符串忽略最后的换行符等，而换行符在其中占2个字符
	private static int offset = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String dictFile = "test\\testSpace.txt";
		try {
			FileReader fileReader = new FileReader(dictFile);
			BufferedReader bfReader = new BufferedReader(fileReader);
			DBConnection dbc = new DBConnection();
			MD5 md5 = new MD5();
			String word;
			Page page = new Page();
			offset = 0;
			int oldOffset = 0;
			//bfReader已经把version:1.0读入了
			while((word = bfReader.readLine()) != null)
			{
				oldOffset = offset;
				offset += word.length() + 2;
				String url = readRawHead(bfReader);
				//System.out.println("the offset is: " + offset);
				String content = readRawContent(bfReader);
				System.out.println("the offset is: " + offset);
				String contentMD5 = md5.getMD5ofStr(content);
				page.setPage(url, oldOffset, contentMD5, dictFile);
				//page.add2DB();
			}	
			
			bfReader.close();
			dbc.close();
			System.out.println("finish all the program");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readRawHead(BufferedReader bfReader)
	{		
		String urlLine = null;
		try {
			
			urlLine = bfReader.readLine();	
			offset = offset + urlLine.length() + 2;
			if(urlLine != null)
				urlLine = urlLine.substring(urlLine.indexOf(":")+1, urlLine.length());
			
			String temp;
			while(!(temp = bfReader.readLine()).trim().isEmpty())
			{
				offset = offset + temp.length() + 2;
			}
			
			offset += 2;
			//System.out.println("head finished! get url: " + urlLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return urlLine;
	}
	
	public static String readRawContent(BufferedReader bfReader)
	{
		StringBuffer strBuffer = new StringBuffer();
		
		try {		
			String word;
			while((word = bfReader.readLine()) != null)
			{
				offset = offset + word.length() + 2;
				if(word.trim().isEmpty())
					break;
				else
					strBuffer.append(word + "\n");
			}
			
			System.out.println("readRawContent finished! get content: ");
			//System.out.println(strBuffer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strBuffer.toString();
	}
	
	public static int computeOffset(BufferedReader bfReader)
	{
		//int offset = bfReader.
		return 0;
	}

}
