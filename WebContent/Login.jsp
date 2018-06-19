<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.LoginBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WindowTest</title>
</head>
<body>
<%if(bean.getMessage() != null){%>
<%= bean.getMessage() %><br>
<%} %>
<form  method="POST" action="LoginServlet">
  ユーザーID<input type="text" name="userId" value="1"><br>
  <input type="submit" value="ログイン"><br>
</form>
<form  method="POST" action="/Regist.jsp">
  <input type="submit" value="新規"><br>
</form>
</body>
</html>