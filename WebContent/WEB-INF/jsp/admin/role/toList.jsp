<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示所有资源信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"></link>
</head>
<body>

	<div class="container">
		<!-- 查询信息传递 -->
		<form id="query" method="post" action="${pageContext.request.contextPath}/res/toList.htmlx">
			<input type="hidden" id="currentPage" name="currentPage" >
		</form>
<%-- 		<a href="${pageContext.request.contextPath}/res/toAdd.htmlx">新增根节点</a> --%>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>角色ID</th>
					<th>角色名称</th>
					<th>排序</th>
					<th>录入时间</th>
					<th>备注</th>
					<th>操作列</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.result}" varStatus="status">
					<tr>
						<td>${item.roleId}</td>
						<td>${item.roleName}</td>
						<td>${item.orderBy }</td>
						<td>${item.createTs }</td>
						<td>${item.note }</td>
						<td>
							<a href="${pageContext.request.contextPath}/role/toAuthor.htmlx?authorRole.roleId=${item.roleId}">给角色授权</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
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