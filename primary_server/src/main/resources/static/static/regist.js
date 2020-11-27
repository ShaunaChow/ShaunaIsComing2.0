//导航栏设置
var shows = document.querySelectorAll(".nav>ul>li");

for(var i=0;i<shows.length;i++){
    shows[i].onmouseover = function(){
        var toshow = this.children[1];
        toshow.style.display = "block";
    }
    shows[i].onmouseout = function(){
        var toshow = this.children[1];
        toshow.style.display = "none";
    }
}


//密码太短提示等
var passwds = document.querySelectorAll(".regist #passwd");
var but = document.querySelector(".submit");
var phoneNum = document.querySelector(".regist #name");
var promt0 = document.querySelector(".promt0");
var promt1 = document.querySelector(".promt1");
var promt2 = document.querySelector(".promt2");

phoneNum.addEventListener("blur",function(){
    var text = phoneNum.value;
    if(isNaN(text)||text.length!=11){
        promt0.style.display = "block";
    }else{
        promt0.style.display = "none";
    }
});

passwds[0].addEventListener("blur",function(){
    var text = passwds[0].value;
    if(text.length<6) {
        promt1.style.display = "block";
    }else {
        promt1.style.display = "none";
    }
});

passwds[1].addEventListener("blur",function(){
    var text1 = passwds[1].value;
    var text0 = passwds[0].value;
    if(text1==text0){
        promt2.style.display = "none";
    }else{
        promt2.style.display = "block";
    }
});


but.addEventListener("click",function(e){
    var text1 = passwds[1].value;
    var text0 = passwds[0].value;
    var text2 = phoneNum.value;
    if(isNaN(text2)||text2.length!=11){
        promt0.style.display = "block";
        e.preventDefault()
    }
    if(text0.length<6){
        promt1.style.display = "block";
        e.preventDefault();
    }
    if(text1!=text0){
        promt2.style.display = "block";
        e.preventDefault()
    }
});