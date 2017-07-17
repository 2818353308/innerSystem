<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>给用户授权角色</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>

</head>
<body>
	<ul id="tree">
		
	</ul>
	
	<form id="saveUserRole" action="${pageContext.request.contextPath}/user/saveAuthor.htmlx">
		<input id="roleIds" name="roleIds" value="" /><br/>
		<input id="userId" name="userId" value="${user.userId }" /><br/>
		<input type="button" onclick="getSelects()" value="保存" >
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#tree").tree({
			url:"${pageContext.request.contextPath}/role/all.htmlx",
			checkbox:true,
			formatter:function(node){
				return node.roleName;
			},
// 			onLoadSuccess:function (node,data){
// 				alert(data);
// 			} 
		});
	})
	function getSelects(){
		var nodes = $('#tree').tree('getChecked',['checked','indeterminate']);
        var s = '';
        for (var i = 0; i < nodes.length; i++) {
            if (s != '')
            s += ',';
            s += nodes[i].roleId;
        }
		$("#roleIds").val(s);
		$("#saveUserRole")[0].submit();
	}
</script>
</html>