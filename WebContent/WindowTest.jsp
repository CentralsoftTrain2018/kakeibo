<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.BungyBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>WindowTest</title>
</head>
<body>
  <%
    if ( bean.getMessage() != null )
    {
  %>
  <%= bean.getMessage() %><br>
  <%
    }
  %>
  <form method="POST" action="BungyServlet">
      経過日数<input type="text" name="nengetu"><br>
      <input type="submit" value="Bungy"><br>
  </form>
  <form method="POST" action="ConanServlet">
    <input type="submit" value="Conan">
  </form>
  <form method="POST" action="JihakuServlet">
    <input type="submit" value="jihaku">
  </form>
  <form method="POST" action="Expense.jsp">
    <input type="submit" value="Expense">
  </form>
</body>
</html>