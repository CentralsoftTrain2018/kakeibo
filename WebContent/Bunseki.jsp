<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8" %>
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
      <input type="hidden" name="nengetu" value="2018/05">
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
<option value="0">1</option>
<option value="1">2</option>
<option value="2">3</option>
<option value="3">4</option>
<option value="4">5</option>
<option value="5">6</option>
<option value="6">7</option>
<option value="7">8</option>
<option value="8">9</option>
<option value="9">10</option>
<option value="10">11</option>
<option value="11">12</option>
</select>
<input type="submit" value="年月変更">
</form>
<div style="width: 400px; height: 371px; margin-bottom: 0px; padding-bottom: 0px; position: relative;">
<canvas id="newcanvas"
  style="width: 100%; height: 100%;"
  ></canvas>
  <p
   style=" position: absolute;
   top: 0;
   left: 0;
   margin: auto;
   right: 0;
   margin: auto;
   text-align: center;
   top: 50%;
   -webkit-transform: translateY(-50%);
   transform: translateY(-50%);
   ">
   合計金額<br>
   <%=bean.getSumSpending() %></p>
</div>
<script >
var data = [
  <%
    for ( bean.BunsekiBean bb : bean.getList())
    {
    %>
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