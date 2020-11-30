function createXMLHttpRequest() {
    try{
        var request = new XMLHttpRequest();
        return request;
    }catch (e) {
        try{
            return new ActiveXObject('Msxml2.XMLHTTP');
        }catch (e2) {
            console.log(e2);
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
var passwds = document.querySelectorAll(".regist .passwd");
var but = document.querySelector(".submit");
var mail = document.querySelector(".regist #name");
var promt0 = document.querySelector(".promt0");
var promt1 = document.querySelector(".promt1");
var promt2 = document.querySelector(".promt2");
var promt3 = document.querySelector(".promt3");
var code = document.querySelector("#validationCode");


mail.addEventListener("blur",function(){
    var text = mail.value;
    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (!myReg.test(text)) {
        promt0.style.display = "block";
        promt0.innerHTML = "邮箱格式不正确哦";
    }else{
        promt0.style.display = "none";
    }
});

passwds[0].addEventListener("blur",function(){
    var text = passwds[0].value;
    if(text.length<6) {
        promt1.style.display = "block";
        promt1.innerHTML = "密码太短";
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
        promt2.innerHTML = "密码不一致";
    }
});


but.addEventListener("click",function(e){
    e.preventDefault();
    var text1 = passwds[1].value;
    var text0 = passwds[0].value;
    var text2 = mail.value;
    var text3 = code.value;

    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (!myReg.test(text2)) {
        promt0.style.display = "block";
        promt0.innerHTML = "邮箱格式不正确哦";
        return;
    }
    if(text0.length<6){
        promt1.style.display = "block";
        promt1.innerHTML = "密码太短";
        return;
    }
    if(text1!=text0){
        promt2.style.display = "block";
        promt2.innerHTML = "密码不一致";
        return;
    }
    if (text3.length!=6){
        promt3.style.display = "block";
        promt3.innerHTML = "验证码为6位";
        return;
    }

    console.log("======="+text3);
    var request = createXMLHttpRequest();
    request.open('POST','/regist/registServlet', true);
    request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    request.send("mail="+text2+"&psw="+text1+"&validationCode="+text3);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText.toString();
            console.log(text);
            if (text == "validationCodeError") {
                promt0.style.display = "none";
                promt1.style.display = "none";
                promt2.style.display = "none";
                promt3.style.display = "block";
                promt3.innerHTML = "验证码错误";
            }else if(text == "alreadyExits"){
                promt0.style.display = "block";
                promt1.style.display = "none";
                promt2.style.display = "none";
                promt3.style.display = "none";
                promt0.innerHTML = "用户已存在";
            }else if(text == "failed"){
                window.location.href = "/primary/error.html";
            }else{
                window.location.href = "/primary/login/login.html";
            }
        }
    };
});

var getCode = document.querySelector(".getCode");
var disableTime = 60;
var interval;
getCode.addEventListener("click",function (e) {
    e.preventDefault();
    var myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
    if (!myReg.test(mail.value)) {
        promt0.style.display = "block";
        return;
    }
    var request = createXMLHttpRequest();
    request.open('GET','/regist/validationCode?mail='+mail.value, true);
    request.send(null);
    interval = window.setInterval(timeCount,1000);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            console.log(text);
        }
    };
});

function timeCount() {
    disableTime --;
    if (disableTime == 0) {
        disableTime = 60;
        getCode.innerHTML = "点击获取验证码";
        getCode.disabled = false;
        getCode.style.backgroundColor = "pink";
        //删除定时器
        clearInterval(interval);
    }else{
        getCode.style.backgroundColor = "#eee";
        getCode.disabled = true;
        getCode.innerHTML = ""+disableTime+"秒后重新获取";
    }
}