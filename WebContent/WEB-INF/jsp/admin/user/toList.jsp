<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="code" uri="http://java.code.el"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示所有用户信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"></link>
</head>
<body>

	<div class="container">
		<!-- 查询信息传递 -->
		<form id="query" method="post" action="${pageContext.request.contextPath}/user/toList.htmlx">
			<input type="hidden" id="currentPage" name="currentPage" >
			<div class="row">
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					 <label for="exampleInputName2">用户名</label>
					 <input name="params['uname']"  type="text" class="form-control" id="exampleInputName2" >
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					    <label for="exampleInputName2">Email</label>
					    <input name="params['email']" type="text" class="form-control" id="exampleInputName2" >
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					    <label for="exampleInputName2">手机号</label>
					    <input name="params['phone']" type="text" class="form-control" id="exampleInputName2">
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					   <label class="sr-only" for="name">用户类型</label>
						<select>
							<c:forEach var="item" items="${code:codeType('100')}">
								<option>${item.value }</option>
							</c:forEach>
						</select>
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
			  	   <label for="exampleInputName2">&emsp;</label>
			  	 	<button type="submit"  class="btn btn-default">查询</button>
				 </div>
			  </div>
			</div>
			
		</form>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>用户名</th>
					<th>用户类型</th>
					<th>Email</th>
					<th>手机号</th>
					<th>登录状态</th>
					<th>家庭地址信息</th>
					<th>注册时间</th>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.result}" varStatus="status">
					<tr>
						<td>${status.index}</td>
						<td>${item.uname}</td>
						<td>${code:formatSimpleCode(item.userType)}</td>
						<td>${item.email }</td>
						<td>${item.phone }</td>
						<td>${item.state }</td>
						<td>家庭地址信息</td>
						<td>${item.registerDate }</td>
						<c:if test="${item.userType != '100001' }">
						<td><a href="${pageContext.request.contextPath}/user/toAuthor.htmlx?user.userId=${item.userId}">授权角色</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						 <nav>
					      <ul class="pagination pagination-sm">
					     	<c:choose>
					     		<c:when test="${pageVo.currentPage==1}">
						     		 <li class="disabled">
						     			<a href="#" aria-label="Previous">
						        			<span aria-hidden="true">&laquo;</span>
						        		</a>
						        	 </li>
					     		</c:when>
					     		<c:otherwise>
					     			<li>															
						     			<a href="#"  onclick="javascript:void(query('${pageVo.currentPage-1}'));" aria-label="Previous">
						        			<span aria-hidden="true">&laquo;</span>
						        		</a>
					        		</li>
					     		</c:otherwise>
					     	</c:choose>
					       
					        	
					        
					        <c:forEach begin="1" end="${pageVo.totalPage}" step="1" var="item">
					        	<c:choose>
										<c:when test="${pageVo.currentPage eq item}">
											<li class="active"><a href="#"> ${item} </a></li>
										</c:when>
										<c:otherwise>
											<li><a href="#" onclick="javascript:query('${item}');">${item} </a></li>
										</c:otherwise>
								</c:choose>
					        </c:forEach>
					        <c:choose>
					     		<c:when test="${pageVo.currentPage==pageVo.totalPage}">
						     		 <li class="disabled">
						     			<a href="#" aria-label="Previous">
						        			<span aria-hidden="true">&raquo;</span>
						        		</a>
						        	 </li>
					     		</c:when>
					     		<c:otherwise>
					     			<li>
						     			<a onclick="javascript:query('${pageVo.currentPage+1}');" href="#" aria-label="Previous">
						        			<span aria-hidden="true">&raquo;</span>
						        		</a>
					        		</li>
					     		</c:otherwise>
					     	</c:choose>
					      </ul>
					    </nav>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function query(currentPage){
	$("#currentPage").val(currentPage);
	$("#query")[0].submit();
} 
</script>
</html>