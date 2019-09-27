<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<title>打分</title>
</head> 
<body class="container"> 
<br/> 
<h1 align="center">成绩打分</h1> 

<div class="with:80%">
		<form:form class="form-horizontal" modelAttribute="choose" id="formSubmit"
			action="doScoreEdit" method="post">
		<input type="hidden" value="${id}" name="id"> 
		<div class="form-group">
				<label for="num" class="col-sm-2 control-label">课程名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="cname" value="${cname}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label for="num" class="col-sm-2 control-label">课程编号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="cum" value="${cnum}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label for="num" class="col-sm-2 control-label">学生姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="sname" value="${sname}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label for="num" class="col-sm-2 control-label">学生学号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="snum" value="${snum}" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label for="num" class="col-sm-2 control-label">学生分数</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="score" value="${score}"
						id="score" placeholder="成绩" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" value="确认" class="btn btn-info" onclick="ensure()"/> &nbsp;
					&nbsp; &nbsp; <input type="reset" value="重置"
						class="btn btn-info" />&nbsp; &nbsp; &nbsp;
						<input type="button" class="btn btn-info" value="返回主页" onclick="tomain()"/>
				</div>
			</div>
			</form:form>
	</div> 
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript">
function tomain(){
	 window.location.href="classListTeacher" 
}

  function ensure(){
		var val2 = $("#score").val();
		var val3 = $("#desc").val();
		if(val2<0||val2>100){
			alert("分数在0-100之间");
			return false;
		}
		if(val2.length==0){
			alert("请给该学生成绩");
			return false;
		} else{
			
			alert("打分成功");
			$("#formSubmit").submit(); 
			 /*  $.ajax({
					url:"doScoreEdit",
					data:$('#formSubmit').serialize(),
					type:"POST",
					success:function(data){
						if(data == 0){
							
							alert("打分成功");
							 //window.location.href="toTeacherList" 
							 window.location.reload();
							 
						}else{
							alert("打分失败");
						}
					},
					error:function(data){
						alert("服务器忙，请稍后重试" );
					},
					dataType: "json"
				});   */ 
		}
	}
	
	
</script>
</body> 
</html>