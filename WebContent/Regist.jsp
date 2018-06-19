<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.RegistBean" scope="request" />
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
      <li><div id="errorMessage">
      <%if(bean.getMessage() != null){%>
      <%= bean.getMessage() %>
      <%} %>
      </div></li>
      <li><label>ユーザーID</label> <input type="text" name="userId"
        pattern="^[0-9A-Za-z]+$" required>（半角英数）</li>
      <li><label>PASS</label> <input type="password" name="password"
        id="password" required></li>
      <li><label>PASS(確認)</label> <input type="password"
        name="passwordConfirm" id="passwordConfirm" required oninput="check(this)"></li>
      <li><label>メールアドレス</label> <input type="email" name="mail"
        required></li>
      <li class="under"><label>収入</label> <input type="number"
        name="income" required>円</li>
      <li><input type="submit" value="登録"> <input type="reset"
        value="リセット"></li>
    </ul>
  </form>
  <script>
function check(input) {
  if (input.value != document.getElementById('password').value) {
    input.setCustomValidity('パスワードが一致しません');
  } else {
    // input is valid -- reset the error message
    input.setCustomValidity('');
  }
}
</script>
</body>
</html>