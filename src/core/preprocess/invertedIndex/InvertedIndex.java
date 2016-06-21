package core.preprocess.invertedIndex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import core.preprocess.forwardIndex.ForwardIndex;

/************************************************* 
InvertedIndex类建立网页倒排索引，对应关系为词组映射url，通过正向索引来建立
建立过程如下，从正向索引取得索引值，遍历其中url，对于其中每个词组，推入map中作为key
而url作为其value插入，最后得到的map就是倒排索引
*************************************************/  

/**
* <p>Title: InvertedIndex</p> 
* <p>Description: 调用正向索引，遍历得到倒排索引</p> 
* <p>Copyright: Copyright (c) 2010</p> 
* <p>Company: </p> 
*  @author  <a href="dreamhunter.dy@gmail.com">dongyu</a> 
*  @version  1.0 
*  @created 2010-03-17 
*/

public class InvertedIndex {

	private HashMap<String, ArrayList<String>> fordwardIndexMap;
	private HashMap<String, ArrayList<String>> invertedIndexMap;
	
	public InvertedIndex()
	{
		ForwardIndex forwardIndex = new ForwardIndex();
		fordwardIndexMap = forwardIndex.createForwardIndex();
	}
	
	public HashMap<String, ArrayList<String>> createInvertedIndex() {
		
		invertedIndexMap = new HashMap<String, ArrayList<String>>();
		
		//遍历原来的正向索引，进行倒排
		for (Iterator iter = fordwardIndexMap.entrySet().iterator(); iter.hasNext();) 
		{
			Map.Entry entry = (Map.Entry) iter.next(); // map.entry 同时取出键值对
			String url = (String) entry.getKey();
			ArrayList<String> words = (ArrayList<String>) entry.getValue();
			String word;
			for(int i = 0; i < words.size(); i++)
			{
				word = words.get(i);
				//倒排索引中还没有这个key，可以加入这个词，再把url链接上
				if(!invertedIndexMap.containsKey(word))
				{
					ArrayList<String> urls = new ArrayList<String>();
					urls.add(url);
					invertedIndexMap.put(word, urls);
				}
				//索引中已经含有这个key，不许要加入这个key，需要找到这个key从而把url链接上
				else
				{
					ArrayList<String> urls = invertedIndexMap.get(word);
					if(!urls.contains(url))
						urls.add(url);
				}
			}
		}

		System.out.println("***************************************************************");
		System.out.println("create invertedIndex finished!!");
		System.out.println("the size of invertedIndex is : " + invertedIndexMap.size());
		return invertedIndexMap;
	}

	public HashMap<String, ArrayList<String>> getInvertedIndex()
	{
		return invertedIndexMap;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InvertedIndex invertedIndex = new InvertedIndex();
		HashMap<String, ArrayList<String>> invertedIndexMap = invertedIndex.createInvertedIndex();
		
		String key = "的";
		ArrayList<String> urls = invertedIndexMap.get(key);
		
		if(urls != null)
		{
			System.out.println("得到了结果如下：");
			for(String url : urls)
				System.out.println(url);
		}
		else
		{
			System.out.println("真可惜，没找到您要搜索的关键词");
		}
	}

}
