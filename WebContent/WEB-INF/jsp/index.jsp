<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.2.1.js"></script>
<title>index</title>
</head>
<body>
	${errorMsg}
	<div id="title">
		<h1>综合平台</h1>
	</div>
	<div id="main">
		<table>
			<tr>
				<td><a href="app?redirectUrl=http://127.0.0.1:8081/sso/app1">应用一</a></td>
				<td><a href="app?redirectUrl=http://127.0.0.1:8081/sso/app2">应用二</a></td>
				<td><a href="app?redirectUrl=http://127.0.0.1:8081/sso/app3">应用三</a></td>
				<td><a href="app?redirectUrl=http://127.0.0.1:8081/sso/app4">应用四</a></td>
			</tr>
		</table>
	</div>
</body>
<style>
a {
	text-decoration: none;
}

div#title {
	width: 100%;
	text-align: center;
	position: absolute;
	top: 10%;
}

div#main {
	width: 100%;
	position: absolute;
	top: 30%;
}

table {
	width: 80%;
	text-align: center;
	margin: auto;
}
</style>

</html>