<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar"%>

<jsp:useBean id="bean" class="bean.BunsekiListBean" scope="request" />

<!DOCTYPE html>
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
        <form method="POST" action="SetteiServlet">
      <input type="submit" value="設定">
    </form>
  </div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<form method="POST" action="BunsekiServlet" style="margin-top: 10px;">
<select name="year">
<option value="2017">2017</option>
<option value="2018" selected>2018</option>
</select>
<select name="month">
<%for(int i = 1; i <= 12; i++ ){%>
<option value=<%= i %>
<%if(i == bean.getDate().get(Calendar.MONTH)+1) {%>
      selected
      <%} %>> <%= i %></option>
<%} %>

</select>
<input type="submit" value="年月変更">
</form>


<div style="width: 1100px; height: 700px; text-align:center; margin-left:auto; margin-right:auto; position: relative; ">
<div class="en" style="width: 600px; height: 700px; margin-bottom: 0px; padding-bottom: 0px;  position: absolute; left:0px;">

<canvas id="newcanvas"style="width: 100%; height: 100%;"></canvas>

  <p
   style=" position: absolute;
   top: 0;
   left: 0;
   margin: auto;
   right: 0px;
   margin: auto;
   text-align: center;
   top: 50%;
   -webkit-transform: translateY(-50%);
   transform: translateY(-50%);
   ">
   <%=bean.getDate().get(Calendar.YEAR) %>年
   <%=bean.getDate().get(Calendar.MONTH)+1 %>月<br>

   <%if(bean.getList().size() == 0){%>
   データがありません
   <%}else{%>
   合計金額<br>
   <%=bean.getSumSpending() %>円
   <%}%></p>
   </div>
   <div style="display: inline-block;
  vertical-align: top;
  border: solid 1px;
  border-collapse: separate;
  top:0%;
  right:0%;
  position: absolute;">
   <table class="bunseki" >
  <tr >
    <th>色</th>
    <th>項目</th>
    <th>金額</th>
    <th>目標</th>
    <th>差</th>
    <th>割合</th>
  </tr>
    <%for (bean.BunsekiBean bb : bean.getList()) {%>
  <tr>
    <td bgcolor=<%=bb.getColor() %> style="padding:5px 15px;"></td>
    <td style="padding:5px 20px;"><%=bb.getCategoryName() %></td>
    <td style="padding:5px 20px;"><%=bb.getSumSpending() %>円</td>
    <td style="padding:5px 20px;"><%=bb.getMokuhyouKingaku() %>円</td>
    <%if(bb.getDifference() < 0){%>
      <td style="padding:5px 20px;"><font color="red"><%=bb.getDifference() %>円</font></td>
    <%}else{%>
    <td style="padding:5px 20px;"><%=bb.getDifference() %>円</td>
     <%}%>
    <td style="padding:5px 20px;"><%=bb.getWariai() %>%</td>
  </tr>
   <%}%>

</table>
</div>
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