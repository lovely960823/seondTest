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
<title>课程列表</title>
</head>
<body>
	<div class="container-fluid" >
		<div class="row">		
			<div class="row">
			<div class="col-md-12" style="width: 1380px;">
			<input name="id" type="hidden" value="${tclass.id}">
				<table class="table table-hover table-condensed table-bordered" >
					<thead>
						<tr>
							<td colspan="9">
							<form class="form-inline" action="classListAdmin" id="classListAdminSearchFrm">
								<!-- 默认看第一页 ,简化点击分页链接时候带表单参数问题-->
 								<input name="pageNo" type="hidden" value="1">
 								
									<div class="form-group">
										<label for="inputName">课程名</label>
										<input type="text" class="form-control" id="inputName" name="name" value="${param.name}"/>
									</div>
									<div class="form-group">
										<label for="selTid">教师</label>
										<select name="tid" id="selTid" class="form-control">
											<option value="-1">---请选择---</option>
											<c:forEach items="${types}" var="type">
												<option value="${type.id}">${type.name}</option>
											</c:forEach>
										</select>
									</div>
									<button type="submit" class="btn btn-default">搜索</button>&nbsp;&nbsp;&nbsp;
									 &nbsp;&nbsp;&nbsp;&nbsp;
									  <a href="javascript:void(0)" onclick="add()" >
									   <img alt="" src="resources/img/add.png" title="添加课程信息" >
									  </a>
									  &nbsp;&nbsp;&nbsp;&nbsp;
									  <a href="exportClass" >
									  <img alt="" src="resources/img/down.png" title="导出课程信息">
									  </a>
									  
									  <a href="main" >
									  <img alt="" src="resources/img/back.png" title="回到主页">
									  </a>
								</form>

							</td>
						</tr>
						<tr>
						    <th scope="col">序列</th>
							<th scope="col">课程名称</th>
							<th scope="col">课程代号</th>
							<th scope="col">上课教师</th>
							<th scope="col">上课时间</th>
							<th scope="col">上课地点</th>
							<th scope="col">剩余人数</th>
							<th scope="col">可选总数</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty pageInfo.content}">
								<tr>
									<td colspan="9" align="center">无数据</td>
								</tr>
							</c:when>
						<c:otherwise>
						<!-- pageInfo.list返回查询列表数据 -->
						<c:forEach items="${pageInfo.content}" var="tclass" varStatus="l">
						<tr>
						   <td scope="row">${l.count}</td>
							<td >${tclass.name}</td>
							<td >${tclass.num}</td>
							<td class="btn1" onclick="detail('${tclass.tid}')"><a href="javascript:void(0)">${tclass.tname}</a></td>
							<td >${tclass.timeName}</td>
							<td >${tclass.locationName}</td>
							<td >${tclass.count-tclass.rest_count}</td>
							<td >${tclass.count}</td>
							<td>  
							<a href="toClassEdit?id=${tclass.id}">
                                 <img alt="" src="resources/img/edit.png" title="修改">
                              </a>&nbsp;
							
								<a href="" onclick="del('${tclass.id }')">
									 <img alt="" src="resources/img/del.png" title="删除">
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
										<a href="classListAdmin?pageNo=${pageInfo.number+1-1}" class="page-link">&laquo;</a>
										</li>
									</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${pageInfo.totalPages<=5}">
											<c:forEach begin="1" end="${pageInfo.totalPages}" var="pi">
												<li class="page-item">
												<a href="classListAdmin?pageNo=${pi}" class="page-link">${pi}</a>
												</li>
											</c:forEach>
										</c:when>
										<c:when test="${pageInfo.number+1<=3}">
											<li class="page-item">
											<a href="classListAdmin?pageNo=1" class="page-link">1</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=2" class="page-link">2</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=3" class="page-link">3</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=4" class="page-link">4</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages}" class="page-link">...${pageInfo.totalPages}</a>
											</li>
										</c:when>
										<c:when test="${pageInfo.number+1>=pageInfo.totalPages-2}">
											<li class="page-item">
											<a href="classListAdmin?pageNo=1" class="page-link">1...</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages-3}" class="page-link">${pageInfo.totalPages-3}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages-2}" class="page-link">${pageInfo.totalPages-2}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages-1}" class="page-link">${pageInfo.totalPages-1}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages}" class="page-link">${pageInfo.totalPages}</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
											<a href="classListAdmin?pageNo=1" class="page-link">1...</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.number+1-1}" class="page-link">${pageInfo.number+1-1}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.number+1}" class="page-link">${pageInfo.number+1}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.number+1+1}" class="page-link">${pageInfo.number+1+1}</a>
											</li>
											<li class="page-item">
											<a href="classListAdmin?pageNo=${pageInfo.totalPages}" class="page-link">...${pageInfo.totalPages}</a>
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
										<a href="classListAdmin?pageNo=${pageInfo.number+1+1}" class="page-link">&raquo;</a>
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
</body>
<script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.my-modal.1.1.js"></script>
	<script type="text/javascript">
	
	var m1 = new MyModal.modal(function() {});
	$('.btn1').on("click", function() {
		m1.show();
	});
	
	function add(){
		window.location.href="toClassAdd";
	}
	function del(rId){
			if (confirm("确认删除吗")) {
				 $.ajax({ 
				url:"classDel",
				data:{'id':rId},
				type:'post',
				dataType:'json',
				success:function(data){
					if(data == '0'){
						alert("删除成功");
						window.location.href="classListAdmin";
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
	</script>
	<script type="text/javascript">
		$(function() {
			$("a[ href='classListAdmin?pageNo=${pageInfo.number+1}']").parent("li").addClass("active");
			//让Tid回填
			$('#selTid').val("${empty param.tid?-1:param.tid}");
			//让超级链接追加表单参数，最简单方式阻止超级链接默认行为，为表单添加隐藏字段a['class="page-link"']
			$('a[class="page-link"][href^="classListAdmin?pageNo="]').click(function(evt){
				evt.preventDefault();
				var prePage=${pageInfo.number+1-1};
				var nextPage=${pageInfo.number+1+1};
				//修改表单中pageNo的值
				if($(this).text()=="«"){
					$('#classListAdminSearchFrm input[name="pageNo"]').val(prePage);
				}else if($(this).text()=="»"){
					$('#classListAdminSearchFrm input[name="pageNo"]').val(nextPage);
				}else{
					$('#classListAdminSearchFrm input[name="pageNo"]').val($(this).text().replace(/\./g,''));
				}
				$("#classListAdminSearchFrm").submit();
			});
		});
		//该函数不能放置$(function(){})内部，如果放置进去了它就是局部变量了，删除链接点击时候就无法找到
		//同时不用担心它放置位置，因为当你能点击时候只要页面已加载完毕
		
function detail(tid){
	$.ajax({
		url:"teacherDetail",
		data:{"id":tid},
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

</html>