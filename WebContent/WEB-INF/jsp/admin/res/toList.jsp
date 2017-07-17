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
		<a href="${pageContext.request.contextPath}/res/toAdd.htmlx">新增根节点</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>资源名称</th>
					<th>父节点</th>
					<th>资源路径</th>
					<th>排序</th>
					<th>录入时间</th>
					<th>备注</th>
					<th>操作列</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.result}" varStatus="status">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}<a href="${pageContext.request.contextPath}/res/toList.htmlx?params['parentId']=${item.id }">查看子节点</a></td>
						<td>${item.parentId }</td>
						<td>${item.path }</td>
						<td>${item.rorder }</td>
						<td>${item.createTs }</td>
						<td>${item.note }</td>
						<td>
							<a href="${pageContext.request.contextPath}/res/toAdd.htmlx?res.parentId=${item.id}">增加子节点 </a>
							<a href="${pageContext.request.contextPath}/res/todelete.htmlx?res.id=${item.id}">删除节点 </a>
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