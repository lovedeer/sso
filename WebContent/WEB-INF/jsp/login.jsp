<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form id="form" action="login?appUrl=http://127.0.0.1:8080/sso/index" method="post">
		<table>
			<tr>
				<td>用户名：</td><td><input type="text" name="username"/></td>
			</tr>
			<tr>
				<td>密码:</td><td><input type="password" name="password"/></td>
			</tr>
			<tr><td><input type="submit" value="提交"></td></tr>
		</table>
	</form>
</body>
</html>