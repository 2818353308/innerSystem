<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>
<script type="text/javascript"	
	src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript"				
	src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jqueryValidator/jquery.validate.min.js"></script>

<script>
</script>
<style>
	*{margin:0;margin:auto;}
	.login{width:1000px;margin:auto;} 
	
	.lo{display: block;width: 140px;height:36px;background:#40adee;transition:0.3s;border-radius:30px;text-align:center;line-height: 36px;text-decoration: none;}
	.lo:hover{background:#00ff00;transition:1s;font-weight:700;border-radius:20px;}
	input{margin-top:5px;height:20px;border-width:0 0 1px;border-color:#000033;
			font-size:16px;backround:transparent;outline:none;}
</style>
</head>
<script type="text/javascript">
		$(function (){
			$("#login").validate({
				rules:{
					uname:{
						required:true
					},
					upass:{
						required:true
					},
					logincode:{
						required:true						
					}
				},
				messages:{
					uname:{
						required:"用户名不能为空！"
					},
					upass:{
						required:"密码不能为空！"
					},
					logincode:{
						required:"验证码不能为空！"				
					}
				},
				submitHandler:function (form){
					$.post("${pageContext.request.contextPath}/login.htmlx?"+$("#login").serialize(),function (data){
						//alert(data);0
						console.log(data+"--------------------");
						$(form)[0].submit();
					});
					return true;
				}
			});
		});
	</script>
<body>
	<div class="login easyui-panel" title="用户登录系统" style="width: 400px;margin:auto;">
		<div style="font-color:red;">
			<c:forEach items="${errors}" var="item">
				${item.context }
			</c:forEach>
		</div>
		<form  id="login" method="post" action="${pageContext.request.contextPath}/login.htmlx">
			<table>
				<tr>
					<td>用户名</td>
					<td><input class="easyui-validatebox" type="text" name="uname"/></td>
				</tr>
				<tr>
					<td>登录密码</td>
					<td><input class="easyui-validatebox" type="password" name="upass"/></td>
				</tr>
				<tr>
					<td>认证码</td>
					<td>
						<input type="text" id="loginCode" name="loginCode"/>
						<img id="vcode" src="${pageContext.request.contextPath}/code/vlogin.htmlx"/> <a href="javascript:void(document.getElementById('vcode').src='${pageContext.request.contextPath}/code/vlogin.htmlx?v='+new Date());" >看不清楚？</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input class="lo" type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>