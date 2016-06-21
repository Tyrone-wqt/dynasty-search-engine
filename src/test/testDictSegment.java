package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.preprocess.DictSegment;
import core.util.HtmlParser;

public class testDictSegment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dictFile = "test\\testHtml.txt";
		try {
			FileReader fileReader = new FileReader(dictFile);
			BufferedReader bfReader = new BufferedReader(fileReader);
			StringBuffer result = new StringBuffer();
			
			String word;
			while((word = bfReader.readLine()) != null)
			{
				result.append(word + "\n");
			}
			
			String htmlDoc = result.toString();
			System.out.println(htmlDoc.length());
			
			htmlDoc = (new HtmlParser()).html2Text(htmlDoc);
			
			DictSegment seg = new DictSegment();
			ArrayList<String> segResult = seg.SegmentFile(htmlDoc);
			
			int count = 0;
			for(String temp : segResult)
			{
				if(count++ == 100)
				{
					System.out.println();
					count = 0;
				}
					
				System.out.println(temp);
			}
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
