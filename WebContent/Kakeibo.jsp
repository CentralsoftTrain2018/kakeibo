<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家計簿</title>
</head>
<body>
お金<br>
<form  method="POST" action="KakeiboServlet">
  支出ID<input type="text" name="expenseId"><br>
  金額<input type="text" name="kingaku"><br>
  カテゴリー<input type="text" name="categoryId"><br>
  支出名<input type="text" name="expenseName"><br>
  ユーザーID<input type="text" name="userId"><br>
  登録<input type="radio" name="choice" value="touroku">
  変更<input type="radio" name="choice" value="henkou">
  削除<input type="radio" name="choice" value="sakujo">
  <input type="submit" value="実行">

</form>
</body>
</html>