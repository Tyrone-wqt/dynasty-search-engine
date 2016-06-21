package core.query;

import java.util.ArrayList;
import java.util.HashMap;
import core.preprocess.DictSegment;
import core.preprocess.invertedIndex.InvertedIndex;
import core.util.Result;
import core.util.ResultGenerator;

public class Response {

	//��������
	private HashMap<String, ArrayList<String>> invertedIndexMap;
	//���ؽ���б�
	private ArrayList<Result> results;
	
	//�ִ���
	private DictSegment dictSeg;
	
	private ResultGenerator resultGenerator;
	
	public Response()
	{
		InvertedIndex invertedIndex = new InvertedIndex();
		invertedIndexMap = invertedIndex.createInvertedIndex();
		dictSeg = new DictSegment();
		resultGenerator = new ResultGenerator();
	}
	
	public ArrayList<Result> getResponse(String request)
	{
		doQuery(request);
		return results;
	}
	
	//��ѯ���̣�
	//1. �ؼ��ʷִʡ��޳�ͣ�ôʣ����Էִʽ�����в��Ҷ�Ӧ�Ľ��
	//2. �ϲ������ִʵĽ�������س�������ҳURL��Ϣ
	//3. ����URLͨ�����ݿ�����ҳ����λ�ã��Ӷ���RAWs�л����ҳ����
	//4. ������ҳ���ݣ��޳�TAG�ȱ�ǩ��Ϣ����������ҳ��Result����
	//5. ��JSPҳ������ʾ����б������ʵ��ķ�ҳ
	//6. ��ɿ��չ���
	//ע��㣺
	//1. �������ܵ����⣬�����ҳ��Ƚϴ󣬺ܿ��ܻص�ֻ��ѯ�Ļ�������Դ�Ĵ�������
	//2. ������ҳ����������
	private void doQuery(String request) {
		
		//1. �ؼ��ʷִʡ��޳�ͣ�ôʣ����Էִʽ�����в��Ҷ�Ӧ�Ľ��
		//2. �ϲ������ִʵĽ�������س�������ҳURL��Ϣ
		results = new ArrayList<Result>();
		ArrayList<String> keyWords = dictSeg.cutIntoWord(request);
		
		System.out.println("��ѯ�ؼ��ֱ���Ϊ");
		for(String keyWord : keyWords)
			System.out.println(keyWord);
		System.out.println("�ִʽ����ʾ������ \n");
		
		ArrayList<String> resultUrl = new ArrayList<String>();
		
		ArrayList<String> resultTemp = new ArrayList<String>();
		for(String keyWord : keyWords){
			resultTemp = invertedIndexMap.get(keyWord);			
			if(resultTemp != null){
				resultUrl = mergeResultURL(resultUrl, resultTemp);
			}	
		}
		
		try{
		
			if(resultUrl.size() != 0){
			
				System.out.println("��ѯ�����URL�������£�");
				for(String url : resultUrl)
					System.out.println(url);
		
				// 3. ����URLͨ�����ݿ�����ҳ����λ�ã��Ӷ���RAWs�л����ҳ����
				// ArrayList<Page> pageList = new ArrayList<Page>();
		
				for(String url : resultUrl){
				
					Result tempR = resultGenerator.generateResult(url);
					if(tempR == null)
						System.out.println(url + "��Ӧ��resultΪ�գ�����");
					else
					{
						System.out.println(url + "��Ӧ��result��Ϊ�ա�������");
						results.add(tempR);	
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
			
	}

	private ArrayList<String> mergeResultURL(ArrayList<String> resultUrl,
			ArrayList<String> resultTemp) {
		//�����һ��ִ�У���ôresultUrl���ǿյģ�ֱ�ӷ���resultTemp�Ϳ���
		if(resultUrl.size() == 0)
			return resultTemp;
		
		//������Ҫ�ϲ����ߵĹ�������
		ArrayList<String> RESULT = new ArrayList<String>();
		for(String urlT : resultTemp)
		{
			if(resultUrl.contains(urlT))
				RESULT.add(urlT);
		}
		
		return RESULT;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Response response = new Response();
		ArrayList<Result> results = response.getResponse("�й�����");
		
		System.out.println("���ؽ�����£�");
		for(Result result : results)
		{
			System.out.println(result.getTitle());
			System.out.println(result.getContent());
			System.out.println(result.getUrl() + "  " + result.getDate());
		}
		
	}

}
