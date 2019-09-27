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
<title>添加课程</title>
</head>
<body class="container"> 
<br/> 
<h1 align="center">添加课程信息</h1> 
<div class="with:80%">
		<form:form class="form-horizontal" modelAttribute="tClass" id="formSubmit"
		 action="doClassAdd" method="post">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">课程名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						id="name" placeholder="请输入名称" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">课程编号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="num" id="num" onkeyup="this.value=this.value.replace(/\D/g, '')"
						placeholder="课程编号" />
			    </div>
			</div>
			
			<div class="form-group ">
						<form:label path="tid" cssClass="col-sm-2 col-form-label">
							上课教师
						</form:label>
						<div class="col-sm-10">
							<form:select items="${types}" itemLabel="name" itemValue="id"
								path="tid" cssClass="form-control" />
						</div>
             </div> 
             
            <div class="form-group ">
						<form:label path="locationId" cssClass="col-sm-2 col-form-label">
							上课地点
						</form:label>
						<div class="col-sm-10">
							<form:select items="${location}" itemLabel="name" itemValue="id"
								path="locationId" cssClass="form-control" />
						</div>
             </div> 
			
			<div class="form-group ">
						<form:label path="timeId" cssClass="col-sm-2 col-form-label">
							上课时间
						</form:label>
						<div class="col-sm-10">
							<form:select items="${time}" itemLabel="name" itemValue="id"
								path="timeId" cssClass="form-control" />
						</div>
             </div> 
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">可选总数</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="count" id="count" onkeyup="this.value=this.value.replace(/\D/g, '')"
						placeholder="可选数" />
			    </div>
			</div>
			
			
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" value="确认" class="btn btn-info" onclick="ensure()"/> &nbsp;
					&nbsp; &nbsp; <input type="reset" value="重置"
						class="btn btn-info" /> &nbsp; &nbsp; &nbsp;
						<input type="button" class="btn btn-info" value="返回主页" onclick="tomain()"/>
				</div>
			</div>
		</form:form>
	</div>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
function tomain(){
	 window.location.href="main" 
}
function ensure(){
	var name = $("#tclassname")
	var val1 = $("#name").val();
	var val2 = $("#count").val();
	var val3 = $("#num").val();
	
	if(name.length!=0){
		alert("该课程名称已经存在，请核对后再添加");
		return false;
	}
	
	if(val1.length==0){
		alert("名称不能为空");
		return false;
	}
	if(val2.length==0){
		alert("可选总数不能为空");
		return false;
	}if(val2==0){
		alert("可选总数不能0");
		return false;
	}if(val3.length==0){
		alert("课程编号不能为空");
		return false;
	}else{
		//$("#formSubmit").submit();
		$.ajax({
			url:"doClassAdd",
			data:$('#formSubmit').serialize(),
			type:"POST",
			success:function(data){
				if(data == 0){
					alert("添加成功");
					window.location.reload();
				}else if(data == 1){
					alert("添加失败,该编号已经存在，不可重复");
				}
				else if(data == 2){
					alert("添加失败,该课程名称已经存在，不可重复");
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