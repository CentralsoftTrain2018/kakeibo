<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.ExpenseBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家計簿</title>
</head>
<body>
<%if(bean.getMessage() != null){%>
<%= bean.getMessage() %><br>
<%} %>
入力してね<br>
<form  method="POST" action="ExpenseServlet">
  支出ID<input type="text" name="expenseId"><br>
  金額<input type="text" name="kingaku"><br>
  カテゴリー<input type="text" name="categoryId"><br>
  支出名<input type="text" name="expenseName"><br>
  登録<input type="radio" name="choice" value="touroku" checked>
  変更<input type="radio" name="choice" value="henkou">
  削除<input type="radio" name="choice" value="sakujo">
  <input type="submit" value="実行">
</form>
<table border="1">
<tr>
<td>日曜日</td>
<td>月曜日</td>
<td>火曜日</td>
<td>水曜日</td>
<td>木曜日</td>
<td>金曜日</td>
<td>土曜日</td>
</tr>
<%boolean shouldWrite = false;%>
<%int day = 1;%>
<tr>
<%for(int i = 0; i < 7; i++){ %>
<td>
  <%if(shouldWrite){%>
    <form method="POST" action="ExpenseServlet">
    <input type="hidden" name="selectDay" value=<%=day %>>
    <input type="submit" value=<%=day %>>
    </form>
    <%day++; %>
  <%}%>
  <%if(i == bean.getStartDayOfTheWeek()){ %>
  <form method="POST" action="ExpenseServlet">
  <input type="hidden" name="selectDay" value=<%=day %>>
  <input type="submit" value=<%=day %>>
  </form>
    <%day++; %>
    <%shouldWrite = true; %>
  <%} %>
</td>
<%} %>
</tr>
<%boolean endCalender = false; %>
<%while(true){ %>
<tr>
  <%for(int i = 0; i < 7; i++){ %>
<td>
    <%if(shouldWrite){ %>
      <form method="POST" action="ExpenseServlet">
      <input type="hidden" name="selectDay" value=<%=day %>>
      <input type="submit" value=<%=day %>>
      </form>
      <%day++; %>
    <%} %>
    <%if(day > bean.getEndDay()){ %>
      <%shouldWrite = false; %>
      <%endCalender = true; %>
    <%} %>
</td>
  <%} %>
</tr>
  <%if(endCalender) break;%>
<%} %>
</table>
</body>
</html>