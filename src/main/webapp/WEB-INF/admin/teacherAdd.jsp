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
<title>教师</title>
</head> 
<body class="container"> 
<h1 align="center">添加教师信息</h1> 
<div class="with:80%">
		<form:form class="form-horizontal" modelAttribute="teacher" id="formSubmit"
		action="doTeacherAdd" method="post">
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">教师姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						id="name" placeholder="请输入姓名" autocomplete="off" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="num" class="col-sm-2 control-label">教师编号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="num" autocomplete="off"
						id="num" placeholder="请输入编号" onkeyup="this.value=this.value.replace(/\D/g, '')"/>
				</div>
			</div>
			
			 <div class="form-group">
				<label for="num" class="col-sm-2 control-label">教师照片</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="photoFile"
						id="photoFile" placeholder="" onchange="getFile(this)"/>
				</div>
			</div> 
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">入职日期</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="pubdate" id="inputPubDate"
					autocomplete="off"	placeholder="日期" />
			    </div>
			</div>
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="phone" id="phone"
					autocomplete="off"	placeholder="电话" />
			    </div>
			</div>
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">办公室</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="address" id="address"
					autocomplete="off"	placeholder="办公地点" />
			    </div>
			</div>
			
			<div class="form-group">
				<label for="pubdate" class="col-sm-2 control-label">邮箱</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="email" id="email"
					autocomplete="off"	placeholder="邮箱" />
			    </div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" value="确认" class="btn btn-info" onclick="ensure()"/> &nbsp;
					&nbsp; &nbsp; <input type="reset" value="重置"
						class="btn btn-info" />  &nbsp; &nbsp; &nbsp;
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

function getFile(obj) {
	var imgStr = /\.(jpg|jpeg|png|bmp|BMP|JPG|PNG|JPEG)$/;
	var imgFile = obj.files[0];
	if(!imgStr.test(imgFile.name)) {
		alert("请选择正确的图片格式");
		document.getElementById("photoFile").value="";
		return false;
	}
	
}

$(function(){
	//日期控件
	$('#inputPubDate').datepicker({
		format : 'yyyy-mm-dd',//日期格式
		language : 'zh-CN',//提示中文界面
		autoclose : true//自动关闭
	});
})
function tomain(){
	 window.location.href="main" 
}
  function ensure(){
		var val1 = $("#name").val();
		var val2 = $("#num").val();
		var val3 = $("#inputPubDate").val();
		//验证手机号码
		var str = $("#phone").val();
		var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
		//验证邮箱
		var str1 =$("#email").val();
		var reg1 = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(val1.length==0){
			alert("姓名不能为空");
			return false;
		}
		if(val2.length==0){
			alert("教师编号不能为空");
			return false;
		}if(val3.length==0){
			alert("入职日期不能为空");
			return false;
		}if (!reg.test(str)){
			alert("请检查手机号码是否正确");
			return false;
		}if(!reg1.test(str1)){
			alert("请检查邮箱格式是否正确");
			return false;
		}else{
			//$("#formSubmit").submit();
			var form = new FormData(document.getElementById("formSubmit"));
			$.ajax({
				url:"doTeacherAdd",
				//data:$('#formSubmit').serialize(),
				data:form,
				processData:false,
                contentType:false,
				type:"POST",
				success:function(data){
					if(data == 0){
						
						alert("添加成功");
						window.location.href="toTeacherAdd";
					}else{
						alert("添加失败,该编号已经存在，不可重复");
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