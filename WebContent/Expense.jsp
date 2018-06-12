<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.ExpenseBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<title>家計簿</title>
</head>
<body>
  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetu" value="2018/05"> <input
        type="submit" value="バンジー">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿" disabled="disabled">
    </form>
    <form method="POST" action="Bunseki.jsp">
      <input type="submit" value="分析">
    </form>
  </div>
<%if(bean.getMessage() != null){%>
<%= bean.getMessage() %><br>
<%} %>
入力してね<br>

<table border="1" align="left">
<tr>
<th>日曜日</th>
<th>月曜日</th>
<th>火曜日</th>
<th>水曜日</th>
<th>木曜日</th>
<th>金曜日</th>
<th>土曜日</th>
</tr>


<%boolean shouldWrite = false;%>
<%int day = 1;%>
<tr>
<%for(int i = 0; i < 7; i++){ %>
<td>
  <%if(shouldWrite){%>
    <%=bean.getExpenses()[day - 1] %>
    <form method="POST" action="ExpenseServlet">
    <input type="hidden" name="selectDay" value=<%=day %>>
    <input type="submit" value=<%=day %>>
    </form>
    <%day++; %>
  <%}%>
  <%if(i == bean.getStartDayOfTheWeek()){ %>
    <%=bean.getExpenses()[day - 1] %>
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
      <%=bean.getExpenses()[day - 1] %>
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

<table border = "1" >

<tr>
<th> 項目 </th>
<th> 買ったもの </th>
<th> 金額 </th>
<th colspan = 2 align = "center"> 操作 </th>
</tr>

<form method="POST" action="ExpenseServlet">
<%for(int i=0; i<10; i++){ %>
<tr>

  <td>
    <select name = "categoryId">
    <option value = "1"> カテゴリー名 </option>
    <option value = "2"> カテゴリー名 </option>
    <option value = "3"> カテゴリー名 </option>
    <option value = "4"> カテゴリー名 </option>
    </select>
  </td>

  <td>
    <input type="text" name="expenseName">
  </td>

  <td>
  <input type="text" name="kingaku">
  </td>

  <td>
  <input type="submit" value="変更">
  </td>

    <td>
  <input type="submit" value="削除">
  </td>

  </tr>
  <%} %>
</form>
<tr>
  <form method="POST" action="ExpenseServlet">
  <td>
    <select name = categoryId>
    <option value = "1"> カテゴリー名 </option>
    <option value = "2"> カテゴリー名 </option>
    <option value = "3"> カテゴリー名 </option>
    <option value = "4"> カテゴリー名 </option>
    </select>
  </td>

  <td>
  <input type="text" name="expenseName">
  </td>

  <td>
  <input type="text" name="kingaku">
  </td>

  <td colspan = 2 align = "center">

  <input type="submit" value="登録"  >
  <input type="hidden" name="choice" value="touroku">
  </form>
  </td>
  </tr>
</table>
</body>
</html>