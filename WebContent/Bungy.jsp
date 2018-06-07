<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Bungy</title>
</head>
<body>
<h1>Bungy</h1>
 <div class="pattern1"
 style="width: 800px;
  height: 742px;
  overflow: hidden;
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
  <img src="image/hanninkaidan.png" alt="" class="hannin" id="hannin"
  style=
  "position: absolute;
  width: 100px;
  height: 100px;
  left: 0px;
  bottom:0%;"
  >
  <img src="image/jump.png" alt="" class="jumper" id="jumper"
  style=
  "position: absolute;
  width: 100px;
  height: 100px;
  right: 30%;
  top:0%;"
  >
 </div>

<form  method="POST" action="JihakuServlet">
  <input type="submit" value="殺される">
</form>
<form  method="POST" action="ConanServlet">
  <input type="submit" value="生き延びる">
</form>
</body>
</html>