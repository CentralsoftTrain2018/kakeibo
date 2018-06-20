//設定画面左側のIDボタンを押したとき呼ばれる。
function YuzaHenkouGamen() {

  document.getElementById('hanni').innerHTML =
  '<form method="POST" action="SetteiServlet">'+
    '<ul><li><label>新しく設定するPASS</label><input type="password" name="newPassword"id="password" required></li>'+
    '<li><label>PASSの確認</label><input type="password"name="passwordConfirm" id="passwordConfirm" required oninput="check(this)"></li>'+
    '</ul><input type="submit" value="updatePassword"></form>';
}
function check(input) {
  if (input.value != document.getElementById('password').value) {
    input.setCustomValidity('パスワードが一致しません');
  } else {
    // input is valid -- reset the error message
    input.setCustomValidity('');
  }
}
//設定画面左側の設定ボタンを押したとき呼ばれる。引数のnumユーザが持っているカテゴリの数
function SetteiHenkouGamen(num) {
  let SETTEIFORM ='<form method="POST" action="SetteiServlet" id="henkouform">';
  let SYUUNYUINPUT='収入<input type="text" name="syuunyu" placeholder="<%=bean.getSyunyuu();%>"><input type="submit" value="updateSyunyuu" name="choice"></form><br>';
  let MOKUHYOUINPUT='目標:<%=bean.getMokuhyougoukei();%><br>';
  let KOUMOKUALL='<table border="1"  id="koumokulist"></table><tr>';
  let KATEGORYTOUROKU='<form method="POST" action="SetteiServlet"><table>'+
  '<td><input type="text" name="categoryName"  value="" placeholder="'+"追加する項目名を入力してください"+'"></td>'+
  '<td><input type="text" name="newMokuhyoukingaku"  value="" placeholder="'+0+'"></td>'+
  '<td><input type="submit" value="addCategory" name="choice"></td></tr></table></form>';
  var KOUMOKULISTMENBER='';

  document.getElementById('hanni').innerHTML = '';
  document.getElementById('hanni').innerHTML = SYUUNYUINPUT+MOKUHYOUINPUT+KATEGORYTOUROKU+KOUMOKUALL;

  for(var i=0;i<num;i++)
  {
    KOUMOKULISTMENBER+='<form method="POST" action="SetteiServlet"><tr  border="1" id="'+i+'"></tr></form>';
  };
  document.getElementById('koumokulist').innerHTML = KOUMOKULISTMENBER;

  for(let j=0;j<num;j++)
  {
    document.getElementById(j).innerHTML =
      '<td  border="1"><input type="text" name="categoryName"  value="" placeholder="'+'<%=bean.getCategoryMokuhyouList('+i+').getCategoryName();%>"'+'></td>'+
      '<td  border="1"><input type="text" name="kouinMokuhyou" value="" placeholder="'+'<%=bean.getCategoryMokuhyouList('+i+').getMokuhyouKingaku();%>"'+'></td>'+
      '<td  border="1">'+
      '<input type="submit" name="choice" value="deleteCategory">'+
      '<input type="hidden" name="year" value="'+'<%=bean.getCategoryMokuhyouList('+i+').getCategoryName();%>">'+
      '<input type="hidden" name="month" value="'+'<%=bean.getCategoryMokuhyouList('+i+').getMokuhyouKingaku();%>"></td>';
  };

}
//設定画面左側のヘルプボタンを押したとき呼ばれる。
function HelpGamen() {
  document.getElementById('hanni').innerHTML =
    '<h2>画面説明</h2><p>バンジー画面</p>'+
    '<ul><li>日付が進むと男の子が下に落ちていくよ！</li>'+
    '<li>犯人は支出額が増えるごとに上へ上がっていくよ！</li>'+
    '<li>目標額より支出額が上回ったらゲームオーバーだよ！クリアを目指してがんばろう！</li></ul>'+
    '<p>コナン画面</p><ul>'+
    '<li>目標を達成するとコナン君からのアドバイスがもらえるよ！</li></ul>'+
    '<p>自白画面</p>'+
    '<ul><li>目標を超えてしまうと犯人がどうして犯行に及んだかを聞くことができるよ！</li>'+
    '<li>次からの参考にしよう！</li></ul>'+
    '<p>家計簿画面</p>'+
    '<ul><li>月ごとの支出一覧が見れるよ！</li>'+
    '<li>ここで支出の登録・編集・削除ができるんだ！</li></ul>'+
    '<p>分析画面</p>'+
    '<ul><li>月ごとに支出の割合が見れるよ！</li>'+
    '<li>円グラフがかっこいいね！</li></ul>'
    ;
}