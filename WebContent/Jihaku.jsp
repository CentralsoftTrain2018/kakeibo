<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.JihakuBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Jihakucss.css" />
<title>自白</title>
</head>
<body>
<div class="Jihaku">
  <p><%=bean.toString() +"目標を超えたから殺したんだ!!!"%></p>
</div>
<div class="hannin1">
  <img src="image/hannin1.png">
</div>
</body>
</html>