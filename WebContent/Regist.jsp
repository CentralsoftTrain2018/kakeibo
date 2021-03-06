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
  <form method="POST" action="RegistServlet" onSubmit="return double()">
    <ul>
      <li><h2>会員登録</h2></li>
      <li><div id="errorMessage">
          <%
            if (bean.getMessage() != null) {
          %>
          <%=bean.getMessage()%>
          <%
            }
          %>
        </div></li>
      <li><label>ユーザーID</label> <input type="text" name="userId"
        pattern="^[0-9A-Za-z]+$" required>（半角英数）</li>
      <li><label>PASS</label> <input type="password" name="password"
        id="password" required></li>
      <li><label>PASS(確認)</label> <input type="password"
        name="passwordConfirm" id="passwordConfirm" required
        oninput="check(this)"></li>
      <li><label>メールアドレス</label> <input type="email" name="mail"
        required oninput="mailCheck(this)"></li>
      <li class="under"><label>月収</label> <input type="number"
        name="income" min="0" required>円</li>
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
    function mailCheck(input) {
      if (input.match(/^[^\x01-\x7E\xA1-\xDF]+$/)) {
        input.setCustomValidity('半角英数字と@ . 以外は使用できません');
      } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
      }
    }
    var set=0;
    function double() {
    if(set==0){ set=1; } else {
    alert("只今処理中です。\nそのままお待ちください。");
    return false; }}
  </script>
</body>
</html>