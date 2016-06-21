<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"
	+request.getServerName()+":"
	+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>dySE</title>
      
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
	<p align="center"><img src="dySE-logo.jpg" /></p>
	
	
	<form action="search.jsp" name="search" method="get" 
			enctype="application/x-www-form-urlencoded">
	<table border="0" height="30px" width="450px" align="center">		
		<tr>
			<td width ="66%"><input name="keyword" type="text" maxlength="100" 
					id="textArea"></td>
			<td height="29" align="center"><input type="submit" value="搜索一下" 
					id = "search"></td>
		</tr>
	</table>
	</form>
  </body>

</html>
