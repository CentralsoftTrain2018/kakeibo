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
     年月<input type="text" name="nengetsu"><br>
      <input type="submit" value="バンジー"><br>
  </form>

  <form method="POST" action="ExpenseServlet">
    <input type="submit" value="家計簿">
  </form>

  <form method="POST" action="BunsekiServlet">
    <input type="submit" value="分析">
  </form>
</body>
</html>