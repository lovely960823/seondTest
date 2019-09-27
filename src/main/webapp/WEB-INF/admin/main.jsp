<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet" type="text/css" />
<title>管理员主页</title>
</head>

<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0
    }

    .total {
        width: 850px;
        height: 43px;
        border-radius: 3px;
        border: solid 1px #666;
        box-shadow: inset 0 0 5px #666;
        margin: 40px auto;
        position: relative;
    }

    .webName {
        display: inline-block;
        height: 43px;
        margin-left: 22px;
        margin-top: 6px;
        font-size: 20px;
        cursor: pointer;
    }

    .webName:hover {
        color: red;
    }


    ul {
        list-style: none;
    }

    .navBox {
        position: absolute;
        top: 10px;
    }

    .navBox .left {
        float: left;
        list-style: none;
        margin-left: 20px;
        font-size: 15px;
        cursor: pointer;
    }

    .navBox .left:hover {
        color: red;
    }

    .navBox .left:nth-child(1) {
        margin-left: 100px;
    }

    i {
        margin-left: 4px;
        margin-top: 2px;
    }

    a {
        text-decoration: none;
        color: #000;
        display: block;
        font-size: 16px;
    }

    a:hover {
        color: #F00;
        background-color: #666;
    }

    .navBox .left ul {
        display: none;
    }

    .navBox .left ul li {
        float: none;
        background-color: #eee;
        margin: 5px 0px;
    }

    .right {
        float: right;
        margin-right: 30px;
        font-size: 15px;
        margin-top: 8px;
        cursor: pointer;
    }

    .right:hover {
        color: red;
    }

    .fa-caret-down {
        float: right
    }


    .demo {
        width: 750px;
        height: 470px;
        margin: 60px auto;
        position: relative;

    }

    .imgBox li {
        opacity: 0;
        transition: all 0.5s;
        position: absolute;
    }

    .imgBox li.active {
        opacity: 1;
    }

    .imgBox li img {
        width: 750px;
        height: 470px;
    }

    .tabBox {
        position: absolute;
        bottom: 30px;
        left: 30px;

    }

    .tabBox li {
        display: inline-block;
        border: solid 2px rgba(0, 0, 0, 0.3);
        width: 10px;
        height: 10px;
        border-radius: 50%;
        margin-right: 6px;

    }

    .tabBox li.current {
        border-color: #fff;
        background: #fff;
        box-shadow: 0 0 1px 3px rgba(0, 0, 0, 0.3);
    }
</style>

<body style="background-color: #E3CECE">
<img alt="" src="resources/img/logo.png" align="top" style="width: 150px;height: 50px">
    <div class="total" style="width: 80%;height: 80%">
       
        <ul class="navBox">
        
            <li class="left"><a href="toTeacherList" style="color: red">教师名单</a></li>
            <li class="left"><a href="toStudentList" style="color: red">学生名单</a></li>
            <li class="left"><a href="classListAdmin" style="color: red">课程名单</a></li> 
            <li class="left" onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)">
              <a href="exportTeacher" onclick="exportT()" style="color: red"> 导出教师信息</a> 
                <ul>
                    <li><a href="exportStudent" onclick="exportT()" style="color: red">导出学生信息</a></li>
                    <li><a href="exportClass" onclick="exportT()" style="color: red">导出课表信息</a></li>
                </ul>
            </li>
        </ul>
        <div class="right">
            <span onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)" style="color: red">欢迎${sessionScope.user.name} 登陆
			<ul>
			    <li><a href="toPwdEdit" style="color: red">修改密码</a></li>
			    <li><a href="logout" style="color: red">退出</a></li>
			</ul>
			</span>
            <i class="fa fa-caret-down"></i>
        </div>
        <span class="right"><a href="main1"></a></span>
    </div>
     <div class="demo">
        <ul class="imgBox">
             <li>
                <img src="resources/img/1.jpg" alt="">
            </li>
            <li>
                <img src="resources/img/2.jpg" alt="">
            </li>
            <li>
                <img src="resources/img/3.jpg" alt="">
            </li>
            <li>
                <img src="resources/img/4.jpg" alt="">
            </li>
        </ul>
        <ul class="tabBox">
            <li></li>
            <li class="current"></li>
            <li></li>
            <li></li>
        </ul>
    </div> 
  
</body>
<script>
function exportT(){
	alert("导出成功");
	
}
    function displaySubMenu(li) {

        var subMenu = li.getElementsByTagName("ul")[0];

        subMenu.style.display = "block";

    }

    function hideSubMenu(li) {

        var subMenu = li.getElementsByTagName("ul")[0];

        subMenu.style.display = "none";

    }
    // 自动轮播
    var lisImg = document.querySelectorAll('.imgBox li');
    var lisTab = document.querySelectorAll('.tabBox li');

    // 设置全局变量，存储定时器
    var timer
    var demo = document.querySelector('.demo');
    // 设置页面上显示的初始图片
    // 设置显示图片索引值
    var showIndex = 0;

    function show() {
        for (var j = 0; j < lisTab.length; j++) {
            lisTab[j].className = '';
            lisImg[j].className = '';
        }
        lisTab[showIndex].className = 'current';
        lisImg[showIndex].className = 'active';
    }
    show();
    // 封装上一张
    function prev() {
        showIndex--;
        if (showIndex < 0) {
            showIndex = lisTab.length - 1;
        }
        show();
    }
    // 封装下一张
    function next() {
        showIndex = showIndex + 1;
        if (showIndex >= lisTab.length) {
            showIndex = 0;
        }
        show();

    }

    // 自动切换至上一张图片，索引值-1
    timer = setInterval(function () {
        next();
    }, 1500);
    // 当鼠标放在图片之上，停止轮播，清除定时器
    demo.onmouseover = function () {
        clearInterval(timer);
    }
    // 鼠标离开图片，继续轮播
    demo.onmouseleave = function () {
        //继续 执行倒计时
        timer = setInterval(function () {
            next();
        }, 1500);
    }
    for (var i = 0; i < lisTab.length; i++) {
        // 将索引值i绑定到每个li标签对象上
        lisTab[i].index = i;
        // 给每个li标签绑定一个鼠标滑过的事件(行为)
        lisTab[i].onmouseover = function () {
            showIndex = this.index;
            show();
        }
    }
</script>

</html>