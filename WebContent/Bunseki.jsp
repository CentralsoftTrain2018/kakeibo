<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar"%>

<jsp:useBean id="bean" class="bean.BunsekiListBean" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<title>分析</title>
</head>
<body>
  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu" value="">
      <input type="submit" value="バンジー">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析" disabled="disabled">
    </form>
  </div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<form method="POST" action="BunsekiServlet">
<select name="year">
<option value="2017">2017</option>
<option value="2018" selected>2018</option>
</select>
<select name="month">
<%for(int i = 1; i <= 12; i++ ){%>
<option value=<%= i %>
<%if(i == bean.getDate().get(Calendar.MONTH)) {%>
      selected
      <%} %>> <%= i %></option>
<%} %>

</select>
<input type="submit" value="年月変更">
</form>
<div class="en" style="width: 800px; height: 600px; margin-bottom: 0px; padding-bottom: 0px;  position: relative;">
<canvas id="newcanvas" style="width: 50%; height: 100%;" ></canvas>
  <p
   style=" position: absolute;
   top: 0;
   left: 0;
   margin: auto;
   right: 0;
   margin: auto;
   text-align: center;
   top: 45%;
   -webkit-transform: translateY(-50%);
   transform: translateX(-25%);
   ">
   <%=bean.getDate().get(Calendar.YEAR) %>年
   <%=bean.getDate().get(Calendar.MONTH) %>月<br>

   <%if(bean.getList().size() == 0){%>
   データがありません
   <%}else{%>
   合計金額<br>
   <%=bean.getSumSpending() %>円
   <%}%></p>
   <table class="bunseki">
  <tr>
    <th>色</th>
    <th>項目</th>
    <th>金額</th>
    <th>目標</th>
    <th>差</th>
    <th>割合</th>
  </tr>
    <%for (bean.BunsekiBean bb : bean.getList()) {%>
  <tr>
    <td bgcolor=<%=bb.getColor() %>></td>
    <td><%=bb.getCategoryName() %></td>
    <td><%=bb.getSumSpending() %>円</td>
    <td><%=bb.getMokuhyouKingaku() %>円</td>
    <%if(bb.getDifference() < 0){%>
      <td><font color="red"><%=bb.getDifference() %>円</font></td>
    <%}else{%>
    <td><%=bb.getDifference() %>円</td>
     <%}%>
    <td><%=bb.getWariai() %>%</td>
  </tr>
   <%}%>

</table>
</div>
<script >
var data = [
  <%for (bean.BunsekiBean bb : bean.getList()) {%>
   {
    value:"<%=bb.getSumSpending()%>",
    color:"<%=bb.getColor()%>",
    label:"<%=bb.getCategoryName()%>"
   },
   <%}%>
  ];
var myChart = new Chart(document.getElementById("newcanvas").getContext("2d")).Doughnut(data);
</script>

</body>
</html>