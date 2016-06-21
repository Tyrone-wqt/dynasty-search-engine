package core.preprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

import configure.Configuration;

import core.util.HtmlParser;

//分词类,单例模式
public class DictSegment {

	private HashSet<String> dict;            //词典
	private HashSet<String> stopWordDict;            //停用词词典
	private DictReader dictReader = new DictReader();
	private static final int maxLength = 4;
	private static String dictFile = "";
	private static String stopDictFile = "";
	private Configuration conf;
	public DictSegment()
	{
		conf = new Configuration();
		dictFile = conf.getValue("DICTIONARYPATH")+"\\Dictionary\\wordlist.txt";
		stopDictFile = conf.getValue("DICTIONARYPATH")+"\\Dictionary\\stopWord.txt";
		
		dict = dictReader.scanDict(dictFile);
		stopWordDict = dictReader.scanDict(stopDictFile);
	}
	
	
	//htmlDoc的预处理，比如截取body的标签，同时判断页面的类型，是hub，img还是主题页面
	//根据内容提取中文，按照标点符号断句成新的htmlDoc
	//流程，从htmlDoc中读取句子S，如果没有S了，算法结束
	//如果还有，读取S，调用句子切词程序，切完继续读取S
	//后续处理：剔除停用词，统计
	public ArrayList<String> SegmentFile(String htmlDoc)
	{
		//第一步操作，把html的文件用正则表达式处理，去掉标签等无用信息，保留文本进行操作
		HtmlParser parser = new HtmlParser();
		String htmlText = parser.html2Text(htmlDoc);
		
		//断句cutIntoSentance，把句子传到cutIntoWord，然后获得返回值
		ArrayList<String> sentances = cutIntoSentance(htmlText);
		ArrayList<String> segResult = new ArrayList<String>();
		for(int i = 0; i < sentances.size(); i++)
		{
			segResult.addAll(cutIntoWord(sentances.get(i)));
		}
		
		return segResult;
	}
	
//	public ArrayList<String> SegmentKeyWord(String keyWord)
//	{
//		return cutIntoWord(keyWord);
//	}
	
	public ArrayList<String> cutIntoSentance(String htmlDoc)
	{
	    //创建StringTokenizer类的对象tokenizer,并构造字符串tokenizer的分析器
        //以空格符、","、"."及"!"作为定界符
		ArrayList<String> sentance = new ArrayList<String>();
		
		String token = "。，、；：？！“”‘’《》（）-";
	    StringTokenizer tokenizer = new StringTokenizer(htmlDoc,token);

	    //获取字符串str1中语言符号的个数
	    int num = tokenizer.countTokens();
	    
	    //利用循环来获取字符串str1中下一个语言符号,并输出
	    while(tokenizer.hasMoreTokens()) 
	    	sentance.add(tokenizer.nextToken());

		return sentance;
	}
	
	//如果一句话中含有字母或者数字，这些应该不用切分掉，这个还没处理
	//过滤停用词，过滤单字
	public ArrayList<String> cutIntoWord(String sentance)
	{	
		int currLen = 0;
		String wait2cut = sentance;
		ArrayList<String> sentanceSegResult = new ArrayList<String>();
		
		while(wait2cut.length() != 0)
		{
			String temp;
			if(wait2cut.length() >= maxLength)
				currLen = maxLength;
			else
				currLen = wait2cut.length();
				
			temp = wait2cut.substring(0, currLen);
			
			while(!dict.contains(temp) && currLen > 1)
			{
				currLen--;
				temp = temp.substring(0, currLen);
			}
			
			//到这里，temp是分好的词，判断temp是否在停用词表中，如果不是，则放入list中
			if(!stopWordDict.contains(temp) && temp.length() != 1)
				sentanceSegResult.add(temp);
			
			//句子去除temp长度的字符串，继续执行
			wait2cut = wait2cut.substring(currLen);	
		}
		
		//System.out.println(result);		
		return sentanceSegResult;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DictSegment dictSeg = new DictSegment();
	}

}
