<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/sso.css">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/templatemo_main.css">
<title>无锡市质量技术监督局</title>
<style type="text/css">
ol, ul, li {
	list-style: none;
}

a {
	text-decoration: none;
}

a:hover, a:active, a:focus {
	text-decoration: none;
}

.clearfix:after {
	clear: both;
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}

.clearfix {
	display: block;
	*zoom: 1;
}

.templatemo-site-title {
	margin-top: 27px;
	margin-bottom: 22px;
}

#logo {
	display: inline;
}

.tit {
	color: #fff;
	font-size: 30px;
}

#logo>a>img {
	width: 120px;
}

#list>li {
	float: left;
	text-align: center;
	padding: 0 5px;
}

#list>li>a>p {
	text-align: center;
	font-size: 20px;
}

.container>div {
	margin: 0 auto;
}

.templatemo-content {
	width: 100%;
}

#menu-section {
	margin-top: 50px;
}

.wxcs {
	position: fixed;
	bottom: 10px;
	left: 20px;
	font-size: 16px;
}

#list {
	margin: 0 !important;
}

#logo>a>img {
	width: 400px;
}

.templatemo-logo {
	margin-top: 10px;
}

.titles {
	font-size: 20px !important;
}

.center {
	width: 100%;
	margin: auto;
}

#list>li>a>img {
	height: 160px;
	width: 270px;
}

#search {
	position: fixed;
	top: 10px;
	right: 20px;
	z-index: 999;
}

footer {
	position: fixed;
	color: white;
	bottom: 0;
	left: 0;
	right: 0;
	text-align: center;
	height: 30px;
	line-height: 30px;
}

@media only screen and (min-width: 100px) and (max-width: 1550px) {
	#list>li>a>img {
		width: 225px;
	}
}

@media only screen and (min-width: 100px) and (max-width: 640px) {
}
</style>
</head>
<body>
	<div id="main-wrapper">
		<div
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center templatemo-logo margin-top-20">
			<p id="logo">
				<img src="pic/logo.png" usemap="#planetmap" alt="Planets" border="0">
				<map name="planetmap" id="planetmap">
					<area shape="rect" coords="145,0,272,140"
						href="http://wxqts.wuxi.gov.cn/" target="_blank"
						style="outline: none;">
				</map>
			</p>
			<h1 class="tit" style="font-size: 50px">无锡市质量技术监督综合业务平台</h1>
			<p></p>

		</div>

		<div class="image-section">
			<div class="image-container">
				<img src="pic/bg.jpg" id="menu-img" class="main-img" style="display:none">

			</div>
		</div>

		<div class="center col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div id="fs-login">
				<div id="fs-login-glass"></div>
				<div id="fs-login-content">
					<form id="form" action="login" method="post">
						<div class="fs-login-input fs-login-input-username">
							<input tabindex="1" class="fs-login-username" placeholder="用户名"
								title="用户名" type="text" name="username">
							<div class="fs-login-errmask" style="width: 308px;">${errorMsg}</div>
						</div>
						<div class="fs-login-input fs-login-input-password">
							<input tabindex="2" class="fs-login-password" placeholder="密码"
								title="密码" type="password" name="password">
						</div>
						<input type="submit" tabindex="3" value="登录" id="fs-login-btn">
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer>©<a href="http://www.nsccwx.cn/" target="_blank"><font color="white">国家超级计算无锡中心版权所有</font> </a>&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.miibeian.gov.cn/" target="_blank"><font color="white">苏ICP备16008843号-1</font></a></footer>
	<div class="backstretch" style="left: 0px; top: 0px; overflow: hidden; margin: 0px; padding: 0px; height: 943px; width: 1920px; z-index: -999999; position: fixed;"><img style="position: absolute; margin: 0px; padding: 0px; border: medium none; width: 1920px; height: 1080px; max-height: none; max-width: none; z-index: -999999; left: 0px; top: -68.5px;" src="pic/bg.jpg"></div>
	<script src="js/jquery_002.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/main.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(document).ready(function(){
			var errorMsg = $("div.fs-login-errmask");
			var value = $.trim(errorMsg.text());
			if(value == "")
				$("div.fs-login-errmask").hide();
			$("#form").click(function(){
				$("div.fs-login-errmask").hide();
			})
		});
	</script>
</body>
</html>