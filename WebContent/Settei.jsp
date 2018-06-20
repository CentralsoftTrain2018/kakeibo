<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Settei.css">
<script src='Settei.js'></script>
<title>設定</title>
</head>
<body>

  <div class="menu">
    <form method="POST" action="BungyServlet">
      <input type="hidden" name="nengetsu" value="">
      <input type="submit" value="バンジー">
    </form>
    <form method="POST" action="ExpenseServlet">
      <input type="submit" value="家計簿">
    </form>
    <form method="POST" action="BunsekiServlet">
      <input type="submit" value="分析" >
    </form>
  </div>

  <br>
 <div class="contena">
  <div class="settei">

    <button class="button1" type="submit" name="processType" value="update" onClick="YuzaHenkouGamen()">ID</button><br>

    <button class="button1" type="submit" name="processType" value="update" onClick="SetteiHenkouGamen()">設定</button><br>

    <button class="button1" type="submit" name="processType" value="update" onClick="HelpGamen()">ヘルプ</button>

   </div>

  <div class="hanni" id="hanni">

  </div>
  </div>

  </body>
</html>