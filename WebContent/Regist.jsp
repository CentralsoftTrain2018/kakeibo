<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/form.css">
<title>会員登録</title>
</head>
<body>
  <form method="POST" action="RegistServlet">
    <ul>
    <li><h2>会員登録</h2></li>
      <li class="userId">
        <label for="userId">ユーザーID</label>
        <input type="text" name="userId"></li>
      <li class="pass">
        <label for="pass">PASS</label>
        <input type="password" name="pass"></li>
      <li class="pass2">
        <label for="pass2">PASS(確認)</label>
        <input type="password" name="pass2"></li>
      <li class="mail">
        <label for="mail">メールアドレス</label>
        <input type="email" name="mail"></li>
      <li class="income">
        <label for="income">収入</label>
        <input type="text" name="income"></li>
      <li><input type="submit" value="登録">
      <input type="reset" value="リセット"></li>
      </ul>
  </form>

</body>
</html>