<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>我的修改</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" media="screen" href="resources/css/style.css">
  <link rel="stylesheet" type="text/css" href="resources/css/reset.css"/>
</head>
<body>

<div id="particles-js">
		<div class="login">
			<div class="login-top">
				修改密码
			</div>
			<form:form action="pwdEditStudent" method="post" id="formSubmit">
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="resources/img/name.png"/></div>
				<div class="login-center-input">
					<input type="password" name="pwds" id="newpwd" value="" placeholder="请输入您的新密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的新密码'"/>
					<div class="login-center-input-text">新密码</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="resources/img/password.png"/></div>
				<div class="login-center-input">
					<input type="password" name="pwdss" id="newpwds" value="" placeholder="确认新密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
					<div class="login-center-input-text">确认密码</div>
				</div>
			</div>
			<div class="login-button">
				<a href="" onclick="ensure()">确定修改</a>
			</div>
			</form:form>
		</div>
		<div class="sk-rotating-plane"></div>
</div>

<!-- scripts -->
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="resources/js/particles.min.js"></script>
<script src="resources/js/app.js"></script>

<script type="text/javascript">
	function ensure(){
		var pwd1 = $("#newpwd").val();
		var pwd2 = $("#newpwds").val();
		if(pwd1.length==0||pwd2.length==0){
			alert("密码不能为空");
			return false;
		}
		if(pwd1!=pwd2){
			alert("两次输入的新密码不一样");
			return false;
		}else{
			
			//$("#formSubmit").submit();
			$.ajax({
				url:"pwdEditStudent",
				data:$('#formSubmit').serialize(),
				type:"POST",
				success:function(data){
					if(data == 0){
						alert("修改成功");
						window.location.href="classList";
					}else{
						alert("失败");
					}
				},
				error:function(data){
					alert("服务器忙，请稍后重试" );
				},
				dataType: "json"
			});
		}
	}
	</script>
</body>
</html>