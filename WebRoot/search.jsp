<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:directive.page import="core.query.Response" />
<jsp:directive.page import="core.util.Result" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Search Result</title>
    
    <style>
	#search{
	width:78px;
	height:28px;
	font:14px "宋体"
	}
	
	#textArea{
	width:300px;
	height:30px;
	font:14px "宋体"
	}
	</style>

  </head>
  
  <body>
    
    <%	
		String keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"),"GB2312"); 	
	%>
	
	
    <form action="search.jsp" name="search" method="get">
	<table border="0" height="30px" width="450px" align="center">		
		<tr>
			<td><img src="dySE-logo.jpg" /></td>
			<td width ="66%"><input name="keyword" type="text" 
				maxlength="100" id="textArea" value=<%=keyword%>></td>
			<td height="29" align="center"><input type="submit" value="搜索一下" id = "search"></td>
		</tr>
	</table>
	</form>
	
	
	
	<%  
		Response resp = new Response();
		ArrayList<Result> results = resp.getResponse(keyword);
		
		System.out.println("返回结果如下：");
		for(Result result : results)
		{
	%>	
			<h2><a href=<%=result.getUrl()%>><%=result.getTitle()%></a></h2>
			<p><%=result.getContent()%><p>
			<p><%=result.getUrl()%> &nbsp;&nbsp;&nbsp; <%=result.getDate()%><p>
	<%  		
		}
	%>
    	
  </body>
</html>
