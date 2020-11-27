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
var but = document.querySelector(".submit");
var phoneNum = document.querySelector(".regist #name");
var promt0 = document.querySelector(".promt0");

phoneNum.addEventListener("blur",function(){
    var text = phoneNum.value;
    if(isNaN(text)||text.length!=11){
        promt0.style.display = "block";
    }else{
        promt0.style.display = "none";
    }
});



but.addEventListener("click",function(e){
    var text2 = phoneNum.value;
    if(isNaN(text2)||text2.length!=11){
        promt0.style.display = "block";
        e.preventDefault()
    }
});