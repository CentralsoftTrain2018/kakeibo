<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.JihakuListBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Jihaku.css" />
<title>自白</title>
</head>
<body>
  <div class="Jihaku">
    <p>
       目標が<%=bean.getMokuhyou()%>円なのに<br>
      <%int go = bean.getGoukei();
        int mo = bean.getMokuhyou();
        int sagaku = go - mo;%>
      <%=sagaku %>円も超えていた!!<br>特に
      <%
        for ( bean.JihakuBean jb : bean.getJihakulist() )
        {
      %>
      <%=jb.getCategoryname()%>が<%=jb.getExcess()%>円、 <br>
      <%
        }
      %>
      超えていて節約できていないから<br>殺したんだよ!!!<br>
    </p>
    <br> <br>
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu" value=""><br>
      <input type="submit" class="square_btn" value="TOPにもどる"><br>
    </form>
  </div>
  <div>

    <img src="image/hannin1.png" class="hannin">
  </div>

</body>
</html>