
//广告区域设置
var dots = document.querySelector(".dots");
var imgs = document.querySelector(".bg-imgs");


//动态生成li
for(var i=0;i<imgs.children.length;i++){
    var li = document.createElement("li");
    li.setAttribute("index",i);
    dots.appendChild(li);
}

dots.children[0].className = 'picSelect';

// //为了循环效果  克隆第一张图片!!
// var clonenode = imgs.children[0].cloneNode(true);
// imgs.appendChild(clonenode);

//给按钮添加动作
var index = 0;


for(var i=0;i<dots.children.length;i++){
    dots.children[i].onclick = function(){
        //设置自身高亮
        for(var i=0;i<dots.children.length;i++){
            dots.children[i].className = '';
        }
        this.className = 'picSelect';
        
        var pin = this.getAttribute("index");
        index = pin;
        if(pin==0){
            animateX(imgs,0,10);
        }else{
            animateX(imgs,-pin*1200-7,10);
        }
        
    }
}

//自动播放
var imgsfun = function(){
    index++;
    index = index%imgs.children.length;
    if(index==0){
        animateX(imgs,0,10);
    }else{
        animateX(imgs,-index*1200-7,10);
    }
    //设置自身高亮
    for(var i=0;i<dots.children.length;i++){
        dots.children[i].className = '';
    }
    dots.children[index].className = 'picSelect';
}
var imgsTimer = setInterval(imgsfun,3000);

//鼠标悬停图片停留！！！
for(var i=0;i<imgs.children.length;i++){
    imgs.children[i].addEventListener("mouseover",function(){
        clearInterval(imgsTimer);
    });
    imgs.children[i].addEventListener("mouseleave",function(){
        imgsTimer = setInterval(imgsfun,3000);
    });
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