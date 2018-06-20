<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.LoginBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/form.css">
<title>ログイン</title>
</head>
<body>
  <div id="logo">
    <img src="image/logo.png" alt="顛落の家計簿">
  </div>

  <form method="POST" action="LoginServlet">
    <ul>
      <li><div id="errorMessage">
          <% if ( bean.getMessage() != null ){ %>
          <%= bean.getMessage() %>
          <% } %>
        </div></li>
      <li><label>ユーザーID</label>
      <input type="text" name="userId" pattern="^[0-9A-Za-z]+$" required>（半角英数）</li>
      <li class="under"><label>PASS</label>
      <input type="password" name="password" id="password" required></li>
      <li><button type="submit" name="action" value="login" style="margin:0px; float:left;">ログイン</button></li>
    </ul>
  </form>
  <form method="POST" action="Regist.jsp">
    <button type="submit" name="action" value="regist" style="margin:0px 230px; float:left;">新規登録</button>
  </form>
</body>
</html>