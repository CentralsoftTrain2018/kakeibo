<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<jsp:useBean id="bean" class="bean.BunsekiListBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
<canvas id="newcanvas"
  style="width: 800px;
  height: 742px;"></canvas>
<script >
var data = [
  <%
    for ( bean.BunsekiBean bb : bean.getList())
    {
    %>
   {
    value: <%=bb.getSisyutu()%>,
    color:<%=bb.getColor()%>,
    label:<%=bb.getCategoryName()%>
   },
   <%}%>

  ];
var myChart = new Chart(document.getElementById("newcanvas").getContext("2d")).Doughnut(data);
</script>
</body>
</html>