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
		<form method="post" action="${pageContext.request.contextPath}/res/add.htmlx">
			<table class="table">
				<tr>
					<td>资源Id</td>
					<td><input name="res.id" value="${res.id}" type="text" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>资源名称</td>
					<td><input name="res.name" value="${res.name}" type="text"/></td>
				</tr>
				<tr>
					<td>资源父节点</td>
					<td><input name="res.parentId" value="${res.parentId}" type="text" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>路径</td>
					<td><input name="res.path" value="${res.path}" type="text"/></td>
				</tr>
				<tr>
					<td>排序</td>
					<td><input name="res.rorder" value="${res.rorder}" type="text" /></td>
				</tr>
				<tr>
					<td>备注</td>
					<td>
						<textarea rows="5" cols="70" name="res.note">${res.note}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="保存/更新"/>
					</td>
				</tr>
			</table>
		</form>
		
	</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
</html>