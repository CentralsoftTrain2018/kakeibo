<%@ page language="java" import="java.util.*"
  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ page import="java.time.LocalDate"%>
<jsp:useBean id="bean" class="bean.BungyBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<title>TOP</title>
</head>
<body>
<%bean.checkFlgs(); %>
  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu">
      <input type="submit" value="バンジー" disabled="disabled">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析">
    </form>
    <form method="POST" action="SetteiServlet">
      <input type="hidden" name="choice" value="">
      <input type="submit" value="設定">
    </form>

  </div>

  <h1>
    Bungy
    <%=bean.getNengetsu()%>
    </h1>
    <div id="nengetsu">
    <form method="POST" action="BungyServlet">
        <select name="year" id = "selectYear" onChange = "checkRegistMonth()" style="float: left;">
          <% for(int count = bean.getRegistYear(); count <= LocalDate.now().getYear(); count++ ){ %>
          <option value=<%=count %>
            <% if(count == bean.getSelectedYear()){ %> selected
            <%} %>><%=count %></option>
          <%} %>
        </select>
        <input type="hidden" id="nowYear" value=<%=bean.getSelectedYear() %>>
        <input type="hidden" id="registYear" value=<%=bean.getRegistYear() %>>
        <input type="hidden" id="nowMonth" value=<%=bean.getSelectedMonth() %>>
        <input type="hidden" id="registMonth" value=<%=bean.getRegistMonth() %>>

     <div id = "selectMonth" style="margin-left: 10px; margin-right: 10px; float: left;">
      <select name="month">
     <%if(bean.getSelectedYear() == LocalDate.now().getYear()) {%>
          <% for (int i = 1; i <= LocalDate.now().getMonthValue(); i++) { %>
          <option value=<%=i%>
            <%if (i == bean.getSelectedMonth()) {%> selected
            <%}%>>
            <%=i%></option>
            <%} %>
            <%} else{ %>
          <% for (int i = 1; i <= 12; i++) { %>
          <option value=<%=i%>
            <%if (i == bean.getSelectedMonth()) {%> selected
            <%}%>>
            <%=i%></option>
            <%} %>
            <%} %>

      </select>
     </div>
     <input type="submit" value="年月変更">
      </form>
      </div>
  <div class="pattern1"
    style="width: 800px; height: 742px; overflow: hidden;
    margin-bottom: 0px; padding-bottom: 0px; position: relative; text-align:center; margin-left:auto; margin-right:auto;">

    <img src="image/bg_taki.jpg" alt="" class="base"
      style="position: absolute; width: 800px; height: 742px; margin-all: 0px; padding-all: 0px; left: 10px;">
    <img src="image/hanninkaidan.png" alt="" class="hannin"
      style="position: absolute;
      width: 100px;
      height: 100px;
      transform: scale(<%=bean.getHanninMuki()%>,1);
      left: <%=bean.getHanninYoko()%>%;
      bottom:<%=bean.getHanninIchi()%>%;">

    <%
      if ( bean.isMonthfinflg() && bean.isGameoverflg() )
      {
    %>
      <img src="image/obore.png" alt="" class="jumper"
      style="position: absolute; width: 100px; height: 100px; right: 30%; top: 83%;">
       <img src="image/kireta_himo.png" alt="" class="himo"
      style="position: absolute;
      width: 50px;
      right: 35%;
      top:19%;">
    <%
      } else
      {
    %>
      <img src="image/jump.png" alt="" class="jumper"
      style="position: absolute;
      width: 100px;
      height: 100px;
      right: 30%;
      top:<%=bean.getJumperDispPosition() + 24%>%;">
      <img src="image/himo.png" alt="" class="himo"
      style="position: absolute;
      width: 50px;
      height:<%=bean.getJumperDispPosition() + 7%>%;
      right: 35%;
      top: 19%;">
    <%
      }
    %>

    <form method="POST" action="<%=bean.getAdovicePage()%>">

      <%
        if ( bean.isMonthfinflg() )
        {
      %>
      <input type="hidden" name="nengetsu" value="<%=bean.getNengetsu()%>">
      <input type="image" src="<%=bean.getButtonImage()%>"
        style="position: absolute; right: 25%; top: 30%;">
      <%
        }
      %>
    </form>
  </div>

<script type="text/javascript">
      function checkRegistMonth(){

      var sel = document.getElementById("selectMonth");
      var year = document.getElementById("selectYear").value;
      var registyear = document.getElementById("registYear").value;
      var month = document.getElementById("nowMonth").value;
      var registmonth = document.getElementById("registMonth").value;

      var dt = new Date();

      var text = null;

      text = '<select name="month">';

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
      </script>

</body>
</html>