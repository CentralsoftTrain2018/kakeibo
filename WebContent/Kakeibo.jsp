<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.KakeiboBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家計簿</title>
</head>
<body>
<%if(bean.getMessage() != null){%>
<%= bean.getMessage() %><br>
<%} %>
入力してね<br>
<form  method="POST" action="KakeiboServlet">
  支出ID<input type="text" name="expenseId"><br>
  金額<input type="text" name="kingaku"><br>
  カテゴリー<input type="text" name="categoryId"><br>
  支出名<input type="text" name="expenseName"><br>
  登録<input type="radio" name="choice" value="touroku" checked>
  変更<input type="radio" name="choice" value="henkou">
  削除<input type="radio" name="choice" value="sakujo">
  <input type="submit" value="実行">

</form>
</body>
</html>