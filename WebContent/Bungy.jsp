<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="bean" class="bean.BungyBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Bungy</title>
</head>
<body background="image/kawa.png">
<h1>Bungy</h1>
 <div class="pattern1"

  style="width: 800px;
  height: 742px;
  overflow: hidden;
  background-image: url(image/kawa.png);
  margin-bottom:0px;padding-bottom:0px;
  position: relative;"
  >
  <img src="image/kaidan.png" alt="" class ="base"
  style="
  position: absolute;
  left: 10px;
  bottom:0px;
  margin-bottom:0px;padding-bottom:0px;"
  >
  <img src="image/hanninkaidan.png" alt="" class="hannin"
  style=
  "position: absolute;
  width: 100px;
  height: 100px;
  transform: scale(<%=bean.getHanninMuki() %>,1);
  left: <%=bean.getHanninYoko() %>%;
  bottom:
       <%=bean.getHanninIchi() %>%;"
  >
  <img src="image/himo.png" alt="" class="himo"
  style=
  "position: absolute;
  width: 50px;
  height:<%=bean.getJumperDispPosition()+7%>%;
  right: 35%;
  top:25%;"
  >
  <img src="image/jump.png" alt="" class="jumper"
  style=
  "position: absolute;
  width: 100px;
  height: 100px;
  right: 30%;

  top:<%=bean.getJumperDispPosition()+30%>%;">

<form method="POST" action="<%=bean.getAdovicePage()%>">

  <%if(bean.isMonthfinflg()){ %>
   <input type="image" src="<%=bean.getButtonImage() %>"
   style="
   position: absolute;
   right:30%;
   top:30%;
   ">
   <%} %>

</form>

 </div>

<form  method="POST" action="BungyServlet">
  何年何月<input type="text" name="nengetu"><br>
  </form>
</body>
</html>