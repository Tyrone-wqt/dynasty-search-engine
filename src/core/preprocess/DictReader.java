package core.preprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class DictReader {

	//private static final String dictFile = "Dictionary\\wordlist.txt";
	
	public DictReader()
	{}
	
	public HashSet<String> scanDict(String dictFile)
	{
		HashSet<String> dictionary = new HashSet<String>();
		try {
			FileReader fileReader = new FileReader(dictFile);
			BufferedReader bfReader = new BufferedReader(fileReader);
			
			String word;
			while((word = bfReader.readLine()) != null)
			{
				dictionary.add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("the size of dictionary is: " + dictionary.size());
//		Iterator iter = dictionary.iterator();
//		int count = 20;
//		while(iter.hasNext() && count-- >= 0)
//			System.out.println(iter.next());
			
		return dictionary;
	}
}
