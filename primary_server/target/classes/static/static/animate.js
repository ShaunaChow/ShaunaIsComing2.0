var animateX = function(obj,tar,vel,fun){
    var pos = obj.offsetLeft;
    clearInterval(obj.timer);
    obj.timer = setInterval(function(){
        pos = obj.offsetLeft;
        if(pos!=tar){
            var step = (tar-pos) / 10;
            step = step>0?Math.ceil(step):Math.floor(step);
            obj.style.left = pos + step + "px";
        }else{
            clearInterval(obj.timer);
            fun && fun();
        }
    },vel);
};

var animateY = function(tar,vel,fun){
    var pos = window.pageYOffset;
    var timer = setInterval(function(){
        pos = window.pageYOffset;
        if(pos!=tar){
            var step = (tar-pos) / 10;
            step = step>0?Math.ceil(step):Math.floor(step);
            window.scroll(0,pos + step);
        }else{
            clearInterval(timer);
            fun && fun();
        }
    },vel);
};