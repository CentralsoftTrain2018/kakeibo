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
<%bean.checkFlgs(); %>
  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu"> <input type="submit"
        value="バンジー" disabled="disabled">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析">
    </form>

    <form method="POST" action="ConanServlet">
      <input type="hidden" name="nengetsu" value="<%=bean.getNengetsu()%>">
      <input type="submit" value="コナン">
    </form>
    <form method="POST" action="JihakuServlet">
      <input type="hidden" name="nengetsu" value="<%=bean.getNengetsu()%>">
      <input type="submit" value="自白">
    </form>
  </div>

  <h1>
    Bungy
    <%=bean.getNengetsu()%></h1>
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
      style="position: absolute; width: 100px; height: 100px; right: 30%; top: 80%;">
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
      top:19%;">
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
  <form method="POST" action="BungyServlet">
    年月<select name="nengetsu">
      <option value="2018/01"> 2018/01 </option>
      <option value="2018/02"> 2018/02 </option>
      <option value="2018/03"> 2018/03 </option>
      <option value="2018/04"> 2018/04 </option>
      <option value="2018/05"> 2018/05 </option>
      <option value="2018/06"> 2018/06 </option>
      <option value="2018/07"> 2018/07 </option>
      <option value="2018/08"> 2018/08 </option>
      <option value="2018/09"> 2018/09 </option>
      <option value="2018/10"> 2018/10 </option>
    </select>
    <input type="submit" value="Bungy"><br>
  </form>



</body>
</html>