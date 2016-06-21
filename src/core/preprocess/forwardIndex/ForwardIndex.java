package core.preprocess.forwardIndex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import core.preprocess.DictSegment;
import core.preprocess.index.originalPageGetter;
import core.util.DBConnection;

/************************************************* 
ForwardIndex�ཨ����ҳ������������Ӧ��ϵΪurlӳ�䵽��ҳ��������,Ϊ����������׼��
�����������£������ݿ���ȡ��url���жϸ���ҳ�Ƿ��Ѿ������������û�з������������ļ�����ƫ��
�õ���ҳ�����ݣ�����ҳ���ݽ��зִʣ��õ��������Ĵ��飬Ȼ��ʹ��url����������������
*************************************************/  

/**
* <p>Title: ForwardIndex</p> 
* <p>Description: ����ĳ����ҳ�����÷ִ�ϵͳ�����зִʣ�Ȼ��õ�urlӳ�䵽���������</p> 
* <p>Copyright: Copyright (c) 2010</p> 
* <p>Company: </p> 
*  @author  <a href="dreamhunter.dy@gmail.com">dongyu</a> 
*  @version  1.0 
*  @created 2010-03-17 
*/  

public class ForwardIndex {

	private DBConnection dbc = new DBConnection();
	private HashMap<String, ArrayList<String>> indexMap = new HashMap<String, ArrayList<String>>();
	private originalPageGetter pageGetter = new originalPageGetter();
	private DictSegment dictSeg = new DictSegment();
	
	public ForwardIndex()
	{}
	
	public HashMap<String, ArrayList<String>> createForwardIndex()
	{
		try {
			ArrayList<String> segResult = new ArrayList<String>();
			String sql = "select * from pageindex"; // Ҫִ�е�SQL���
			ResultSet rs = dbc.executeQuery(sql);
			String url,fileName;
			int offset = 0;
			
			//�����ݿ��ж����ֶΣ�Ȼ��õ���ҳ֮����зִʴ���
			System.out.println("in the process of creating forwardIndex: ");
			while (rs.next()) {				
				url = rs.getString("url"); // ѡ��sname��������
				System.out.println(url);
				
				if(url.equals("http://www.sogou.com/")){
					System.out.println();
				}
				
				fileName = rs.getString("raws");
				offset = Integer.parseInt(rs.getString("offset"));
				String htmlDoc = pageGetter.getContent(fileName, offset);
				
				segResult = dictSeg.SegmentFile(htmlDoc);
				indexMap.put(url, segResult);
			}

			rs.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("create forwardIndex finished!!");
		System.out.println("the size of forwardIndex is : " + indexMap.size());
		
		return indexMap;
	}
	

	public static void main(String[] args) {
		

		ForwardIndex forwardIndex = new ForwardIndex();
		HashMap<String, ArrayList<String>> indexMap = forwardIndex.createForwardIndex();
		
		for (Iterator iter = indexMap.entrySet().iterator(); iter.hasNext();) {
			
			Map.Entry entry = (Map.Entry) iter.next();    //map.entry ͬʱȡ����ֵ��
		    String url = (String) entry.getKey();
		    ArrayList<String> words = (ArrayList<String>) entry.getValue();

		    System.out.println(url + " ��Ӧ�ķִʽ���ǣ� " + words.size());
		}
		
	}

}
