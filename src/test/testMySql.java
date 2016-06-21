package test;

import java.sql.*;
import java.sql.*;

public class testMySql {

	public static void main(String[] args) {

		String driver = "com.mysql.jdbc.Driver"; // ����������
		String url = "jdbc:mysql://localhost/search_engine"; // URLָ��Ҫ���ʵ����ݿ���scutcs
		String user = "root"; // MySQL����ʱ���û���
		String password = "dongyu"; // MySQL����ʱ������

		try {

			Class.forName(driver); // ������������
			Connection conn = DriverManager.getConnection(url, user, password); // �������ݿ�

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!"); // ��֤�Ƿ����ӳɹ�

			Statement statement = conn.createStatement(); // statement����ִ��SQL���

			String sql = "select * from url"; // Ҫִ�е�SQL���

			ResultSet rs = statement.executeQuery(sql); // �����

			System.out.println("-----------------------------------------");
			System.out.println("ִ�н��������ʾ:");
			System.out.println("-----------------------------------------");
			System.out.println(" ID" + "\t" + " URL" + "\t\t" + "RAWS");
			System.out.println("-----------------------------------------");

			String ID = null;

			while (rs.next()) {

				ID = rs.getString("ID"); // ѡ��sname��������

				System.out.println(ID + "\t" + rs.getString("url") + "\t"
						+ rs.getString("RawName")); // ������
			}

			rs.close();
			conn.close();

		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
