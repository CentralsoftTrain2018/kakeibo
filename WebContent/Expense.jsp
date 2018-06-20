<%@page import="vo.CategoryVo"%>
<%@page import="vo.ExpenseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar"%>
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
      <input type="hidden" name="nengetsu" value=""> <input
        type="submit" value="バンジー">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿" disabled="disabled">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析">
    </form>
        <form method="POST" action="SetteiServlet">
      <input type="submit" value="設定">
    </form>
  </div>
  <div style="width: 1200px; height: 500px; margin-left:auto; margin-right:auto; position: relative; text-align:center; ">
  <div style="width: 300px; height: 50px; left:15%;  position: absolute;">
<%if(! bean.getMessage().equals("")){%>
<%= bean.getMessage() %><br>
<%} %>
入力してね<br>

<%=bean.getDate().get(Calendar.YEAR) %>年
<%=bean.getDate().get(Calendar.MONTH)+1 %>月

<form method="POST" action="ExpenseServlet">
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
</div>
<table border="1" align="left" cellpadding="5" style="position:absolute; width:400px; height:400px; left:5%; top:20%;">
<tr>
<th style = "color:red">日曜日</th>
<th>月曜日</th>
<th>火曜日</th>
<th>水曜日</th>
<th>木曜日</th>
<th>金曜日</th>
<th style = "color:blue">土曜日</th>
</tr>
<%boolean shouldWrite = false;%>
<%int day = 1;%>
<tr>
<%for(int i = 0; i < 7; i++){ %>
<td<%if(i == 0){%> bgcolor = rgba(255,0,0,0.2) <%} %>
    <%if(i == 6){%> bgcolor = rgba(0,0,255,0.2) <%} %>>
  <%if(shouldWrite){%>

    <form method="POST" action="ExpenseServlet">
    <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
    <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
    <input type="hidden" name="selectDay" value=<%=day %>>
    <input type="submit" value=<%=day %> style = "WIDTH: 60px; HEIGHT: 40px; font-size: 1.2em;" >
    </form>
    <%=bean.getExpenses()[day - 1] %>
    <%day++; %>
  <%}%>
  <%if(i == bean.getStartDayOfTheWeek()){ %>

    <form method="POST" action="ExpenseServlet">
    <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
    <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
    <input type="hidden" name="selectDay" value=<%=day %>>
    <input type="submit" value=<%=day %> style = "WIDTH: 60px; HEIGHT: 40px;  font-size: 1.2em;">
    </form>
    <%=bean.getExpenses()[day - 1] %>
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
<td <%if(i == 0){%> bgcolor = rgba(255,0,0,0.2) <%} %>
    <%if(i == 6){%> bgcolor = rgba(0,0,255,0.2) <%} %>>
    <%if(shouldWrite){ %>
      <form method="POST" action="ExpenseServlet">
      <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
      <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
      <input type="hidden" name="selectDay" value=<%=day %>>
      <input type="submit" value=<%=day %> style = "WIDTH: 60px; HEIGHT: 40px; font-size: 1.2em;">
      </form>
      <%=bean.getExpenses()[day - 1] %>
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

<!-- 変更ボタン押す前 -->

<% if(!bean.isChange()) {%>


<table border = "1" cellpadding="3" align = "center" style="position:absolute; width:500px; right:5%; top:20%;">
<tr>
<th bgcolor = lime colspan = 5>
<%=bean.getDate().get(Calendar.MONTH)+1 %>月
<%=bean.getSelectDay() %>日
</th>
</tr>

<tr>
<th> 項目 </th>
<th> 買ったもの </th>
<th> 金額 </th>
<th colspan = 2 align = "center"> 操作 </th>
</tr>



<% for( int i=0; i<bean.getExpenseOfDay().size(); i++ ){ %>

<tr>
  <form method="POST" action="ExpenseServlet">
  <td width = "67">

<% for( CategoryVo cv: bean.getCategoryList()){%>
      <%if(bean.getExpenseOfDay().get(i).getCategoryId() == cv.getCategoryid()) {%>
      <%=cv.getCategoryname() %>
      <%} %>
<% }%>

  </td>

  <td width = "153">
  <%=bean.getExpenseOfDay().get(i).getExpenseName() %>
  </td>

  <td width = "153">
  <%=bean.getExpenseOfDay().get(i).getExpenseKingaku() %>
  </td>

    <td>
  <input type="submit" name = "choice" value="削除" onclick = "return confirm('本当に削除してよろしいですか？')">
  <input type="hidden" name="expenseId" value=<%=bean.getExpenseOfDay().get(i).getExpenseId() %>>
  <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
  <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
  <input type="hidden" name="selectDay" value=<%=bean.getSelectDay() %>>
  </td>

<%if(i == 0){ %>
  <td rowspan = <%=bean.getExpenseOfDay().size() %>>
  <input type="submit" name = "choice" value="変更待機">
  <input type="hidden" name="isChange" value = "true">
  </td>
<%} %>
  </form>
<%} %>

<tr>
<form method="POST" action="ExpenseServlet">
  <td>
    <select name = "categoryId">
<% for( CategoryVo cv: bean.getCategoryList()){%>
      <option value=<%=cv.getCategoryid() %>> <%=cv.getCategoryname() %> </option>
<% }%>
    </select>

  </td>

  <td>
  <input type="text" name="expenseName">
  </td>

  <td>
  <input type="text" name="kingaku">
  </td>

    <td colspan = 2 align = "center">
  <input type="submit" name="choice" value="登録">
  <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
  <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
  <input type="hidden" name="selectDay" value=<%=bean.getSelectDay() %>>

  </td>
  </form>



  </tr>
  </table>

<%} %>


<!-- 変更ボタンを押した後  -->

<% if(bean.isChange()) {%>

<table border = "1" cellpadding="3">
<tr>
<th bgcolor = lime colspan = 5 align = "right">
<%=bean.getDate().get(Calendar.MONTH)+1 %>月
<%=bean.getSelectDay() %>日
</th>
</tr>

<tr>
<th> 項目 </th>
<th> 買ったもの </th>
<th> 金額 </th>
<th colspan = 2 align = "center"> 操作 </th>
</tr>

<% for( ExpenseVo ev: bean.getExpenseOfDay()){ %>

<tr>
  <form name = "myform" method="POST" action="ExpenseServlet" onSubmit = "test2();">
  <td>
    <select name = "categoryId">
<% for( CategoryVo cv: bean.getCategoryList()){%>

      <option value=<%=cv.getCategoryid() %>
      <%if(ev.getCategoryId() == cv.getCategoryid()) {%>
      selected
      <%} %>

      ><%=cv.getCategoryname() %></option>

<% }%>
    </select>
  </td>

  <td>
  <input type="text" name="expenseName" value = <%=ev.getExpenseName() %>>
  <input type="hidden" name = "expenseId" value = <%=ev.getExpenseId()%>>
  </td>

  <td>
  <input type="text" name="kingaku"  value = <%=ev.getExpenseKingaku() %>>
  </td>


<%} %>

<td rowspan = <%=bean.getExpenseOfDay().size()%>>
  <input type="submit" name = "choice" id = "henkou" value="変更" >
  <div id = "data">
  <input type="hidden" id = "indata" name="data" value = "str" >
  </div>

  <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR) %>>
  <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
  <input type="hidden" name="selectDay" value=<%=bean.getSelectDay() %>>
  </td>

  <td rowspan = <%=bean.getExpenseOfDay().size()%>>
  <input type="submit" name = "choice" value="戻る">
  <input type="hidden" name="isChange" value = "false">
</td>
  </form>
  </tr>

</table>

</div>

<% }%>

<script type="text/javascript">


function test2(){

  var categoryIdList = document.getElementsByName("categoryId");
    var expenseIdList =  document.getElementsByName("expenseId");
    var expenseNameList =  document.getElementsByName("expenseName");
    var kingakuList =  document.getElementsByName("kingaku");

  var list = "";

  for( var i = 0; i<categoryIdList.length; i++ ){
      list = list + categoryIdList[i].value + "/";
  }

  for( var i = 0; i<expenseIdList.length; i++ ){
    list = list + expenseIdList[i].value + "/";
  }

  for( var i = 0; i<expenseNameList.length; i++ ){
    list = list + expenseNameList[i].value + "/";
  }

  for( var i = 0; i<kingakuList.length; i++ ){
    list = list + kingakuList[i].value + "/";
  }

  document.getElementById("indata").value = list;

}

</script>
</body>

</html>
