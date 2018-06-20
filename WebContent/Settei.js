

function YuzaHenkouGamen() {
  document.getElementById('hanni').innerHTML = '';
}
function SetteiHenkouGamen() {
  let SETTEIFORM ='<form method="POST" action="BungyServlet" id="henkouform"></form>';
  let SYUUNYUINPUT='収入<input type="text" name="syuunyu" value="120000"><br>';
  let MOKUHYOUINPUT='目標:10000円<br>';
  let KOUMOKUALL='<table border="1"  id="koumokulist"></table>';
  let KATEGORYTOUROKU='<form method="POST" action="SetteiServlet">'+
                       '<input type="text" name="categoryName"  value="" placeholder="'+"追加する項目名を入力してください"+'">'+
                       '<input type="submit" value="追加" name="choice"></form>';
  var KOUMOKULISTMENBER='';

  document.getElementById('hanni').innerHTML = '';
  document.getElementById('hanni').innerHTML = SYUUNYUINPUT+MOKUHYOUINPUT+KATEGORYTOUROKU+KOUMOKUALL;

  for(var i=0;i<10;i++)
  {
    KOUMOKULISTMENBER+='<tr  border="1" id="'+i+'"></tr>';
  };
  document.getElementById('koumokulist').innerHTML = KOUMOKULISTMENBER;

  for(let j=0;j<10;j++)
  {
    document.getElementById(j).innerHTML =
    '<form method="POST" action="SetteiServlet">'+
    '<td  border="1"><input type="text" name="categoryName"  value="" placeholder="'+"食費"+'"></td>'+
    '<td  border="1"><input type="text" name="kouinMokuhyou" value="" placeholder="'+"10000"+'"></td>'+
    '<td  border="1"><input type="submit" value="deleteCategory" name="choice"></td>'+'</form>';
  };

}
function HelpGamen() {
  document.getElementById('hanni').innerHTML = '';
}