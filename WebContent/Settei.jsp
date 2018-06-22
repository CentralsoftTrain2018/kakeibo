<%@page import="vo.SetteiVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="bean" class="bean.SetteiBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Settei.css">
<script src='Settei.js'></script>
<title>設定</title>
</head>
<body
<%if(bean.getDispName()=="settei"){ %>
  onload="SetteiHenkouGamen()"
<%} %>
>
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

    <button class="button1" type="submit" name="processType" value="update" onClick="YuzaHenkouGamen()">IDパス</button><br>

    <button class="button1" type="submit" name="processType" value="update" onClick="SetteiHenkouGamen()">設定</button><br>

    <button class="button1" type="submit" name="processType" value="update" onClick="HelpGamen()">ヘルプ</button>

   </div>

  <div class="hanni" id="help">
  <h2>画面説明</h2><p>バンジー画面</p>
    <ul><li>日付が進むと男の子が下に落ちていくよ！</li>
    <li>犯人は支出額が増えるごとに上へ上がっていくよ！</li>
    <li>目標額より支出額が上回ったらゲームオーバーだよ！クリアを目指してがんばろう！</li></ul>
    <p>コナン画面</p><ul>
    <li>目標を達成するとコナン君からのアドバイスがもらえるよ！</li></ul>
    <p>自白画面</p>
    <ul><li>目標を超えてしまうと犯人がどうして犯行に及んだかを聞くことができるよ！</li>
    <li>次からの参考にしよう！</li></ul>
    <p>家計簿画面</p>
    <ul><li>月ごとの支出一覧が見れるよ！</li>
    <li>ここで支出の登録・編集・削除ができるんだ！</li></ul>
    <p>分析画面</p>
    <ul><li>月ごとに支出の割合が見れるよ！</li>
    <li>円グラフがかっこいいね！</li></ul>
  </div>
  <%if(bean.getDispName()=="settei"){%>
  <div class="hanni2" id="settei" style="display:block;">
  <%} else {%>
  <div class="hanni2" id="settei" style="display:none;">
  <%} %>

    <form method="POST" action="SetteiServlet" id="henkouform">
    <input type="hidden" name="dispName" value="settei">
    収入<input type="number" name="newIncome" min="0" value="" placeholder=<%=bean.getSyunyuu()%> required>
    <input type="submit" value="収入変更" name="">
    <input type="hidden" value="updateSyunyuu" name="choice">
    </form><br>
    目標:<%=bean.getMokuhyougoukei()%><br>

    <form method="POST" action="SetteiServlet">
    <table ><tr>
    <td><input type="text" name="newCategoryName" value="" placeholder="追加する項目名" required></td>
    <td><input type="number" name="newMokuhyoukingaku" min="0" value="" placeholder="目標金額" required></td>
    <td><select name="colorName">
       <option value="red">赤</option>
       <option value="bulue">青</option>
       <option value="yellow">黄色</option>
       <option value="green">緑</option>
       </select>
    </td>
    <td>
    <input type="submit" value="項目追加" name="">
    <input type="hidden" name="dispName" value="settei">
    <input type="hidden" value="addCategory" name="choice">
    </td>
    </tr></table>
    </form>

   <ul class="subsettei">
    <li class="button2"><button  type="submit" name="processType" value="update" onClick="subKoumokusakujo()" style="width:100%; padding: 15px 0; margin:0;">項目削除</button></li>
    <li class="button2"><button  type="submit" name="processType" value="update" onClick="subMokuhyouHenkou()" style="width:100%; padding: 15px 0; margin:0;">目標金額変更</button></li>
    <li class="button2"><button  type="submit" name="processType" value="update" onClick="subKoumokuMeiHennkou()" style="width:100%; padding: 15px 0; margin:0;">項目名変更</button></li>
   </ul>

   <div class="subcontana">
    <div id="deleteKoumoku">
    <input type="hidden" name="dispName" value="settei">
    <table border="1"  id="koumokulist" class="subtable">
    <tr>
    <td>項目名</td>
    <td>目標金額</td>
    <td></td>
    </tr>
    <%for(SetteiVo sv:bean.getCategoryMokuhyouList()){ %>
       <tr>
       <form method="POST" action="SetteiServlet">
       <input type="hidden" name="dispName" value="settei">
       <td><%=sv.getCategoryName()%></td>
       <td><%=sv.getMokuhyouKingaku()%></td>
       <td>
       <input type="submit" name="choice" value="deleteCategory">
       <input type="hidden" name="oldCategoryName" value=<%=sv.getCategoryName()%>>
       </td>
       </form>
       </tr>
    <%} %>
    </table>
    </div>

    <div id="updateMokuhyou">
    <table border="1"  id="koumokulist" class="subtable">
    <tr>
    <td>項目名</td>
    <td>目標金額</td>
    <td></td>
    </tr>
    <%for(SetteiVo sv:bean.getCategoryMokuhyouList()){ %>
       <tr>
       <form method="POST" action="SetteiServlet">
       <input type="hidden" name="dispName" value="settei">
       <td><%=sv.getCategoryName()%></td>
       <td><input type="number" required name="newMokuhyoukingaku" min="0" value=<%=sv.getMokuhyouKingaku()%>></td>
       <td>
       <input type="submit" name="choice" value="updateMokuhyou">
       <input type="hidden" name="oldCategoryName" value=<%=sv.getCategoryName()%>>
       <input type="hidden" name="newCategoryName" value=<%=sv.getCategoryName()%>>
       </td>
       </form>
       </tr>
    <%} %>
    </table>
    </div>

    <div id="updateKoumoku">
    <table border="1"  id="koumokulist" class="subtable">
    <tr>
    <td>項目名</td>
    <td>目標金額</td>
    <td></td>
    </tr>
    <%for(SetteiVo sv:bean.getCategoryMokuhyouList()){ %>
       <tr>
       <form method="POST" action="SetteiServlet">
       <input type="hidden" name="dispName" value="settei">
       <td><input type="text" name="newCategoryName" value=<%=sv.getCategoryName()%> required></td>
       <td><%=sv.getMokuhyouKingaku()%></td>
       <td>
       <input type="submit" name="" value="項目変更">
       <input type="hidden" name="choice" value="updateCategory">
       <input type="hidden" name="oldCategoryName" value=<%=sv.getCategoryName()%>>
       </td>
       </form>
       </tr>
    <%} %>
    </table>
    </div>
    </div>
  </div>

　<%if(bean.getDispName()=="user"){%>
  <div class="hanni3" id="yuzaHenkou" style="display:block ;">
  <%} else {%>
  <div class="hanni3" id="yuzaHenkou" style="display:none ;">
  <%} %>
     <form method="POST" action="SetteiServlet">
     <input type="hidden" name="dispName" value="user">
    <ul><li><label>新しく設定するPASS</label><input type="password" name="newPassword"id="password" required></li>
    <li><label>PASSの確認</label><input type="password"name="oldPassword" id="passwordConfirm" required oninput="check(this)"></li>
    </ul>
    <input type="submit" value="変更">
    <input type="hidden" value="updatePassword" name="choice">
    </form>
  </div>
  </div>

  </body>
</html>