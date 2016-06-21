package core.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StopWordsMerger {

	
	public StopWordsMerger()
	{}
	
	public HashSet<String> scanDict(String stopDictFile)
	{
		HashSet<String> dictionary = new HashSet<String>();
		try {
			FileReader fileReader = new FileReader(stopDictFile);
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
			
		return dictionary;
	}
	
	public HashSet<String> mergeSet(HashSet<String> set1, HashSet<String> set2)
	{
		HashSet<String> wordSet = new HashSet<String>();
		
		wordSet.addAll(set1);
		wordSet.addAll(set2);
		
		System.out.println(wordSet.size());
		return wordSet;
	}
	
	public static void main(String[] args) {

		String stopDictFile = "Dictionary\\stopWord.txt";
		String stopDictFile2 = "Dictionary\\stopWord2.txt";
		
		StopWordsMerger merge = new StopWordsMerger();
		HashSet<String> wordSet1 = merge.scanDict(stopDictFile);
		HashSet<String> wordSet2 = merge.scanDict(stopDictFile2);

		HashSet<String> wordSet = merge.mergeSet(wordSet1, wordSet2);
	}

}
