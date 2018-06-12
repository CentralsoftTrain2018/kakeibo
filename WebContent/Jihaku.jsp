<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.JihakuListBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Jihaku.css" />
<title>自白</title>
</head>
<body>
  <div class="Jihaku">
    <p>
      <%
        for ( bean.JihakuBean jb : bean.getJihakulist() )
        {
      %>
      <%=jb.getCategoryname()%>が<%=jb.getExcess()%>円 目標を超えたから殺したんだ!!!<br>
      <%
        }
      %>
    </p>
  </div>
  <div class="hannin1">
    <img src="image/hannin1.png">
  </div>

  <form method="POST" action="BungyServlet">
    <input type="hidden" name="nengetu" value=<%=bean.getNengetsu()%>><br>
    <input type="submit" value="TOPにもどる"><br>
  </form>
</body>
</html>