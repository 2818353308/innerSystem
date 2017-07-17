<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>给角色授权</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>

</head>
<body>
	<ul id="tree">
		
	</ul>
	
	<form id="saveAuthorRole" action="${pageContext.request.contextPath}/role/saveAuthorRole.htmlx">
		<input id="resourcesIds" name="resourcesIds" value="" /><br/>
		<input id="roleId" name="roleId" value="${authorRole.roleId }" /><br/>
		<input type="button" onclick="getSelects()" value="保存" >
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#tree").tree({
			url:"${pageContext.request.contextPath}/res/all.htmlx",
			checkbox:true,
			formatter:function(node){
				return node.name;
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
            s += nodes[i].id;
        }
		$("#resourcesIds").val(s);
		$("#saveAuthorRole")[0].submit();
	}
</script>
</html>