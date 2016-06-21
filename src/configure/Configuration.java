package configure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	/**
	 * ��ȡproperties�ļ�
	 * @author Qutr
	 */
	private Properties propertie;
	private FileInputStream inputFile;
	private FileOutputStream outputFile;

	/**
	 * ��ʼ��Configuration��
	 */
	public Configuration() {
		propertie = new Properties();
		try {
			propertie.load(getClass().getClassLoader().
					getResourceAsStream("configure.properties"));
		} catch (FileNotFoundException ex) {
			System.out.println("�ļ�·����������ļ�������");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("װ���ļ�ʧ��!");
			ex.printStackTrace();
		}
	}

	/**
	 * ���غ������õ�key��ֵ
	 * @param key ȡ����ֵ�ļ�
	 * @return key��ֵ
	 */
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);//�õ�ĳһ���Ե�ֵ
			return value;
		} else
			return "";
	}


	public static void main(String[] args) {
		Configuration conf = new Configuration();

		String rawsPath = conf.getValue("RAWSPATH");
		String dictPath = conf.getValue("DICTIONARYPATH");
		String mysqlPath = conf.getValue("MYSQLLIBPATH");

		System.out.println("the rawsPath is " + rawsPath);
		System.out.println("the dictPath is " + dictPath);
		System.out.println("the mysqlPath is " + mysqlPath);
	}

}
