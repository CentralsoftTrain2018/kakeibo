<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.ConanBean" scope="request" />
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
  <%=bean.getThisMonth() %>月は目標達成だね！おめでとう(^_^)/♪<br>
  君のおかげで犯人を逮捕することができたよ！<br>
  目標金額より<%=bean.getTotal() %>円節約できたね。えらい！！<br>
  一番節約したのは<%=bean.getGoodCategory() %>で、<%=bean.getGoodDifference() %>円も節約できたね！<br>
  でも<%=bean.getBadCategory() %>は<%=bean.getBadDifference() %>円オーバーしちゃったね･･･。気をつけよう！<br>
  この調子で<%=bean.getNextMonth() %>月もがんばってね！
</div>
</body>
</html>