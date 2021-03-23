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

var textArea = document.getElementsByTagName('textarea');

var f = function(e) {
    if (e.keyCode == 9) {
        e.preventDefault();
        var indent = '    ';
        var start = this.selectionStart;
        var end = this.selectionEnd;
        var selected = window.getSelection().toString();
        selected = indent + selected.replace(/\n/g, '\n' + indent);
        this.value = this.value.substring(0, start) + selected
            + this.value.substring(end);
        this.setSelectionRange(start + indent.length, start
            + selected.length);
    }
};

for (var i=0;i<textArea.length;i++){
    textArea[i].onkeydown = f;
}

var run = document.querySelector('.run');
var codeToSend = document.querySelector('.codeToSend');
var inputToSend = document.querySelector('.inputToSend');
var outputToShow = document.querySelector('.outputToShow');

codeToSend.value = "import java.util.*;\n" +
    "\n" +
    "/**\n" +
    " * 请开始你的表演!!!\n" +
    " */\n" +
    "\n" +
    "public class Main{\n" +
    "    public static void main(String[] args) throws Exception {\n" +
    "        System.out.println(\"人人都爱周大风!!!\");\n" +
    "    }\n" +
    "}";

run.onclick = function (e) {
    run.innerHTML = "正在运行";
    outputToShow.value = "";
    var source = codeToSend.value;
    var input = inputToSend.value;
    var request = createXMLHttpRequest();
    source = encodeURIComponent (source);
    input = encodeURIComponent (input);
    request.open('POST','/shaunacode/execute', true);
    request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            var res = JSON.parse(text);
            if (res.code==200) {
                outputToShow.value = res.msg;
            }else{
                outputToShow.value = "出现错误\n"+res.msg;
            }
        }else if(request.status >= 400){
            outputToShow.value = "出现错误\n";
        }
        run.innerHTML = "运行";
    };
    request.send("source="+source+"&input="+input);
};