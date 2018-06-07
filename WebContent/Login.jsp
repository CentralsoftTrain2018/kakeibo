<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.BungyBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>WindowTest</title>
</head>
<body>
<%if(bean.getMessage() != null){%>
<%= bean.getMessage() %><br>
<%} %>
<form  method="POST" action="LoginServlet">
  ユーザーID<input type="text" name="userId"><br>
  <input type="submit" value="ログイン"><br>
</form>
</body>
</html>