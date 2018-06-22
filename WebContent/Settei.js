var help= document.getElementById("help");
var settei = document.getElementById("settei");
var yuzaHenkou = document.getElementById("yuzaHenkou");

function check(input)
{
  if (input.value != document.getElementById('password').value)
  {
    input.setCustomValidity('パスワードが一致しません');
  } else
  {
    // input is valid -- reset the error message
    input.setCustomValidity('');
  }
}

//設定画面左側のIDボタンを押したとき呼ばれる。
function YuzaHenkouGamen()
{
  document.getElementById("help").style.display="none";
  document.getElementById("yuzaHenkou").style.display="block";
  document.getElementById("settei").style.display="none";
}

//設定画面左側の設定ボタンを押したとき呼ばれる。
function SetteiHenkouGamen()
{
  document.getElementById("help").style.display="none";
  document.getElementById("yuzaHenkou").style.display="none";
  document.getElementById("settei").style.display="block";
}

//設定画面左側のヘルプボタンを押したとき呼ばれる。
function HelpGamen()
{
  document.getElementById("help").style.display="block";
  document.getElementById("yuzaHenkou").style.display="none";
  document.getElementById("settei").style.display="none";
}


function subKoumokuMeiHennkou()
{
  document.getElementById("deleteKoumoku").style.display="none";
  document.getElementById("updateMokuhyou").style.display="none";
  document.getElementById("updateKoumoku").style.display="block";
}
function subMokuhyouHenkou()
{
  document.getElementById("deleteKoumoku").style.display="none";
  document.getElementById("updateMokuhyou").style.display="block";
  document.getElementById("updateKoumoku").style.display="none";
}
function subKoumokusakujo()
{
  document.getElementById("deleteKoumoku").style.display="block";
  document.getElementById("updateMokuhyou").style.display="none";
  document.getElementById("updateKoumoku").style.display="none";
}

