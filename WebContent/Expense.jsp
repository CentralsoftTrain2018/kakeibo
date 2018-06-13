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
      <input type="hidden" name="nengetu" value="2018/05"> <input
        type="submit" value="バンジー">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿" disabled="disabled">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析">
    </form>
  </div>
  <%
    if ( bean.getMessage() != null )
    {
  %>
  <%= bean.getMessage() %><br>
  <%
    }
  %>
  入力してね
  <br>

  <%=bean.getDate().get( Calendar.YEAR )%>年
  <%=bean.getDate().get( Calendar.MONTH )%>月
  <br>
  <form method="POST" action="ExpenseServlet">
  支出ID<input type="text" name="expenseId"><br>
    金額<input type="text" name="kingaku"><br>
    カテゴリー<input type="text" name="categoryId"><br>
    支出名<input type="text" name="expenseName"><br>
    登録<input type="radio" name="choice" value="touroku" checked>
    変更<input type="radio" name="choice" value="henkou">
    削除<input type="radio" name="choice" value="sakujo">
    <input type="submit" value="実行">
  </form>
  <br>
  <%=bean.getDate().get( Calendar.YEAR )%>年
  <%=bean.getDate().get( Calendar.MONTH )%>月
  <br>
  <form method="POST" action="ExpenseServlet">
  <select name="year">
      <option value="2018">2018</option>
      <option value="2017">2017</option>
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


    <%
      boolean shouldWrite = false;
    %>
    <%
      int day = 1;
    %>
    <tr>
      <%
        for ( int i = 0; i < 7; i++ )
        {
      %>
      <td>
        <%
          if ( shouldWrite )
            {
        %> <%=bean.getExpenses()[day - 1]%>
        <form method="POST" action="ExpenseServlet">
          <input type="hidden" name="selectDay" value=<%=day%>> <input
            type="submit" value=<%=day%>>
        </form> <%
   day++;
 %> <%
   }
 %> <%
   if ( i == bean.getStartDayOfTheWeek() )
     {
 %> <%=bean.getExpenses()[day - 1]%>
        <form method="POST" action="ExpenseServlet">
          <input type="hidden" name="selectDay" value=<%=day%>> <input
            type="submit" value=<%=day%>>
        </form> <%
   day++;
 %> <%
   shouldWrite = true;
 %> <%
   }
 %>
      </td>
      <%
        }
      %>
    </tr>
    <%
      boolean endCalender = false;
    %>
    <%
      while ( true )
      {
    %>
    <tr>
      <%
        for ( int i = 0; i < 7; i++ )
          {
      %>
      <td>
        <%
          if ( shouldWrite )
              {
        %> <%=bean.getExpenses()[day - 1]%>
        <form method="POST" action="ExpenseServlet">
          <input type="hidden" name="selectDay" value=<%=day%>> <input
            type="submit" value=<%=day%>>
        </form> <%
   day++;
 %> <%
   }
 %> <%
   if ( day > bean.getEndDay() )
       {
 %> <%
   shouldWrite = false;
 %>
        <%
          endCalender = true;
        %> <%
   }
 %>
      </td>
      <%
        }
      %>
    </tr>
    <%
      if ( endCalender )
          break;
    %>
    <%
      }
    %>
  </table>

  <table border="1">

    <tr>
      <th>項目</th>
      <th>買ったもの</th>
      <th>金額</th>
      <th colspan=2 align="center">操作</th>
    </tr>

    <form method="POST" action="ExpenseServlet">
      <%
        for ( int i = 0; i < 10; i++ )
        {
      %>
      <tr>
        <td><select name="categoryId">
            <!-- ここをいじる -->
            <option value="1">カテゴリー名</option>
            <option value="2">カテゴリー名</option>
        </select></td>

        <td><input type="text" name="expenseName"></td>

        <td><input type="text" name="kingaku"></td>

        <td><input type="submit" value="変更"></td>

        <td><input type="submit" value="削除"></td>

      </tr>
      <%
        }
      %>
    </form>
    <tr>
      <form method="POST" action="ExpenseServlet">
        <td><select name=categoryId>
            <option value="1">カテゴリー名</option>
            <option value="2">カテゴリー名</option>
            <option value="3">カテゴリー名</option>
            <option value="4">カテゴリー名</option>

        </select></td>

        <td><input type="text" name="expenseName"></td>

        <td><input type="text" name="kingaku"></td>

        <td colspan=2 align="center"><input type="submit" value="登録">
          <input type="hidden" name="choice" value="touroku"></td>
      </form>
    </tr>
  </table>
</body>
</html>