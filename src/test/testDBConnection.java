package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.util.DBConnection;

public class testDBConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DBConnection dbc = new DBConnection();
//		String sql = "select * from url";
//		ResultSet rs = dbc.executeQuery(sql);
//		try {
//			while(rs.next())
//			{
//				String ID = rs.getString("ID"); // ѡ��sname��������
//				System.out.println(ID + "\t" + rs.getString("url") + "\t"
//						+ rs.getString("RawName")); // ������
//			}
//			
//			rs.close();
//			dbc.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		pst=(PreparedStatement)conn.prepareStatement
		//("insert into devide values('"+no_device+"','"+name_device+"'");
//		insert�������û��')'.�޸�Ϊ:
//		pst=(PreparedStatement)conn.prepareStatement
		//("insert into devide values('"+no_device+"','"+name_device+"')");
		String url = "bba";
		String connent = "aabbbb";
		String offset = "25";
		String raws = "aa";
		
		String sql = "insert into pageindex(url, connent, offset, raws)" +
				" values ('"+url+"', '"+connent+"', '"+offset+"', '"+raws+"')";
		//String sql = "insert into test values('"+url+"', '"+connent+"')";
		dbc.executeUpdate(sql);
	}
}
