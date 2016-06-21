package core.preprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

import configure.Configuration;

import core.util.HtmlParser;

//�ִ���,����ģʽ
public class DictSegment {

	private HashSet<String> dict;            //�ʵ�
	private HashSet<String> stopWordDict;            //ͣ�ôʴʵ�
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
	
	
	//htmlDoc��Ԥ���������ȡbody�ı�ǩ��ͬʱ�ж�ҳ������ͣ���hub��img��������ҳ��
	//����������ȡ���ģ����ձ����ŶϾ���µ�htmlDoc
	//���̣���htmlDoc�ж�ȡ����S�����û��S�ˣ��㷨����
	//������У���ȡS�����þ����дʳ������������ȡS
	//���������޳�ͣ�ôʣ�ͳ��
	public ArrayList<String> SegmentFile(String htmlDoc)
	{
		//��һ����������html���ļ���������ʽ����ȥ����ǩ��������Ϣ�������ı����в���
		HtmlParser parser = new HtmlParser();
		String htmlText = parser.html2Text(htmlDoc);
		
		//�Ͼ�cutIntoSentance���Ѿ��Ӵ���cutIntoWord��Ȼ���÷���ֵ
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
	    //����StringTokenizer��Ķ���tokenizer,�������ַ���tokenizer�ķ�����
        //�Կո����","��"."��"!"��Ϊ�����
		ArrayList<String> sentance = new ArrayList<String>();
		
		String token = "������������������������������-";
	    StringTokenizer tokenizer = new StringTokenizer(htmlDoc,token);

	    //��ȡ�ַ���str1�����Է��ŵĸ���
	    int num = tokenizer.countTokens();
	    
	    //����ѭ������ȡ�ַ���str1����һ�����Է���,�����
	    while(tokenizer.hasMoreTokens()) 
	    	sentance.add(tokenizer.nextToken());

		return sentance;
	}
	
	//���һ�仰�к�����ĸ�������֣���ЩӦ�ò����зֵ��������û����
	//����ͣ�ôʣ����˵���
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
			
			//�����temp�ǷֺõĴʣ��ж�temp�Ƿ���ͣ�ôʱ��У�������ǣ������list��
			if(!stopWordDict.contains(temp) && temp.length() != 1)
				sentanceSegResult.add(temp);
			
			//����ȥ��temp���ȵ��ַ���������ִ��
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
