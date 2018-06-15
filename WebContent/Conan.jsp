<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.Calendar"%>
<jsp:useBean id="bean" class="bean.ConanListBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery.qrcode.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/Conan.css">
<title>コナンくんからのアドバイス</title>
</head>
<body>
  <div id="qrcode"></div>
  <script>
    jQuery('#qrcode').qrcode({
      text : "I am Ochi Yosuke!!",
      width : 50,
      height : 50,
    });
  </script>
  <div style="width: 1000px; height: 500px; text-align:center; position: relative; text-align:center; margin-left:auto; margin-right:auto;">
  <img src="image/conan.png" style="left:0%; position: absolute;">
  <div class="balloon" style="left:40%; top:auto; position: absolute;">
    <%=bean.getDate().get( Calendar.YEAR )%>年
    <%=bean.getDate().get( Calendar.MONTH )%>月 は目標達成だね！おめでとう(^_^)/♪<br>
    君のおかげで犯人を逮捕することができたよ！<br>

    <%
      for ( bean.ConanBean cb : bean.getList() )
      {
    %>
    <%
      if ( cb.getDifference() > 0 )
        {
    %>
    <%=cb.getCategoryName()%>は目標より<%=cb.getDifference()%>円節約できたよ。やったね！<br>
    <%
      } else if ( cb.getDifference() == 0 )
        {
    %>
    <%=cb.getCategoryName()%>は目標ピッタリだよ。おめでとう！<br>
    <%
      } else
        {
    %>
    <%=cb.getCategoryName()%>は目標より<%=cb.getDifference() * -1%>円オーバーだよ。気をつけよう！<br>
    <%
      }
    %>
    <%
      }
    %>
    来月もがんばろう！<br> <br>
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu" value=""><br> <input
        type="submit" value="TOPにもどる"><br>
    </form>
  </div>
  </div>
</body>
</html>