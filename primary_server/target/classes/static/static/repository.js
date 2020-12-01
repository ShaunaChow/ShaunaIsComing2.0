var currentURL = '/';
var items = document.querySelector('.items');

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

function dirClick(e) {
    e.preventDefault();
    currentURL = currentURL + this.innerText;
    freshItems();
    console.log(currentURL);
}

function createItem(node) {
    var div = document.createElement('div');
    if (node.isFile == 0) {
        div.className = 'file';
    }else{
        div.className = 'dir';
    }

    var cover = document.createElement('div');
    cover.className = 'cover';
    div.appendChild(cover);

    var a1 = document.createElement('a');
    if (node.isFile == 0) {
        a1.href = '/shaunafs/info?filePath='+currentURL+node.path;
    }else{
        a1.href = '';
        a1.onclick = dirClick;
    }
    a1.className = 'name';
    a1.innerText = node.path;
    div.appendChild(a1);

    var a2 = document.createElement('a');
    a2.href = '/shaunafs/downloadtmp?filePath='+currentURL+node.path;
    a2.className = 'download';
    a2.innerText = '下载';

    var a3 = document.createElement('a');
    a3.setAttribute('data-url',currentURL+node.path);
    if (node.isFile == 0) {
        a3.href = '';
        a3.onclick = fileDelete;
    }else{
        a3.href = '';
        a3.onclick = dirDelete;
    }
    a3.className = 'delete';
    a3.innerText = '删除';
    div.appendChild(a3);

    if (node.isFile == 0) {
        div.appendChild(a2);
    }

    var span = document.createElement('span');
    span.innerText = '-';
    div.appendChild(span);

    return div;
}

function fileDelete(e) {
    e.preventDefault();
    var request = createXMLHttpRequest();
    request.open('GET','/shaunafs/rmfiletmp?filePath='+this.getAttribute('data-url'), true);
    request.setRequestHeader('Authorization','bearer '+sessionStorage.getItem('shauna-token'));
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            if (text == 'success') {
                freshItems();
            }else {
                console.log('未知错误');
            }
        }
    };
}

function dirDelete(e) {
    e.preventDefault();
    var request = createXMLHttpRequest();
    request.open('GET','/shaunafs/rmdirtmp?filePath='+this.getAttribute('data-url'), true);
    request.setRequestHeader('Authorization','bearer '+sessionStorage.getItem('shauna-token'));
    request.send(null);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            if (text == 'success') {
                freshItems();
            }else {
                console.log('未知错误');
            }
        }
    };
}

function freshItems() {
    var num = items.children.length;
    for(var i=0;i<num;i++){
        items.removeChild(items.children[0]);
    }

    var request = createXMLHttpRequest();

    request.open('GET','/shaunafs/getDir?path='+currentURL, true);
    request.setRequestHeader('Authorization','bearer '+sessionStorage.getItem('shauna-token'));

    request.send(null);

    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            var res = JSON.parse(text);
            if (res.code == 200) {
                var msg = res.msg;
                for(var i=0;i<msg.length;i++){
                    var node = msg[i];
                    items.insertBefore(createItem(node),items.children[0]);
                }
                var clear = document.createElement('div');
                clear.className = 'clear';
                items.appendChild(clear);
            }else {
                console.log('未知错误');
            }
        }
    };
    var dirs = document.querySelector('.dirs');
    while(dirs.children.length>1){
        dirs.removeChild(dirs.children[dirs.children.length-1]);
    }
    addDirs(dirs);
}

function addDirs(dirs) {
    var remain = currentURL;
    var longname = '';
    while(remain.length>0) {
        var cur = remain.substring(0, remain.indexOf('/') + 1);
        longname = longname+cur;
        remain = remain.substring(remain.indexOf('/') + 1);
        var a = document.createElement('a');
        a.href = '';
        a.setAttribute('data-url',longname);
        a.onclick = aClick;
        a.innerText = cur;
        dirs.appendChild(a);
        var span = document.createElement('span');
        span.innerText = '> ';
        dirs.appendChild(span);
    }
}

function aClick(e) {
    e.preventDefault();
    currentURL = this.getAttribute('data-url');
    freshItems();
}

function basic(){
    var fanhui = document.querySelector('.fanhui');
    fanhui.onclick = function (e) {
        if (currentURL=='/'){
            console.log('/');
        } else{
            currentURL = currentURL.substring(0,currentURL.length-1);
            currentURL = currentURL.substring(0,currentURL.lastIndexOf('/')+1);
            freshItems();
        }
    };
    
    var fileInput = document.querySelector('#files');
    fileInput.onchange = function () {
        //读取文件
        var reader = new FileReader();
        var file = this.files[0];
        reader.readAsDataURL(file);
        startFileUpload(file);
    };

    var newBTN = document.querySelector('.newBTN');
    newBTN.onclick = function (e) {
        var name = prompt('请输入你想创建的文件夹名');
        if (name.charAt(name.length - 1) != '/') {
            name = name+'/';
        }
        var request = createXMLHttpRequest();
        request.open('GET','/shaunafs/mkdirtmp?filePath='+currentURL+name, true);
        request.setRequestHeader('Authorization','bearer '+sessionStorage.getItem('shauna-token'));
        request.send(null);
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                var text = request.responseText;
                if (text == 'success') {
                    freshItems();
                }else {
                    console.log('未知错误');
                }
            }
        };
    };

    var upload = document.querySelector('.upload');
    upload.onclick = function () {
        var input = document.querySelector('#files');
        input.click();
    };

    var allFiles = document.querySelector('.allFiles');
    allFiles.onclick = function (e) {
        currentURL = '/';
        freshItems();
    };
}

function startFileUpload(file){
    if(file.size>10485760){
        alert('上传文件8能超过10MB！！');
        return;
    }
    var formData = new FormData();
    formData.append('filePath',currentURL+file.name);
    formData.append('file',file);

    var request = createXMLHttpRequest();

    request.open('POST','/shaunafs/uploadtmp', true);
    request.setRequestHeader('Authorization','bearer '+sessionStorage.getItem('shauna-token'));

    request.send(formData);

    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            var text = request.responseText;
            if (text == 'success') {
                freshItems();
            }else {
                console.log('未知错误');
            }
        }
    };
}

function check(){
    if (sessionStorage.getItem('shauna-token')==null) {
        window.location.href = '/primary/login/login.html?msg=pleaseLogin';
    }
}

window.addEventListener('load',check);
window.addEventListener('load',freshItems);
window.addEventListener('load',basic);