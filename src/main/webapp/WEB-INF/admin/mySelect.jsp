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
<link href="resources/css/jquery.my-modal.1.1.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/jquery.my-modal.1.1.winStyle.css" rel="stylesheet" type="text/css"/>
<title>我选的课程</title>
</head>
<body>
<br>
<h1 align="center">我选择的课程</h1> 
<br>
<table class="table table-bordered">
  <thead>
    <tr>
      <th scope="col">序列</th>
       <th scope="col">所选课程编号</th>
      <th scope="col">所选课程名称</th>
      <th scope="col">上课教师编号</th>
      <th scope="col">上课教师</th>
       <th scope="col">上课时间</th>
        <th scope="col">上课地点</th>
      <th scope="col"><a href="classList">返回</a></th>
    </tr>
   </thead>
           <tbody>
                <c:choose>
					<c:when test="${empty ls}">
						<tr>
						<td colspan="9" align="center">无数据</td>
						</tr>
					</c:when>
				<c:otherwise>
			<c:forEach items="${ls}" var="ls" varStatus="l">
				<tr>
				    <td scope="row">${l.count}</td>
					<td>${ls.cnum}</td>
					<td title="点击查看分数"><a href="" onclick="showScore('${ls.score}')">${ls.cname}</a></td>
					<td>${ls.tnum}</td>
					<td class="btn1" onclick="detail('${ls.teaId}')" title="点击查看教师详细信息"><a href="javascript:void(0)">${ls.tname}</a></td>
					<td>${ls.timeName}</td>
					<td>${ls.locationName}</td>
					<td>
					<a href="" onclick="del('${ls.id }')">
						退课
					 </a>
					</td>
				</tr>
			</c:forEach>       
			</c:otherwise>
			</c:choose>
		</tbody>
</table>
<!-- 模态框 -->	
<div class="m-modal">
			<div class="m-modal-dialog">
				<div class="m-top">
					<h4 class="m-modal-title">
						教师简介
					</h4>
					<span class="m-modal-close">&times;</span>
				</div>
				<div class="m-middle">
					<table  border="1"  >
	                         <tr>
	                            <td rowspan="7" width="25%" align="center" valign="top" id="detail_photo">
	                            
	                            </td>
	                            <td colspan="3" height="40" style="color:#007bd7"><h4 id="">南阳理工学院教师个人信息</h4></td>
	                         </tr>
	                         <tr>
	                            <td height="30" width="30%">姓名：<span id="detail_name"></span></td>
	                            <td width="12%" align="right">编号：</td>
	                            <td><span id="detail_num"></span></td>
	                         </tr>
	                         <tr>
	                            <td height="30">入职日期：<span id="detail_date"></span></td>
	                            <td  align="right">联系方式：</td>
	                            <td ><span id="detail_phone"></span></td>
	                         </tr>
	                          <tr>
	                            <td height="30">办公室：<span id="detail_address"></span></td>
	                            <td  align="right">邮箱：</td>
	                            <td ><span id="detail_email"></span></td>
	                         </tr>
		                 </table>
				</div>
				<div class="m-bottom">
				<button class="m-btn-sure">确定</button>
				</div>
			</div>
		</div>
		<!-- 模态框 -->
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.my-modal.1.1.js"></script>
	<script type="text/javascript" >
	
	var m1 = new MyModal.modal(function() {});
	$('.btn1').on("click", function() {
		m1.show();
	});
	
	function del(rId){
		//alert(111);
			if (confirm("确认退课吗？")) {
				$.ajax({ 
					url:"exitClass",
					data:{'id':rId},
					type:'post',
					dataType:'json',
					success:function(data){
						console.log(data);
						 if(data.flag == 0){
							alert("退课成功");
							window.location.href="mySelect";
						}else{
							alert("退课失败"); 
						}  
						
					},
				error:function(data){
					alert('退选成功');
				} 
				}); 
			} 
	};
	
	function detail(id){
		$.ajax({
			url:"teacherDetail",
			data:{"id":id},
			type:"post",
			success:function(data){
				$("#detail_name").text(data.name);
				$("#detail_num").text(data.num);
				$("#detail_date").text(data.pubdate);
				$("#detail_phone").text(data.phone);
				$("#detail_address").text(data.address);
				$("#detail_email").text(data.email);
				if(data.photo == null || data.photo==""){
				$("#detail_photo").html('<img style="width:140px;height:170px" alt="" src="upload/teacher/404.jpg" >');
				}else{
				$("#detail_photo").html("<img style='width:140px;height:170px' src='"+data.photo+"' >");
				}
			},
			error:function(){
				alert('服务器繁忙');
			},
			dataType:"json"
		});
		}		
</script>
<script type="text/javascript">

	function showScore(score) {
		var n = score;
		//descri = name; 
		if (n.length == 0) {
			alert("暂无打分");
		} else {
			alert('成绩分数为：' + n);
		}
	}
	
	
	
</script>
</body>

</html>