function createXMLHttpRequest() {
    try{
        return new XMLHttpRequest();
    }catch (e) {
        try{
            return new ActiveXObject('Msxml2.XMLHTTP');
        }catch (e2) {
            throw e2;
        }
    }
}

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
// var but = document.querySelector(".submit");
var phoneNum = document.querySelector(".regist #name");
var promt0 = document.querySelector(".promt0");

phoneNum.addEventListener("blur",function(){
    var text = phoneNum.value;
    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (myReg.test(text) || (!isNaN(text) && text.length == 11)) {
        promt0.style.display = "none";
    }else{
        promt0.style.display = "block";
    }
});

// but.addEventListener("click",function(e){
//     var text2 = phoneNum.value;
//     var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
//     if (myReg.test(text2) || (!isNaN(text2) && text2.length == 11)) {
//         promt0.style.display = "none";
//     }else{
//         promt0.style.display = "block";
//         e.preventDefault()
//     }
// });

var submit = document.querySelector('.submit');
var phone = document.querySelector('.phone');
var password = document.querySelector('.password');
var mag = document.querySelector('.error');


submit.addEventListener("click",function (e) {
    e.preventDefault();
    var phoneNum = phone.value;
    var psw = password.value;

    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (myReg.test(phoneNum) || (!isNaN(phoneNum) && phoneNum.length == 11)) {
        promt0.style.display = "none";
    }else{
        promt0.style.display = "block";
        return;
    }

    var request = createXMLHttpRequest();
    request.open('POST','/uaa/oauth/token', true);
    request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            var res = JSON.parse(text);
            if (res.access_token) {
                sessionStorage.setItem("shauna-token",res.access_token);
                window.location.href = '/primary/success.html';
            }
        }else if(request.status >= 400){
            mag.innerHTML = '账号或密码错误';
        }
    };
    request.send("client_id=c1&client_secret=123&grant_type=password&username="+phoneNum+"&password="+psw);
});

var href = window.location.href;
console.log(href);

if (href.toString().search('pleaseLogin')!=-1) {
    mag.innerHTML = '请先登录';
}