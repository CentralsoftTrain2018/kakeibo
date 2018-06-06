<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.ConanListBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/balloon.css">
<title>コナンくんからのアドバイス</title>
</head>
<body>
<img src="image/conan.png" style="float:left;">
<div class="balloon">
<%=bean.toString() %>
<% for( bean.ConanBean cb: bean.getList() ){%>
<%=cb.toString() %>	<br>
<% }%>
</div>
</body>
</html>