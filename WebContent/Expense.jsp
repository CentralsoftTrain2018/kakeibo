<%@page import="vo.CategoryVo"%>
<%@page import="vo.ExpenseVo"%>
<%@ page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" import="java.util.Calendar"%>
<jsp:useBean id="bean" class="bean.ExpenseBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css" href="css/Expense.css">
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
  <div id="kakeibo">
    <div id="nav">
      <% if (!bean.getMessage().equals("")) { %>
      <%=bean.getMessage()%><br>
      <%} %>
        入力してね<br>
      <%=bean.getDate().get(Calendar.YEAR)%>年
      <%=bean.getDate().get(Calendar.MONTH) + 1%>月<br>

  <form method="POST" action="ExpenseServlet" id="nengetsu">
    <select name="year" id = "selectYear" onChange = "checkRegistMonth()" style="float:left;">
      <% for(int count = bean.getRegistYear(); count <= LocalDate.now().getYear(); count++ ){ %>
      <option value=<%=count %>
          <% if(count == bean.getDate().get(Calendar.YEAR)){ %> selected
            <%} %>><%=count %></option>
          <%} %>
    </select>
    <input type="hidden" id="nowYear" value=<%=bean.getDate().get(Calendar.YEAR) %>>
    <input type="hidden" id="registYear" value=<%=bean.getRegistYear() %>>
    <input type="hidden" id="nowMonth" value=<%=bean.getDate().get(Calendar.MONTH)+1 %>>
    <input type="hidden" id="registMonth" value=<%=bean.getRegistMonth() %>>

    <div id = "selectMonth">
      <select name="month" style="margin:0px auto; margin-left: 10px; float:left;">
      <%if(bean.getDate().get(Calendar.YEAR) == LocalDate.now().getYear()) {%>
          <% for (int i = 1; i <= LocalDate.now().getMonthValue(); i++) { %>
          <option value=<%=i%>

            <%if (i == bean.getDate().get(Calendar.MONTH) + 1) {%>
              selected
            <%}%>>

            <%=i%></option>
            <%} %>
            <%} else{ %>
          <% for (int i = 1; i <= 12; i++) { %>
          <option value=<%=i%>
            <%if (i == bean.getDate().get(Calendar.MONTH)+1) {%> selected
            <%}%>>
            <%=i%></option>
            <%} %>
            <%} %>

      </select>
     </div>
       <input type="submit" value="年月変更" style="margin:0px auto; margin-left: 10px; float:left;">
    </form>
    </div>
    <table id="calender">
      <tr>
        <th style="color: red">日曜日</th>
        <th>月曜日</th>
        <th>火曜日</th>
        <th>水曜日</th>
        <th>木曜日</th>
        <th>金曜日</th>
        <th style="color: blue">土曜日</th>
      </tr>
      <% boolean shouldWrite = false; %>
      <% int day = 1; %>
      <tr>
        <% for (int i = 0; i < 7; i++) { %>
        <td <% if (i == 0) { %>
          bgcolor=#F6CED8 <%}%>
          <%if (i == 6) { %>
            bgcolor=#CEE3F6 <%}%>>
            <% if (shouldWrite) { %>

          <form method="POST" action="ExpenseServlet">
            <input type="hidden" name="year"
              value=<%=bean.getDate().get(Calendar.YEAR)%>>
            <input type="hidden" name="month"
                value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
            <input type="hidden" name="selectDay" value=<%=day%>>
            <input type="submit" class="day" value=<%=day%>>
          </form> <%=bean.getExpenses()[day - 1]%>
          <% day++; %>
          <% } %>
          <% if (i == bean.getStartDayOfTheWeek()) { %>

          <form method="POST" action="ExpenseServlet">
            <input type="hidden" name="year"
              value=<%=bean.getDate().get(Calendar.YEAR)%>>
            <input type="hidden" name="month"
              value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
            <input type="hidden" name="selectDay" value=<%=day%>>
            <input type="submit" class="day" value=<%=day%>>
          </form>
          <%=bean.getExpenses()[day - 1]%>
          <% day++; %>
          <% shouldWrite = true; %>
          <% } %>
        </td>
        <% } %>
      </tr>
      <% boolean endCalender = false; %>
      <% while (true) { %>
      <tr>
        <% for (int i = 0; i < 7; i++) { %>
        <td <%if (i == 0) {%>
          bgcolor=#F6CED8 <%}%>
          <%if (i == 6) {%>
            bgcolor=#CEE3F6 <%}%>>
            <% if (shouldWrite) { %>
          <form method="POST" action="ExpenseServlet">
            <input type="hidden" name="year"
              value=<%=bean.getDate().get(Calendar.YEAR)%>>
            <input type="hidden" name="month"
              value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
            <input type="hidden" name="selectDay" value=<%=day%>>
            <input type="submit" class="day" value=<%=day%>>
          </form>
          <%=bean.getExpenses()[day - 1]%>
          <% day++; %>
          <% } %>
          <% if (day > bean.getEndDay()) { %>
          <% shouldWrite = false; %>
          <% endCalender = true; %>
          <% } %>
        </td>
        <% } %>
      </tr>
      <% if (endCalender) break; } %>
    </table>

    <!-- 変更ボタン押す前 -->

    <% if (!bean.isChange()) { %>

    <table class="list">
      <tr>
        <th bgcolor=lime colspan=5><%=bean.getDate().get(Calendar.MONTH) + 1%>月
          <%=bean.getSelectDay()%>日</th>
      </tr>
      <tr>
        <th>項目</th>
        <th>買ったもの</th>
        <th>金額</th>
        <th colspan=2 align="center">操作</th>
      </tr>

      <% for (int i = 0; i < bean.getExpenseOfDay().size(); i++) { %>

      <form method="POST" action="ExpenseServlet">
        <tr>
          <td width="67">
            <% for (CategoryVo cv : bean.getCategoryList()) { %>
              <% if (bean.getExpenseOfDay().get(i).getCategoryId() == cv.getCategoryid()) { %>
              <%=cv.getCategoryname()%>
              <% } %>
            <% } %>
          </td>

          <td width="153"><%=bean.getExpenseOfDay().get(i).getExpenseName()%>
          </td>

          <td width="153"><%=bean.getExpenseOfDay().get(i).getExpenseKingaku()%>
          </td>

          <td>
            <input type="submit" name="choice" value="削除"
              onclick="return confirm('本当に削除してよろしいですか？')">
            <input type="hidden" name="expenseId"
              value=<%=bean.getExpenseOfDay().get(i).getExpenseId()%>>
            <input type="hidden" name="year"
              value=<%=bean.getDate().get(Calendar.YEAR)%>>
            <input type="hidden" name="month"
              value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
            <input type="hidden" name="selectDay" value=<%=bean.getSelectDay()%>>
          </td>

          <% if (i == 0) { %>
          <td rowspan=<%=bean.getExpenseOfDay().size()%>><input
            type="submit" name="choice" value="変更待機"><input
            type="hidden" name="isChange" value="true"></td>
          <% } %>
        </tr>
      </form>
      <% } %>

      <form method="POST" action="ExpenseServlet" onSubmit="return preventDoubleClick()">
        <tr>
          <td><select name="categoryId">
              <% for (CategoryVo cv : bean.getCategoryList()) { %>
              <option value=<%=cv.getCategoryid()%>>
                <%=cv.getCategoryname()%>
              </option>
              <% } %>
          </select></td>

          <td><input type="text" name="expenseName" required></td>
          <td><input type="number" name="kingaku" required></td>
          <td colspan=2 align="center">
          <input type="submit" name="choice" value="登録">
          <input type="hidden" name="year" value=<%=bean.getDate().get(Calendar.YEAR)%>>
          <input type="hidden" name="month" value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
          <input type="hidden" name="selectDay" value=<%=bean.getSelectDay()%>>
          </td>
        </tr>
      </form>
    </table>

    <%}%>

    <!-- 変更ボタンを押した後  -->

    <% if (bean.isChange()) { %>
    <form name="myform" method="POST" action="ExpenseServlet"
      onSubmit="joinData();">
      <table class="list">
        <tr>
          <th bgcolor=lime colspan=5>
          <%=bean.getDate().get(Calendar.MONTH) + 1%>月
          <%=bean.getSelectDay()%>日</th>
        </tr>

        <tr>
          <th>項目</th>
          <th>買ったもの</th>
          <th>金額</th>
          <th colspan=2 align="center">操作</th>
        </tr>

        <% for (ExpenseVo ev : bean.getExpenseOfDay()) { %>

        <tr>
          <td>
          <select name="categoryId">
              <% for (CategoryVo cv : bean.getCategoryList()) { %>

          <option value=<%=cv.getCategoryid()%>
              <%if (ev.getCategoryId() == cv.getCategoryid()) {%> selected
              <%}%>><%=cv.getCategoryname()%></option>

              <% } %>
          </select>
          </td>

          <td>
          <input type="text" name="expenseName" value=<%=ev.getExpenseName()%> required>
          <input type="hidden" name="expenseId" value=<%=ev.getExpenseId()%> required>
          </td>

          <td>
          <input type="number" name="kingaku"
            value=<%=ev.getExpenseKingaku()%> required>
          </td>

          <% } %>

          <td rowspan=<%=bean.getExpenseOfDay().size()%>><input
            type="submit" name="choice" id="henkou" value="変更">
            <div id="data">
              <input type="hidden" id="indata" name="data" value="str">
            </div>
            <input type="hidden" name="year"
            value=<%=bean.getDate().get(Calendar.YEAR)%>>
            <input type="hidden" name="month"
            value=<%=bean.getDate().get(Calendar.MONTH) + 1%>>
            <input type="hidden" name="selectDay" value=<%=bean.getSelectDay()%>>
          </td>

          <td rowspan=<%=bean.getExpenseOfDay().size()%>>
            <input type="submit" name="choice" value="戻る">
            <input type="hidden" name="isChange" value="false">
          </td>
        </tr>
      </table>
    </form>

  </div>

  <%}%>

  <script type="text/javascript">
    function joinData() {

      var categoryIdList = document.getElementsByName("categoryId");
      var expenseIdList = document.getElementsByName("expenseId");
      var expenseNameList = document.getElementsByName("expenseName");
      var kingakuList = document.getElementsByName("kingaku");

      var list = "";

      for (var i = 0; i < categoryIdList.length; i++) {
        list = list + categoryIdList[i].value + "/";
      }

      for (var i = 0; i < expenseIdList.length; i++) {
        list = list + expenseIdList[i].value + "/";
      }

      for (var i = 0; i < expenseNameList.length; i++) {
        list = list + expenseNameList[i].value + "/";
      }

      for (var i = 0; i < kingakuList.length; i++) {
        list = list + kingakuList[i].value + "/";
      }

      document.getElementById("indata").value = list;
    }


    function checkRegistMonth(){

      var sel = document.getElementById("selectMonth");
      var year = document.getElementById("selectYear").value;
      var registyear = document.getElementById("registYear").value;
      var month = document.getElementById("nowMonth").value;
      var registmonth = document.getElementById("registMonth").value;

      var dt = new Date();

      var text = null;

      text = '<select name="month" style="margin:0px auto; margin-left: 10px; float:left;">';

      if(year == registyear){
          for (var i = registmonth; i <= 12; i++) {
                text = text + "<option value=" + i;
                  if (i == month) {
                      text = text + " selected";
                  }
                  text = text + ">" + i + "</option>";
                }
        }
      else if(year == dt.getFullYear()) {
        for (var i = 1; i <= dt.getMonth()+1; i++) {
              text = text + "<option value=" + i;
                if (i == month) {
                    text = text + " selected";
                }
                text = text + ">" + i + "</option>";
              }
      }else {
        for (var i = 1; i <= 12; i++) {
              text = text + "<option value=" + i;
                if (i == month) {
                    text = text + " selected";
                }
                text = text + ">" + i + "</option>";
              }
      }

      text = text + "</select>";

      sel.innerHTML = text;
      }

    var set=0;
    function preventDoubleClick() {
    if(set==0){ set=1; } else {
    alert("只今処理中です。\nそのままお待ちください。");
    return false; }}

  </script>

</body>
</html>