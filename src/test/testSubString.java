package test;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class testSubString {

	/**
	 * beginIndex - 开始处的索引（包括）。 
		endIndex - 结束处的索引（不包括）。 
	 * @param args
	 */
	public static void main(String[] args) {

//		String str = "<a href=\"http://bbs.life.sina.com.cn/\" target=\"_blank\">";
//		
//		System.out.println(str.indexOf("\""));
//		str = str.substring(str.indexOf("\"")+1);
//		System.out.println(str);
//		str = str.substring(0, str.indexOf("\""));
//		System.out.println(str);
//		
//		String str1 = "<a href=http://bbs.life.sina.com.cn/ target=_blank>\"";
//		System.out.println(str1.indexOf("\"") + "   " + str1.length());
		
//		Date date = new Date();   
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss");   
//      System.out.println("time now:"+df.format(date));
        
//		try {
//			URL url = new URL("http://www.baidu.com");
//			URL url2 = new URL("http://www.baidu.com/s?bs=java+%B4%" +
//					"D3%CD%F8%D6%B7%D3%B3%C9%E4ip&f=8&wd=java+%B4%D3%CD%F8%" +
//					"D6%B7%C8%E7%BA%CE%D5%D2%B5%BDip");
//			String hostofurl2 = url2.getHost();
//			System.out.println(hostofurl2);
//			
//			InetAddress address = InetAddress.getByName(hostofurl2);
//			System.out.println(address.toString());
//			String add = address.toString();
//			add = add.substring(add.indexOf("/")+1, add.length());
//			System.out.println(add);
//			String hostname = address.getHostName();   
//            byte ipAddress[] = address.getAddress();   
//            System.out.println("Host name: "+hostname);   
//            System.out.print("IP Address: ");   
//            for(int i =0; i<ipAddress.length; i++){   
//                System.out.print((ipAddress[i]+256)%256 + ".");   
//            }
            
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
//		Date d = new Date();
//		System.out.println(d.toString());
		
//		HashSet<String> seg = new HashSet<String>();
//		
//		seg.add("一个");
//		seg.add("好人");
//		seg.add("真的");
//		
//		int maxLength = 4;
//		int currLen = 0;
//		String wait2cut = "董宇是一个好人啊真的诶";
//		StringBuffer result = new StringBuffer();
//		
//		while(wait2cut.length() != 0)
//		{
//			String temp;
//			if(wait2cut.length() >= maxLength)
//				currLen = maxLength;
//			else
//				currLen = wait2cut.length();
//				
//			temp = wait2cut.substring(0, currLen);
//			
//			while(!seg.contains(temp) && currLen > 1)
//			{
//				currLen--;
//				temp = temp.substring(0, currLen);
//			}
//			
//			result = result.append(temp + "||");
//			wait2cut = wait2cut.substring(currLen);
//			
//		}
//		
//		System.out.println(result);
		
		String str = "董";
		String str2 = "abcdefg";

		System.out.println(str2.length());
		
	}

}
