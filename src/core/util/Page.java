package core.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Page {

	private String url;
	private int offset;
	private String connent;
	private String rawName;
	
	public Page()
	{
		
	}
	
	public String getUrl() {
		return url;
	}

	public int getOffset() {
		return offset;
	}

	public String getConnent() {
		return connent;
	}

	public String getRawName() {
		return rawName;
	}

	public Page(String url, int offset, String connent, String rawName)
	{
		this.url = url;
		this.offset = offset;
		this.connent = connent;
		this.rawName = rawName;
	}
	
	public void setPage(String url, int offset, String connent, String rawName)
	{
		this.url = url;
		this.offset = offset;
		this.connent = connent;
		this.rawName = rawName;
	}

	public void add2DB(DBConnection dbc) {

		String sql = "insert into pageindex(url, connent, offset, raws)" +
			" values ('"+url+"', '"+connent+"', '"+offset+"', '"+rawName+"')";
		dbc.executeUpdate(sql);
	}
	
}
