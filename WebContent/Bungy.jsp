<%@ page language="java" import="java.util.*"
  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.BungyBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<title>TOP</title>
</head>
<body>

  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetu"> <input type="submit"
        value="バンジー" disabled="disabled">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析">
    </form>

    <form method="POST" action="ConanServlet">
      <input type="hidden" name="nengetsu" value=<%=bean.getNengetsu()%>>
      <input type="submit" value="コナン">
    </form>
    <form method="POST" action="JihakuServlet">
      <input type="hidden" name="nengetsu" value=<%=bean.getNengetsu()%>>
      <input type="submit" value="自白">
    </form>
  </div>

  <h1>
    Bungy
    <%=bean.getNengetsu()%></h1>
  <div class="pattern1"
    style="width: 800px; height: 742px; overflow: hidden;
    margin-bottom: 0px; padding-bottom: 0px; position: relative;">

    <img src="image/kawa.png" alt="" class="base"
      style="position: absolute; width: 800px; height: 742px; margin-all: 0px; padding-all: 0px;">
    <img src="image/kaidan.png" alt="" class="base"
      style="position: absolute; left: 10px; bottom: 0px; margin-bottom: 0px; padding-bottom: 0px;">
    <img src="image/hanninkaidan.png" alt="" class="hannin"
      style="position: absolute;
      width: 100px;
      height: 100px;
      transform: scale(<%=bean.getHanninMuki()%>,1);
      left: <%=bean.getHanninYoko()%>%;
      bottom:<%=bean.getHanninIchi()%>%;">
    <img src="image/himo.png" alt="" class="himo"
      style="position: absolute;
      width: 50px;
      height:<%=bean.getJumperDispPosition() + 7%>%;
      right: 35%;
      top:25%;">
    <%
      if ( bean.isMonthfinflg() && bean.isGameoverflg() )
      {
    %>
    <img src="image/obore.png" alt="" class="jumper"
      style="position: absolute; width: 100px; height: 100px; right: 30%; top: 80%;">
    <%
      } else
      {
    %>

    <img src="image/jump.png" alt="" class="jumper"
      style="position: absolute;
      width: 100px;
      height: 100px;
      right: 30%;
      top:<%=bean.getJumperDispPosition() + 30%>%;">
    <%
      }
    %>

    <form method="POST" action="<%=bean.getAdovicePage()%>">

      <%
        if ( bean.isMonthfinflg() )
        {
      %>
      <input type="image" src="<%=bean.getButtonImage()%>"
        style="position: absolute; right: 30%; top: 30%;">
      <%
        }
      %>
    </form>
  </div>
  <form method="POST" action="BungyServlet">
    年月<input type="text" name="nengetsu"><br> <input
      type="submit" value="Bungy"><br>
  </form>

  </form>
  </div>

</body>
</html>