
function newSeting() {
    var temp = document.createElement("form");
    <!-- 跳转连接-->
    temp.action = "/";
    temp.method = "get";
    temp.style.display = "none";
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}


//动画
function activeBoard(current) {
    var temp = document.createElement("form");
    temp.action = "/active";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("input");
    opt.name = "current";
    opt.value = current;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

//动画
function noactiveBoard(current) {
    var temp = document.createElement("form");
    temp.action = "/noactive";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("input");
    opt.name = "current";
    opt.value = current;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}













