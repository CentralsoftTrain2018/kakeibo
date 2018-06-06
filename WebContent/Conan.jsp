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

    <%
      for (bean.ConanBean cb : bean.getList()) {
    %>
    <%
        if (cb.getDifference() > 0) {
    %>
    <%=cb.getCategoryName()%>は<%=cb.getDifference() %>円プラスだよ。やったね！<br>
    <%
      } else {
    %>
    <%=cb.getCategoryName()%>は<%=cb.getDifference()* -1 %>円マイナスだよ。気をつけよう！<br>
    <%
      }
    %>
    <%
      }
    %>
    <!-- 12月が13月になってしまうバグ -->
   <%=bean.getThisMonth() + 1 %>月もがんばろう！<br><br>
    <a href="BungyServlet">TOPに戻る</a>
  </div>
</body>
</html>