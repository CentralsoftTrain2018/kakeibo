<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.ConanListBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/balloon.css">
<title>コナンくんからのアドバイス</title>
</head>
<body>
  <img src="image/conan.png" style="float: left;">
  <div class="balloon">
    <%=bean.getThisMonth()%>月は目標達成だね！おめでとう(^_^)/♪<br>
    君のおかげで犯人を逮捕することができたよ！<br>
    合計目標金額は<%=bean.getTotalGoal() %>円、支出合計は<%=bean.getTotalSpending() %>円、
    差額は<%=bean.getTotalDifference() %>円だよ。<br>
    <% for (bean.ConanBean cb : bean.getList()) { %>
    <%=cb.getCategoryName() %>の目標金額は<%=cb.getMokuhyouKingaku() %>円、
    支出合計は<%=cb.getSumSpending() %>円、差額は<%=cb.getDifference() %>円だよ。<br>
    <%}%>
    <a href="BungyServlet">TOPに戻る</a>
  </div>
</body>
</html>