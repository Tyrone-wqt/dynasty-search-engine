package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testSougouOffset {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String content = "";
		BufferedReader bfReader = null;
		try {			
			//从数据库中读出的文件名中不含有分隔符，需要预处理一下
			String fileName = "Raws\\RAW__3.txt";
			
			FileReader fileReader = new FileReader(fileName);
			bfReader = new BufferedReader(fileReader);

			String word;
			bfReader.skip(35050);
			
			int count = 0;
			String temp;
			while((temp=bfReader.readLine()) != null && count++ <= 5)
				System.out.println(temp);
			
//			String t1 = "wokao";
//			String t2 = "wokao"+"\n";
//			System.out.println(t1.length());
//			System.out.println(t2.length());
			
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
	}

}
