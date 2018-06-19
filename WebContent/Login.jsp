<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.LoginBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/form.css">
<title>Login</title>
</head>
<body>
  <img src="image/logo.png" alt="顛落の家計簿">
  <form method="POST" action="RegistServlet">
    <ul>
      <li><div id="errorMessage">
      <%if(bean.getMessage() != null){%>
      <%= bean.getMessage() %>
      <%} %>
      </div></li>
      <li><label>ユーザーID</label> <input type="text" name="userId"
        pattern="^[0-9A-Za-z]+$" required>（半角英数）</li>
      <li><label>PASS</label> <input type="password" name="password"
        id="password" required></li>
      <li><input type="submit" value="ログイン"></li>
    </ul>
  </form>
</body>
</html>