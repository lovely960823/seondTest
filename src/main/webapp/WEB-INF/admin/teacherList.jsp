<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师列表</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/jquery.my-modal.1.1.css" rel="stylesheet" type="text/css"/>
<link href="resources/css/jquery.my-modal.1.1.winStyle.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.book_news{border-collapse: collapse;}
.book_news tr th{border:1px #ccc solid;border-collapse: collapse;font-size:14px;height:30px;background: #f5f5f5;font-weight: normal;text-align: center}
.book_news tr td{border:1px #ccc solid;border-collapse: collapse;font-size:13px;height:30px;text-align: center}
</style>
</head>
<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<input type="hidden" name="id" value="${teacher.id}">
				<div style="height: 50px;padding-left: 550px"><h1>教师列表</h1></div>
				<div style="height: 50px;padding-left: 600px">
				<a href="toTeacherAdd">
					<img alt="" src="resources/img/add.png" title="添加">
				 </a>
				<a href="main" >
					<img alt="" src="resources/img/back.png" title="回到主页">
				  </a>
				  
				</div>
				 <div class="mainAll_RM">
                    <table   class="table table-bordered">
                      <thead>
                         <tr>
                            <th width="10%" >序号</th>
                            <th width="10%">姓名</th>
                            <th width="10%">编号</th>
                            <th width="10%">入职时间</th>
                            <th width="10%">联系电话</th>
                            <th width="10%">办公地点</th>
                            <th width="10%">邮箱</th>
                            <th width="20%">操作</th>
                          
                         </tr>
                      </thead>
                      
                      <tbody>
						<c:choose>
				   <c:when test="${empty pageInfo.content}">
					 <tr>
					 <td colspan="9">无数据</td>
					 </tr>
					 </c:when>
				     <c:otherwise>
						<c:forEach items="${pageInfo.content}" var="teacher" varStatus="l">
						<tr>
						    <td>${l.count}</td>
							<td class="btn1" onclick="detail('${teacher.id}')" title="点击查看教师详细信息"><a href="javascript:void(0)">${teacher.name}</a></td>
							<td >${teacher.num}</td>
							<td ><fmt:formatDate value="${teacher.pubdate}" pattern="yyyy-MM-dd"/></td>
							<td >${teacher.phone}</td>
							<td >${teacher.address}</td>
							<td >${teacher.email}</td>
							<td >
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							 <a href="toTeacherEdit?id=${teacher.id}"  >
									<img alt="" src="resources/img/edit.png" title="修改">
							   </a>
							   
								&nbsp;&nbsp;&nbsp;&nbsp;
								 <a href="" onclick="del('${teacher.id }')">
								<img alt="" src="resources/img/del.png" title="删除">
								</a> 
							   &nbsp;&nbsp;&nbsp;&nbsp;
								<a href="" onclick="ret('${teacher.id }')">
									<img alt="" src="resources/img/reset.png" title="重置密码">
								</a>
							</td>
						</tr>
						</c:forEach>
						</c:otherwise>
		               	</c:choose>
		               		<tr>
							<td colspan="9" style="padding-top: 0px; padding-bottom: 0px"
								class="text-center">
							<nav aria-label="Page navigation">
									<ul class="pagination">
									<c:choose>
									<c:when test="${pageInfo.first}">
										<li class="disabled page-item">
										<a href="javascript:void(0)" class="page-link">&laquo;</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
										<a href="toTeacherList?pageNo=${pageInfo.number+1-1}" class="page-link">&laquo;</a>
										</li>
									</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${pageInfo.totalPages<=5}">
											<c:forEach begin="1" end="${pageInfo.totalPages}" var="pi">
												<li class="page-item">
												<a href="toTeacherList?pageNo=${pi}" class="page-link">${pi}</a>
												</li>
											</c:forEach>
										</c:when>
										<c:when test="${pageInfo.number+1<=3}">
											<li class="page-item">
											<a href="toTeacherList?pageNo=1" class="page-link">1</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=2" class="page-link">2</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=3" class="page-link">3</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=4" class="page-link">4</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages}" class="page-link">...${pageInfo.totalPages}</a>
											</li>
										</c:when>
										<c:when test="${pageInfo.number+1>=pageInfo.totalPages-2}">
											<li class="page-item">
											<a href="toTeacherList?pageNo=1" class="page-link">1...</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages-3}" class="page-link">${pageInfo.totalPages-3}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages-2}" class="page-link">${pageInfo.totalPages-2}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages-1}" class="page-link">${pageInfo.totalPages-1}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages}" class="page-link">${pageInfo.totalPages}</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
											<a href="toTeacherList?pageNo=1" class="page-link">1...</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.number+1-1}" class="page-link">${pageInfo.number+1-1}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.number+1}" class="page-link">${pageInfo.number+1}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.number+1+1}" class="page-link">${pageInfo.number+1+1}</a>
											</li>
											<li class="page-item">
											<a href="toTeacherList?pageNo=${pageInfo.totalPages}" class="page-link">...${pageInfo.totalPages}</a>
											</li>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
									<c:when test="${pageInfo.last}">
										<li class="disabled page-item">
										<a href="javascript:void(0)" class="page-link">&raquo;</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
										<a href="toTeacherList?pageNo=${pageInfo.number+1+1}" class="page-link">&raquo;</a>
										</li>
									</c:otherwise>
									</c:choose>
									</ul>
								</nav>
							</td>
						</tr>
						</tbody>
                    </table>
 
               </div>
			</div>
		</div>
	</div>
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
	<script type="text/javascript">
	
	var m1 = new MyModal.modal(function() {});
	$('.btn1').on("click", function() {
		m1.show();
	});
	
	function del(rId){
			if (confirm("确认删除吗")) {
				 $.ajax({ 
				url:"teacherDel",
				data:{'id':rId},
				type:'post',
				dataType:'json',
				success:function(data){
					if(data == '0'){
						alert("删除成功");
						window.location.href="toTeacherList";
					}else{
						alert("删除失败");
					}
					
				},
				error:function(data){
					alert('服务器繁忙');
				}
				}); 
			} 
	};
	function ret(rId){
		if (confirm("确认重置吗")) {
			 $.ajax({ 
			url:"toResetTeaPwd",
			data:{'id':rId},
			type:'post',
			dataType:'json',
			success:function(data){
				if(data == '0'){
					alert("重置成功");
					window.location.href="toTeacherList";
				}else{
					alert("重置失败");
				}
				
			},
			error:function(data){
				alert('服务器繁忙');
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
</body>
</html>