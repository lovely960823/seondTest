<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/main.css" rel="stylesheet" type="text/css" />
<link href="resources/css/SetTime.css" rel="stylesheet" type="text/css" />
<title>登录界面</title>
<!--css里面的图片加载不到，原因不明，所以拿到这里直接使用-->
<style type="text/css">
body {
	background: #fff;
	background-image: url("resources/img/02.jpg");
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

</style>
</head>
<body>
<img alt="" src="resources/img/logo.png" align="top" style="width: 150px;height: 50px">
	<main>
	<!-- 时间 -->
	<div class="text-center">
		<span id="year"></span>&nbsp&nbsp<span id="month"></span>&nbsp&nbsp<span
			id="time"></span>
	</div>
	<!-- 主页 -->
	<div class="alert alert-warning" role="alert" style="<c:if test="${empty msg}">visibility:hidden</c:if>">${msg}</div>
	<form action="/login" method="post" id="formSubmit">
		<input type="text" name="num"  value="${param.num}" placeholder="请输入账号" id="num" required>
		<br>
		<br> <input type="password" name="pwd" placeholder="请输入密码" id="pwd"
			required> <br>
		<br> <input type="text" id="yz" name="vcode" placeholder="验证码" autocomplete="off" 
			required maxlength="4" style="width: 200px"> <img src="vcode.png" class="vcodeImg"
			title="单机换图片" id="myVcode" style="height: 50px; width: 160px" /> <br>
			<select class="form-control" id="numbers" name="type" style="width: 438px">
			          <option value="1">管理员</option>
                      <option value="2">教师</option>
                      <option value="3" selected>学生</option>
            <select>

		<br> <input type="button" class="login" value="登录" onclick="ensure()" style="width: 438px"><br>
	</form>
	</main>

	<script type="text/javascript"
		src="resources/bower_components/jquery/dist/jquery.slim.min.js">
	</script>
	
	
	<script type="text/javascript"
		src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js">
	</script>
	
	<script type="text/javascript" 
	    src="resources/js/SetTime.js">
	</script>
    <script type="text/javascript" 
	    src="resources/js/yanzheng.js">
	</script>
	<script type="text/javascript">
	function ensure(){
		var val1 = $("#num").val();
		var val2 = $("#pwd").val();
		var val3 = $("#yz").val();
		if(val1.length==0){
			alert("账号不能为空");
			return false;
		}
		if(val2.length==0){
			alert("密码不能为空");
			return false;
		}if(val3.length==0){
			alert("验证码不能为空");
			return false;
		}else{
			$("#formSubmit").submit();
		}
	}
	</script>
</body>
</html>